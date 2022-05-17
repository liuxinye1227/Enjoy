package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.common.utils.EncodingAesKeyUtil;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.cons.CenterConstants;
import com.facishare.open.manage.manager.AppManager;

import com.facishare.open.manage.manager.ServiceManager;
import com.facishare.open.manage.model.OpenAppScopeVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.msg.constant.MessageSendTypeEnum;
import com.facishare.open.oauth.model.enums.ScopeOwnerEnum;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * 服务号管理接口
 *
 * @author chenzs
 * @date 2016年10月11日
 */
@Controller
@RequestMapping("/open/manage/rest/openService")
public class OpenServiceController extends BaseController {

    @Autowired
    private AppManager appManager;

    @Autowired
    private ServiceManager serviceManager;

    @RequestMapping("/listScope")
    @ResponseBody
    public AjaxResult listScope() {
        return new AjaxResult(appManager.listScope(ScopeOwnerEnum.SERVICE));
    }

    public static void main(String[] args) {
        Instant instant = Instant.ofEpochMilli(1487721600000L - 8 * 60 * 60 * 1000L);
        System.out.println(instant);
        System.out.println(new Date(1487721600000L));
        System.out.println(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));


        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1487721600000L), ZoneId.systemDefault());

        System.out.println(zonedDateTime);
    }

    @RequestMapping("/statistic")
    @ResponseBody
    public AjaxResult statistic(@RequestParam(value = "startTime", required = false) Long startTime,
                                @RequestParam(value = "endTime", required = false) Long endTime) {
        checkParam(startTime, "开始时间不能为空");
        checkParam(endTime, "结束时间不能为空");

        long timeZoneOff = 8 * 60 * 60 * 1000L;
        startTime -= timeZoneOff;
        endTime -= timeZoneOff;
        logger.info("startLocalTime[{}], startLocalDate[{}]", startTime, new Date(startTime));
        logger.info("endLocalTime[{}], endLocalDate[{}]", endTime, new Date(endTime));

        Map<String, Boolean> switchMap = ConfigCenter.SERVICE_STATISTICS_SWITCH_MAP;
        Map<String, Object> result = new HashedMap();

        // 群发消息数
        String groupSendMsgCountKey = CenterConstants.SERVICE_STATISTIC_GROUP_SEND_MSG_COUNT;
        Boolean groupSendMsgCountSwitch = switchMap.get(groupSendMsgCountKey);
        long count = -1;
        if (groupSendMsgCountSwitch != null && groupSendMsgCountSwitch) {
            try {
                count = serviceManager.getGroupSendMsgCount(startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getGroupSendMsgCount error. startTime[{}], endTime[{}]", startTime, endTime, e);
            }
        }
        result.put(groupSendMsgCountKey, count) ;
        logger.info("[{}] = [{}]", groupSendMsgCountKey, count);

        // 群发消息服务号数
        String groupSendMsgServiceCountKey = CenterConstants.SERVICE_STATISTIC_GROUP_SEND_MSG_SERVICE_COUNT;
        Boolean groupSendMsgServiceCountSwitch = switchMap.get(groupSendMsgServiceCountKey);
        count = -1L;
        if (groupSendMsgServiceCountSwitch != null && groupSendMsgServiceCountSwitch) {
            try {
                count = serviceManager.getGroupSendMsgServiceCount(startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getGroupSendMsgServiceCount error. startTime[{}], endTime[{}]", startTime, endTime, e);
            }
        }
        result.put(groupSendMsgServiceCountKey, count) ;
        logger.info("[{}] = [{}]", groupSendMsgServiceCountKey, count);


        // 群发图文消息篇数
        String groupSendImageTextMsgCountKey = CenterConstants.SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_COUNT;
        Boolean groupSendImageTextMsgCountSwitch = switchMap.get(groupSendImageTextMsgCountKey);
        count = -1L;
        if (groupSendImageTextMsgCountSwitch != null && groupSendImageTextMsgCountSwitch) {
            try {
                count = serviceManager.getGroupSendImageTextMsgCount(startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getGroupSendImageTextMsgCount error. startTime[{}], endTime[{}]", startTime, endTime, e);
            }
        }
        result.put(groupSendImageTextMsgCountKey, count) ;
        logger.info("[{}] = [{}]", groupSendImageTextMsgCountKey, count);

        // 群发图文消息阅读数
        String groupSendImageTextMsgReadCountKey = CenterConstants.SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_READ_COUNT;
        Boolean groupSendImageTextMsgReadCountSwitch = switchMap.get(groupSendImageTextMsgReadCountKey);
        count = -1L;
        if (groupSendImageTextMsgReadCountSwitch != null && groupSendImageTextMsgReadCountSwitch) {
            try {
                count = serviceManager.getGroupSendImageTextMsgReadCount(startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getGroupSendImageTextMsgReadCount error. startTime[{}], endTime[{}]", startTime, endTime, e);
            }
        }
        result.put(groupSendImageTextMsgReadCountKey, count) ;
        logger.info("[{}] = [{}]", groupSendImageTextMsgReadCountKey, count);

        List<MessageSendTypeEnum> administratorReplyMsgSendTypeEnumList = new ArrayList<>();
        administratorReplyMsgSendTypeEnumList.add(MessageSendTypeEnum.ADMINISTRATOR_REPLY);
        // 客服（人工回复）消息数
        String serviceHumanReplyCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_COUNT;
        Boolean serviceHumanReplyCountSwitch = switchMap.get(serviceHumanReplyCountKey);
        count = -1L;
        if (serviceHumanReplyCountSwitch != null && serviceHumanReplyCountSwitch) {
            try {
                count = serviceManager.countReplyMsgNum(administratorReplyMsgSendTypeEnumList, startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.countReplyMsgNum error. administratorReplyMsgSendTypeEnumList[{}], startTime[{}], endTime[{}]",
                        administratorReplyMsgSendTypeEnumList, startTime, endTime, e);
            }
        }
        result.put(serviceHumanReplyCountKey, count) ;
        logger.info("[{}] = [{}]", serviceHumanReplyCountKey, count);

        // 客服（人工回复）消息服务号数
        String serviceHumanReplyServiceCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_SERVICE_COUNT;
        Boolean serviceHumanReplyServiceCountSwitch = switchMap.get(serviceHumanReplyServiceCountKey);
        count = -1L;
        if (serviceHumanReplyServiceCountSwitch != null && serviceHumanReplyServiceCountSwitch) {
            try {
                count = serviceManager.countReplyMsgService(administratorReplyMsgSendTypeEnumList, startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.countReplyMsgService error. administratorReplyMsgSendTypeEnumList[{}], startTime[{}], endTime[{}]",
                        administratorReplyMsgSendTypeEnumList, startTime, endTime, e);
            }
        }
        result.put(serviceHumanReplyServiceCountKey, count) ;
        logger.info("[{}] = [{}]", serviceHumanReplyServiceCountKey, count);

        List<MessageSendTypeEnum> autoReplyMsgSendTypeEnumList = new ArrayList<>();
        autoReplyMsgSendTypeEnumList.add(MessageSendTypeEnum.DEFAULT_AUTO_REPLY);
        autoReplyMsgSendTypeEnumList.add(MessageSendTypeEnum.KEYWORDS_AUTO_REPLY);
        // 客服（自动回复）消息数
        String serviceAutoReplyCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_AUTO_REPLY_COUNT;
        Boolean serviceAutoReplyCountSwitch = switchMap.get(serviceAutoReplyCountKey);
        count = -1L;
        if (serviceAutoReplyCountSwitch != null && serviceAutoReplyCountSwitch) {
            try {
                count = serviceManager.countReplyMsgNum(autoReplyMsgSendTypeEnumList, startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.countReplyMsgNum error. autoReplyMsgSendTypeEnumList[{}], startTime[{}], endTime[{}]",
                        autoReplyMsgSendTypeEnumList, startTime, endTime, e);
            }
        }
        result.put(serviceAutoReplyCountKey, count) ;
        logger.info("[{}] = [{}]", serviceAutoReplyCountKey, count);

        // 客服（自动回复）消息服务号数
        String serviceAutoReplyServiceCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_AUTO_REPLY_SERVICE_COUNT;
        Boolean serviceAutoReplyServiceCountSwitch = switchMap.get(serviceAutoReplyServiceCountKey);
        count = -1L;
        if (serviceAutoReplyServiceCountSwitch != null && serviceAutoReplyServiceCountSwitch) {
            try {
                count = serviceManager.countReplyMsgService(autoReplyMsgSendTypeEnumList, startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.countReplyMsgService error. autoReplyMsgSendTypeEnumList[{}], startTime[{}], endTime[{}]",
                        autoReplyMsgSendTypeEnumList, startTime, endTime, e);
            }
        }
        result.put(serviceAutoReplyServiceCountKey, count) ;
        logger.info("[{}] = [{}]", serviceAutoReplyServiceCountKey, count);


        // 新增服务号数
        String newServiceCountKey = CenterConstants.SERVICE_STATISTIC_NEW_SERVICE_COUNT;
        Boolean newServiceCountSwitch = switchMap.get(newServiceCountKey);
        count = -1L;
        if (newServiceCountSwitch != null && newServiceCountSwitch) {
            try {
                count = serviceManager.getNewServiceCount(startTime, endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getNewServiceCount error. startTime[{}], endTime[{}]",
                        startTime, endTime, e);
            }
        }
        result.put(newServiceCountKey, count) ;
        logger.info("[{}] = [{}]", newServiceCountKey, count);

        // 服务号总数(根据截止时间统计)
        String serviceCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_COUNT;
        Boolean serviceCountSwitch = switchMap.get(serviceCountKey);
        count = -1L;
        if (serviceCountSwitch != null && serviceCountSwitch) {
            try {
                count = serviceManager.getServiceCount(endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getServiceCount error. startTime[{}], endTime[{}]",
                        startTime, endTime, e);
            }
        }
        result.put(serviceCountKey, count) ;
        logger.info("[{}] = [{}]", serviceCountKey, count);

        // 服务号企业总数(根据截止时间统计)
        String serviceEaCountKey = CenterConstants.SERVICE_STATISTIC_SERVICE_EA_COUNT;
        Boolean serviceEaCountSwitch = switchMap.get(serviceEaCountKey);
        count = -1L;
        if (serviceEaCountSwitch != null && serviceEaCountSwitch) {
            try {
                count = serviceManager.getServiceEaCount(endTime);
            } catch (Exception e) {
                logger.error("serviceManager.getServiceEaCount error.  endTime[{}]", endTime, e);
            }
        }
        result.put(serviceEaCountKey, count) ;
        logger.info("[{}] = [{}]", serviceEaCountKey, count);

        return new AjaxResult(result);
    }

    @RequestMapping("/createDevService")
    @ResponseBody
    public AjaxResult createDevService(OpenDevAppVO appForm) {
        //基础信息
        checkParam(appForm, "请填写表单");
        checkParam(appForm.getAppCreater(), "没有开发者");
        checkParam(appForm.getLogoIconFile(), "图标不能为空");
        checkParam(appForm.getAppName(), "名称不能为空");
        checkParam(appForm.getAppDesc(), "功能介绍不能为空");
        checkParam(appForm.getIsRecommendedApp(), "是否为推荐服务号不能为空");
        checkParam(appForm.getIsOfficialApp(), "是否为官方服务号不能为空");
        checkParam(appForm.getAppType(), "类型不能为空");
        if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() != appForm.getAppType()  &&
                AppCenterEnum.AppType.EXT_SERVICE_APP.value() != appForm.getAppType()) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "类型必须为基础服务号或扩展服务号");
        }

        //开发信息
        checkParam(appForm.getCallBackDomain(), "登录授权发起页域名不能为空");
        checkParam(appForm.getCallBackMsgUrl(), "消息与事件接收URL不能为空");

        checkParam(appForm.getToken(), "服务号token不能为空");
        if (!appForm.getToken().matches("[a-z0-9A-Z]{3,32}")) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "服务号不符合逻辑加密密钥");
        }

        checkParam(appForm.getEncodingAesKey(), "服务号加密密钥不能为空");
        if (!EncodingAesKeyUtil.isValidEncodingAesKey(appForm.getEncodingAesKey())) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "服务号不符合逻辑加密密钥");
        }

        if (null == appForm.getScopeGroup() || 1 > appForm.getScopeGroup().length) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "至少有一个权限");
        }

        if (appForm.getAppComponents().size() > 1) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "不需要添加组件");
        }

        //默认信息
        appForm.setNeedAuth(1);                             //默认为不需要授权
        appForm.setPayType(PayTypeEnum.FREE.getPayType());  //默认为免费
        appForm.setAppClass(4);                             //默认为**纷享提供**.

        // 验证是否有重名服务号
        if(appManager.existsDevAppName(appForm.getAppName(), appForm.getAppType())) {
            throw new BizException(AjaxCode.PARAM_ERROR, "服务号名称已经存在");
        }

        appManager.createDevApp(appForm);
        return SUCCESS;
    }

    @RequestMapping("/updateDevAppBase")
    @ResponseBody
    public AjaxResult updateDevAppBase(OpenDevAppVO appForm) {
        checkParam(appForm, "请填写表单");
        checkParam(appForm.getAppId(), "请选择应用");
        checkParam(appForm.getAppCreater(), "没有开发者");
        checkParam(appForm.getAppName(), "应用名称不能为空");
        checkParam(appForm.getAppDesc(), "功能介绍不能为空");
        checkParam(appForm.getIsRecommendedApp(), "是否为推荐服务号不能为空");
        checkParam(appForm.getIsOfficialApp(), "是否为官方服务号不能为空");

        if (!appManager.checkAppName(appForm.getAppId(), appForm.getAppName())) {
            throw new BizException(AjaxCode.PARAM_ERROR, "服务号名称已经存在");
        }
        appManager.updateDevAppBase(appForm);
        return SUCCESS;
    }

    @RequestMapping("/updateAppScopes")
    @ResponseBody
    public AjaxResult updateAppScopes(@RequestBody OpenAppScopeVO openAppScopeVO) {
        checkParam(openAppScopeVO, "请填写表单");
        checkParam(openAppScopeVO.getAppId(), "请选择服务号");
        checkParam(openAppScopeVO.getAppScopeVOList(), "没有选择权限");
        appManager.updateAppScopes(openAppScopeVO, ScopeOwnerEnum.SERVICE);
        return SUCCESS;
    }

    /**
     * 分页查询服务号
     *
     * @param pager
     * @param appType
     * @param text：搜索关键字
     * @return
     */
    @RequestMapping("/queryPager")
    @ResponseBody
    public AjaxResult queryPager(Pager<OpenDevAppVO> pager,
                                 @RequestParam(value = "devId", required = false) String devId,
                                 @RequestParam(value = "appType", required = false) Integer appType,
                                 @RequestParam(value = "text", required = false) String text) {
        if (null == pager) {
            pager = new Pager<OpenDevAppVO>();
        }

        if (null != appType) {
            if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() != appType  && AppCenterEnum.AppType.EXT_SERVICE_APP.value() != appType) {
                return new AjaxResult(AjaxCode.PARAM_ERROR, "类型必须为基础服务号或扩展服务号");
            }
            pager.addParam("appTypeList", Arrays.asList(appType));
        } else {
            pager.addParam("appTypeList", Arrays.asList(AppCenterEnum.AppType.BASE_SERVICE_APP.value(), AppCenterEnum.AppType.EXT_SERVICE_APP.value()));    //基础服务号+扩展服务号
        }

        if (StringUtils.isNotBlank(devId)) {
            pager.addParam("devId", devId);
        }

        if (StringUtils.isNotBlank(text)) {
            if (text.matches("FSAID_[a-z0-9A-z]+")) {
                pager.addParam("appId", text);
            } else {
                pager.addParam("appName", text);
            }
        }
        Pager<OpenDevAppVO> result = serviceManager.queryServiceByDev(pager);
        result.setParams(null);
        return new AjaxResult(result);
    }

    /**
     * 查询所有的基础服务号和扩展服务号，用于应用关联服务号
     * @return
     */
    @RequestMapping("/queryServices")
    @ResponseBody
    public AjaxResult queryServices() {
        // appTypeList
        List<AppCenterEnum.AppType> appTypeList = new ArrayList<AppCenterEnum.AppType>();
        appTypeList.add(AppCenterEnum.AppType.BASE_SERVICE_APP);
        appTypeList.add(AppCenterEnum.AppType.EXT_SERVICE_APP);

        List<OpenAppDO> services = serviceManager.queryServicesByAppTypes(appTypeList);

        Map<String, Object> result = new HashMap<>();
        result.put("services", services);
        return new AjaxResult(result);
    }
}
