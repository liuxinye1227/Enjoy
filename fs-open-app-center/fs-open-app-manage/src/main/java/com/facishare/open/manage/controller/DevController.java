package com.facishare.open.manage.controller;

import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.model.MetaParam;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.ad.api.enums.ModuleKeyEnum;
import com.facishare.open.app.ad.api.service.CheckAppUpdatedService;
import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.model.vo.IconFileVO;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.service.AppIconService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.app.center.api.utils.JsonKit;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.dao.AppIconDAO;
import com.facishare.open.manage.model.AppIconDO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.msg.model.PrivateAppServiceConfig;
import com.facishare.open.msg.result.MessageResult;
import com.facishare.open.msg.service.MsgSessionService;
import com.facishare.open.oauth.result.CommonResult;
import com.facishare.open.oauth.service.EaAuthService;
import com.facishare.qixin.api.model.open.arg.OpenGetOSS1SessionDefinitionArg;
import com.facishare.qixin.api.model.open.result.GetOSS1SessionDefinitionResult;
import com.facishare.qixin.api.open.OpenSessionService;
import com.github.jedis.support.MergeJedisCmd;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by albeter on 17/3/15.
 */


@Controller
@RequestMapping("/open/manage/dev/tool")
//@Service
public class DevController extends BaseController {

    @Resource
    MsgSessionService msgSessionService;


    @Resource
    OpenAppService openAppService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private MergeJedisCmd jedis;

    @Resource
    private CheckAppUpdatedService checkAppUpdatedService;


    @Resource
    private AppIconService appIconService;

    @Resource
    private AppIconDAO appIconDAO;

    @Resource
    private OpenSessionService openSessionService;

    @Resource
    private EaAuthService eaAuthService;

    private static final Logger logger = LoggerFactory.getLogger("DEV_LOG");

    @RequestMapping("/modifySessionLogo")
    @ResponseBody
    public AjaxResult modifySessionLogo(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "logoUrl", required = true) String logoUrl
    ) {

        AppResult appResult = openAppService.loadOpenAppFast(appId);
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在");
        }

        String serviceName = appResult.getResult().getServiceName();

        MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(appId, serviceName, logoUrl);
        if (!messageResult.isSuccess()) {
            logger.warn("updateSessionNameLogoDesc failed to updateUniversalSessionDefinition: appId={}, serviceName={}, logourl={}, result={}"
                    + appId, serviceName, logoUrl, messageResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, messageResult, "修改session消息错误");
        }

        return new AjaxResult(messageResult);
    }


    @RequestMapping("/redisGetStr")
    @ResponseBody
    public AjaxResult redisGetStr(
            @RequestParam(value = "key", required = true) String key
    ) {
        return new AjaxResult(jedis.get("key"));
    }


    @RequestMapping(value = "/setServiceNumName")
    @ResponseBody
    public AjaxResult setServiceNumName(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "ea", required = true) String ea,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "logo", required = false) String logo

    ) {
        PrivateAppServiceConfig config = new PrivateAppServiceConfig();
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(logo)){
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "入参错误,name和logo不能同时为空");
        }
        config.setAppId(appId);
        config.setEa(ea);
        config.setName(name);
        config.setPortrait(logo);
        MessageResult messageResult = msgSessionService.updatePrivateAppServiceConfig(config);
        return new AjaxResult(messageResult);
    }



    @RequestMapping(value = "/updateAppIcon")
    @ResponseBody
    public AjaxResult updateAppIcon(
            @RequestParam(value = "appIds", required = true) String appIds

    ) {

        try {
            if (appIds.isEmpty()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "入参错误,appIds不能为空");
            }

            List<String> appIdsList = Arrays.asList(appIds.split(","));
            List<AppIconDO> appIconDOs = appIconDAO.queryByAppIds(appIdsList);
            List<AppIconDO> collect = appIconDOs;
//        List<AppIconDO> collect = appIconDOs.stream().filter(
//                appIconDO -> "FSAID_989764".equals(
//                        appIconDO.getAppId())).collect(Collectors.toList());

            //1.扫描app_icon表,如果icon_key以group开头则把头像下载下来.
            //2.判断icon_key对应的app_id是组件还是应用,调对应的接口修改头像.
            // 如果是应用或者服务号则调用修改更新服务号的接口: OpenFsUserAppViewManagerImplupdateSessionNameLogo
            //3.在redis中记录该app_id已经处理过.

            if(CollectionUtils.isEmpty(collect)){
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "collect为空");
            }


            for (AppIconDO appIconDO : collect) {
                if (!appIconDO.getMasterIconId().startsWith("group")) {
                    logger.error("image id is not startwith group. appId[{}]", appIconDO.getAppId());
                    continue;
                }
                String extName = getExtName(appIconDO.getMasterIconId());
                String fileName = "a." + extName;
                try {
                    downloadFile(ConfigCenter.OLD_DOWNLOAD_IMAGE_URL + appIconDO.getMasterIconId(), fileName);
                } catch (Exception e) {
                    logger.error("downloadFile error. appId[{}]", appIconDO.getAppId(), e);
                    continue;
                }
                byte[] bytes1;
                try {
                    File file = new File(fileName);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    bytes1 = IOUtils.toByteArray(fileInputStream);
                } catch (Exception e) {
                    logger.error("file to local error. appId[{}]", appIconDO.getAppId(), e);
                    continue;
                }

                if (bytes1.length <= 0) {
                    logger.error("bytes1 empty error. appId[{}]", appIconDO.getAppId());
                    continue;
                }
//
                List<IconType> types = new ArrayList<>();
                types.add(IconType.LOGO);
                Rectangle rectangle = new Rectangle(0, 0, 1, 1);
                com.facishare.open.app.center.api.result.BaseResult result = appIconService
                        .update(appIconDO.getAppId(), types, bytes1, extName, rectangle);
                logger.info("appIconService.update, appId[{}] , result[{}]", appIconDO.getAppId(), result);

                    OpenGetOSS1SessionDefinitionArg arg = new OpenGetOSS1SessionDefinitionArg();
                    arg.setAppId(appIconDO.getAppId());
                    GetOSS1SessionDefinitionResult oss1SessionDefinition = openSessionService.getOSS1SessionDefinition(arg);

                    if (oss1SessionDefinition.getOss1SessionDefinition() == null) {
                        logger.info("oss1SessionDefinition.getOss1SessionDefinition is null, appId[{}]",appIconDO.getAppId());
                        continue;
                    }

                    //调用军卫的接口判断session是否存在
                    MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(
                            appIconDO.getAppId(), null,
                            queryAppIconUrl(appIconDO.getAppId(), IconType.LOGO));
                    logger.info(" msgSessionService.updateUniversalSessionDefinition, appId[{}] , result[{}]",
                            appIconDO.getAppId(), messageResult);

            }
        } catch (Exception e) {
            logger.info("updateAppIcon error,appIds[{}]", appIds, e);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "未知错误");
        }
        return new AjaxResult(AjaxCode.OK);
    }

    @RequestMapping(value = "/findSessionDefine")
    @ResponseBody
    public AjaxResult findSessionDefine(
            @RequestParam(value = "appId", required = true) String appId

    ) {

        OpenGetOSS1SessionDefinitionArg arg = new OpenGetOSS1SessionDefinitionArg();
        arg.setAppId(appId);
        GetOSS1SessionDefinitionResult oss1SessionDefinition = openSessionService.getOSS1SessionDefinition(arg);

        if(oss1SessionDefinition.getOss1SessionDefinition() == null){
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "无session");
        }

        return new AjaxResult(JsonKit.object2json(oss1SessionDefinition));

    }

    @RequestMapping(value = "/updateSessionIcon")
    @ResponseBody
    public AjaxResult updateSessionIcon(
            @RequestParam(value = "appIds", required = true) String appIds

    ) {

        checkParam(appIds, "appIDs不能为空");
        List<String> appIdsList = Arrays.asList(appIds.split(","));

        ArrayList<String> succList = Lists.newArrayList();

        for (String appId : appIdsList) {

            OpenGetOSS1SessionDefinitionArg arg = new OpenGetOSS1SessionDefinitionArg();
            arg.setAppId(appId);
            GetOSS1SessionDefinitionResult oss1SessionDefinition = openSessionService.getOSS1SessionDefinition(arg);

            if (oss1SessionDefinition.getOss1SessionDefinition() == null) {
                logger.info(" oss1SessionDefinition.getOss1SessionDefinition is null, appId[{}] , result[{}]", appId);
                continue;
            }

            //调用军卫的接口判断session是否存在
            MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(
                    appId, null,
                    queryAppIconUrl(appId, IconType.LOGO));
            logger.info(" msgSessionService.updateUniversalSessionDefinition, appId[{}] , result[{}]",
                    appId, messageResult);

            succList.add(appId);
        }

        return new AjaxResult(succList);
    }

    @RequestMapping(value = "/updateDefaultSessionIcon")
    @ResponseBody
    public AjaxResult updateDefaultSessionIcon(
            @RequestParam(value = "appIds", required = true) String appIds,
            @RequestParam(value = "iconUrl", required = true) String iconUrl

    ) {

        checkParam(appIds, "appIDs不能为空");
        List<String> appIdsList = Arrays.asList(appIds.split(","));

        ArrayList<String> succList = Lists.newArrayList();

        for (String appId : appIdsList) {

            OpenGetOSS1SessionDefinitionArg arg = new OpenGetOSS1SessionDefinitionArg();
            arg.setAppId(appId);
            GetOSS1SessionDefinitionResult oss1SessionDefinition = openSessionService.getOSS1SessionDefinition(arg);

            if (oss1SessionDefinition.getOss1SessionDefinition() == null) {
                logger.info(" oss1SessionDefinition.getOss1SessionDefinition is null, appId[{}] , result[{}]", appId);
                continue;
            }

            //调用军卫的接口判断session是否存在
            MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(
                    appId, null,
                    iconUrl);
            logger.info(" msgSessionService.updateUniversalSessionDefinition, appId[{}] , result[{}]",
                    appId, messageResult);

            succList.add(appId);
        }

        return new AjaxResult(succList);
    }


    @RequestMapping("/updateAppList")
    @ResponseBody
    public AjaxResult updateAppList(@RequestParam(value = "eas", required = true) String eas){

        if(StringUtils.isEmpty(eas)){
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "入参错误");
        }

        String appId = ConfigCenter.UPDATE_APP_LIST_FIXED_APP;

        List<String> eaList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(eas);

        if(CollectionUtils.isEmpty(eaList)){
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "入参错误,数组为空");
        }

        eaList.forEach(fsEa -> {
            //通知终端拉列表
            List<Integer> allUserId = getAllUserIdBy(fsEa);

            //更新checkupdate
            BaseResult<Void> voidBaseResult = checkAppUpdatedService
                    .resetTagByModuleKeyAndUserIds(ModuleKeyEnum.COMPONENTS, fsEa, allUserId);

            if(!voidBaseResult.isSuccess()){
                logger.warn("checkAppUpdatedService.resetTagByModuleKeyAndUserIds . ea[{}]",fsEa);
            }

            //2,通过企信通知手机端更新
            Map<String, Object> map = new HashMap<>();
            map.put("timestamp", System.currentTimeMillis());
            String value = JsonKit.object2json(map);
            MessageResult msgResult = msgSessionService.updateUserPropertiesBatchAsync(appId, fsEa, allUserId,
                    CommonConstant.UPDATE_USER_PROPERTIES_VIEW_MODIFIED, value);
            if (!msgResult.isSuccess()) {
                logger.warn("msgSessionService.updateUserPropertiesBatchAsync appId[{}], fsEa[{}], ownerIds[{}], msgResult[{}]", appId, fsEa, allUserId, msgResult);
            }

        });

        return new AjaxResult(AjaxCode.OK);

    }


    @RequestMapping("/updateAppEaStop")
    @ResponseBody
    public AjaxResult updateAppEaStop(@RequestParam(value = "ea", required = true) String ea,
                                      @RequestParam(value = "appId", required = true) String appId) {

        CommonResult commonResult = eaAuthService.stopEaAuth("", null, ea, appId);

        if (commonResult.isSuccess()) {
            AjaxResult ajaxResult = updateAppList(ea);
        }

        return new AjaxResult(commonResult);

    }

    @RequestMapping("/updateAppEaStart")
    @ResponseBody
    public AjaxResult updateAppEaStart(@RequestParam(value = "ea", required = true) String ea,
                                       @RequestParam(value = "appId", required = true) String appId) {

        CommonResult commonResult = eaAuthService.startEaAuth("", null, ea, appId);

        if (commonResult.isSuccess()) {
            AjaxResult ajaxResult = updateAppList(ea);
        }

        return new AjaxResult(commonResult);

    }

        private List<Integer> getAllUserIdBy(String fsEa) {
        final List<Integer> userIds;

            userIds = new ArrayList<>();
            MetaParam metaParam = new MetaParam(fsEa, 1000);
            ListResult<Integer> listResult = employeeService.getAllEmployeeIds(metaParam, 2);
            if (!listResult.isSuccess()) {
                // 获取公司所有有效员工失败：
                logger.warn("fail to employeeService.getAllEmployeeIds: metaParam[{}], status[2], result={}",
                        metaParam, listResult);
                throw new BizException(listResult);
            }
            if (null != listResult.getResult()) {
                userIds.addAll(listResult.getResult());
            }

        return userIds;
    }

    private String queryAppIconUrl(String appIdOrComponentId, IconType type){
        com.facishare.open.app.center.api.result.BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(
                appIdOrComponentId, type);
        if (!iconUrlResult.isSuccess()) {
            System.out.println("queryAppIconUrl failed, appIdOrComponentId[{}], type[{}], result[{}] : " + appIdOrComponentId+ type+ iconUrlResult);
        }
        return iconUrlResult.getResult();
    }

    private String getExtName(final String fileName) {
        String ext = "";
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex > -1) {
            ext = fileName.substring(lastIndex + 1);
        }
        return ext;
    }

    public static void downloadFile(String url, String localPath) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if(response.getStatusLine().getStatusCode() != 200){
                    throw new BizException(response.getStatusLine().getStatusCode(),"response status error");

                }
                // Get hold of the response entity
                HttpEntity entity = response.getEntity();
                // If the response does not enclose an entity, there is no need
                // to bother about connection release
                if (entity != null) {
                    InputStream in = entity.getContent();
                    try {
                        // do something useful with the response
                        byte[] buffer = new byte[1024];
                        BufferedInputStream bufferedIn = new BufferedInputStream(in);
                        int len = 0;
                        FileOutputStream fileOutStream = new FileOutputStream(new File(localPath));
                        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutStream);
                        while ((len = bufferedIn.read(buffer, 0, 1024)) != -1) {
                            bufferedOut.write(buffer, 0, len);
                        }
                        bufferedOut.flush();
                        bufferedOut.close();
                    } catch (IOException ex) {
                        // In case of an IOException the connection will be released
                        // back to the connection manager automatically
                        throw ex;
                    } finally {
                        // Closing the input stream will trigger connection release
                        in.close();
                    }
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
