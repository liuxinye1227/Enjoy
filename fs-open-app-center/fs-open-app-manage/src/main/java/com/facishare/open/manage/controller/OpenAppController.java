package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;
import com.facishare.open.app.center.api.model.enums.VisibleEnum;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import com.facishare.open.app.center.api.service.PayAppService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.common.utils.EncodingAesKeyUtil;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.manager.AppManager;
import com.facishare.open.manage.manager.AppVisibleManager;
import com.facishare.open.manage.model.*;
import com.facishare.open.oauth.model.enums.ScopeOwnerEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * 应用管理接口
 *
 * @author zenglb
 * @date 2015年9月30日
 */
@Controller
@RequestMapping("/open/manage/rest/openApp")
public class OpenAppController extends BaseController {

    @Autowired
    private AppManager appManager;
    @Autowired
    private AppVisibleManager appVisibleManager;
    @Autowired
    private PayAppService payAppService;

    /**
     * 分页查询应用,并显示开启数
     *
     * @param pager
     * @param devId
     * @param appClass
     * @param text
     * @return
     */
    @RequestMapping("/queryPager")
    @ResponseBody
    public AjaxResult queryPager(Pager<OpenDevAppVO> pager,
                                 @RequestParam(value = "devId", required = false) String devId,
                                 @RequestParam(value = "appClass", required = false) String appClass,
                                 @RequestParam(value = "text", required = false) String text) {
        if (null == pager) {
            pager = new Pager<OpenDevAppVO>();
        }
        if (StringUtils.isNotBlank(appClass)) {
            pager.addParam("appClass", appClass);
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
        Pager<OpenDevAppVO> result = appManager.queryOpenAppByDev(pager);
        result.setParams(null);
        return new AjaxResult(result);
    }

    @RequestMapping("/listScope")
    @ResponseBody
    public AjaxResult listScope() {
        return new AjaxResult(appManager.listScope(ScopeOwnerEnum.APP));
    }

    @RequestMapping("/listAppClass")
    @ResponseBody
    public AjaxResult listAppClass() {
        return new AjaxResult(appManager.listAppClass());
    }

    @RequestMapping("/listAppLabel")
    @ResponseBody
    public AjaxResult listAppLabel() {
        return new AjaxResult(appManager.listAppLabel());
    }

    @RequestMapping("/generateToken")
    @ResponseBody
    public AjaxResult generateToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return new AjaxResult(token);
    }

    @RequestMapping("/generateEncodingAesKey")
    @ResponseBody
    public AjaxResult generateEncodingAesKey() {
        return new AjaxResult(EncodingAesKeyUtil.generateEncodingAesKey());
    }

    @RequestMapping("/loadAppByAppId")
    @ResponseBody
    public AjaxResult loadAppByDevIdAndAppId(@RequestParam(value = "appId", required = false) String appId) {
        checkParam(appId, "请选择应用");
        OpenDevAppVO app = appManager.loadOpenApp(appId);
        return new AjaxResult(app);
    }

    @RequestMapping("/deleteDevApp")
    @ResponseBody
    public AjaxResult deleteDevApp(@RequestBody OpenAppWithAppIdDevIdVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getDevId(), "请选择开发者");
        checkParam(form.getAppId(), "请选择应用");
        appManager.deleteDevApp(form.getDevId(), form.getAppId());
        return SUCCESS;
    }

    @RequestMapping("/applyForOnline")
    @ResponseBody
    public AjaxResult applyForOnline(@RequestBody OpenAppWithAppIdDevIdVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getDevId(), "请选择开发者");
        checkParam(form.getAppId(), "请选择应用");
        checkParam(form.getVisibleStatus() + "","[12]{1}","请选择发布的范围类型");
        if (VisibleEnum.PART.getCode() == form.getVisibleStatus() && CollectionUtils.isEmpty(form.getFsEas())) {
            return  new AjaxResult(AjaxCode.PARAM_ERROR,"请选择至少一个企业");
        }
        appManager.applyForOnline(form.getDevId(), form.getAppId());
        appVisibleManager.save(form.getAppId(),VisibleEnum.valueByCode(form.getVisibleStatus()),form.getFsEas());
        return SUCCESS;
    }

    @RequestMapping("/createDevApp")
    @ResponseBody
    public AjaxResult createDevApp(OpenDevAppVO appForm) {
        checkParam(appForm, "请填写表单");
        checkParam(appForm.getAppCreater(), "没有开发者");
        checkParam(appForm.getAppName(), "应用名称不能为空");
        checkParam(appForm.getAppDesc(), "应用描述不能为空");
        checkParam(appForm.getCallBackMsgUrl(), "应用回调地址不能为空");
        checkParam(appForm.getEncodingAesKey(), "应用加密密钥不能为空");
        checkParam(appForm.getPayType(), "应用付费类型不能为空");
        //todo 增加试用类型
        //todo 修改付费类型的分类
        checkParam(PayTypeEnum.getByPayType(appForm.getPayType()), "应用付费类型不能为空");
        if (!EncodingAesKeyUtil.isValidEncodingAesKey(appForm.getEncodingAesKey())) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "应用不符合逻辑加密密钥");
        }
        checkParam(appForm.getToken(), "应用token不能为空");
        if (!appForm.getToken().matches("[a-z0-9A-Z]{3,32}")) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "应用不符合逻辑加密密钥");
        }
        checkParam(appForm.getAppClass(), "应用类别不能为空");
        //默认设置为第三方应用，兼容老接口
        if (null == appForm.getAppType()){
            appForm.setAppType(AppCenterEnum.AppType.DEV_APP.value());
        }
        checkParam(appForm.getLogoIconFile(), "应用logo不能为空");
        if (appForm.getAppComponents().size() < 1) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "至少添加一个组件");
        }
        if (null == appForm.getScopeGroup() || 1 > appForm.getScopeGroup().length) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "至少有一个权限");
        }

        // 验证是否有重名应用
        if(appManager.existsDevAppName(appForm.getAppName(), appForm.getAppType())) {
            throw new BizException(AjaxCode.PARAM_ERROR, "应用名称已经存在");
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
        checkParam(appForm.getAppDesc(), "应用描述不能为空");
        checkParam(appForm.getAppClass(), "应用类型不能为空");
        checkParam(appForm.getPayType(), "应用付费类型不能为空");
        checkParam(PayTypeEnum.getByPayType(appForm.getPayType()), "应用付费类型不能为空");

        if (!appManager.checkAppName(appForm.getAppId(), appForm.getAppName())) {
            throw new BizException(AjaxCode.PARAM_ERROR, "应用名称已经存在");
        }

        appManager.updateDevAppBase(appForm);
        return SUCCESS;
    }

    @RequestMapping("/updateDevAppDev")
    @ResponseBody
    public AjaxResult updateDevAppDev(OpenDevAppVO appForm) {
        checkParam(appForm, "请填写表单");
        checkParam(appForm.getAppId(), "请选择应用");
        checkParam(appForm.getAppCreater(), "没有开发者");
        checkParam(appForm.getToken(), "应用token不能为空");
        checkParam(appForm.getEncodingAesKey(), "应用加密密钥不能为空");
        if (!appForm.getToken().matches("[a-z0-9A-Z]{3,32}")) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "应用token不符合逻辑");
        }
        if (!EncodingAesKeyUtil.isValidEncodingAesKey(appForm.getEncodingAesKey())) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "应用加密密钥不符合逻辑");
        }
        checkParam(appForm.getCallBackDomain(), "应用域名不能为空");
        checkParam(appForm.getCallBackMsgUrl(), "应用消息地址不能为空");

        appManager.updateDevAppDev(appForm);
        return SUCCESS;
    }

    @RequestMapping("/createAppComponent")
    @ResponseBody
    public AjaxResult createAppComponent(OpenAppComponentVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getAppId(), "应用ID不能为空");
        checkParam(form.getAppType(), "应用类型不能为空");
        checkParam(form.getComponentName(), "组件名称不能为空");
        checkParam(form.getComponentDesc(), "组件描述不能为空");
        checkParam(form.getComponentType(), "组件类型不能为空");
        checkParam(form.getLoginUrl(), "组件地址不能为空");
        if (AppComponentTypeEnum.APP.getType() == form.getComponentType()) {
            checkParam(form.getComponentLabel(), "应用组件标签不能为空");
        } else {
            form.setComponentLabel(CommonConstant.APP_LABEL_OTHER);
        }
        checkParam(form.getLogoIconFile(), "应用组件logo不能为空");
        appManager.createAppComponent(form.getAppId(), form.getAppType(), form);
        return SUCCESS;
    }

    @RequestMapping("/updateAppComponent")
    @ResponseBody
    public AjaxResult updateAppComponent(OpenAppComponentVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getAppId(), "请选择应用");
        checkParam(form.getComponentId(), "组件id不能为空");
        checkParam(form.getComponentName(), "组件名称不能为空");
        checkParam(form.getComponentDesc(), "组件描述不能为空");
        checkParam(form.getComponentType(), "组件类型不能为空");
        checkParam(form.getLoginUrl(), "组件地址不能为空");
        if (AppComponentTypeEnum.APP.getType() == form.getComponentType()) {
            checkParam(form.getComponentLabel(), "应用组件标签不能为空");
        } else {
            form.setComponentLabel(CommonConstant.APP_LABEL_OTHER);
        }
        appManager.updateAppComponent(form.getComponentId(), form);
        return SUCCESS;
    }

    @RequestMapping("/loadAppComponentById")
    @ResponseBody
    public AjaxResult loadAppComponentById(@RequestParam(required = false, value = "componentId") String componentId) {
        checkParam(componentId, "组件id不能为空");
        OpenAppComponentVO data = appManager.loadAppComponentById(componentId);
        return new AjaxResult(data);
    }

    @RequestMapping("/updateAppPayStatus")
    @ResponseBody
    public AjaxResult updateAppPayStatus(@RequestParam(value = "appId", required = false) String appId,
                                         @RequestParam(value = "payType", required = false) Integer payType) {
        checkParam(appId, "请选择应用");
        checkParam(payType, "请设置类型");
        PayTypeEnum payTypeEnum = PayTypeEnum.getByPayType(payType);
        checkParam(payTypeEnum, "请设置有效的类型");
        payAppService.updatePayApp(appId, payTypeEnum);
        return SUCCESS;
    }

    @RequestMapping("/saveOpenComponentUrlGray")
    @ResponseBody
    public AjaxResult saveOpenComponentUrlGray(@RequestBody OpenComponentUrlGrayVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getComponentId(), "请选择组件");
        checkParam(form.getAppId(), "请选择应用");
        appManager.saveOpenComponentUrlGray(form);
        return SUCCESS;
    }

    @RequestMapping("/loadOpenComponentUrlGrayById")
    @ResponseBody
    public AjaxResult loadOpenComponentUrlGrayById(@RequestParam(value = "componentId", required = false) String componentId) {
        checkParam(componentId, "请选择组件");
        OpenComponentUrlGrayVO openComponentUrlGrayVO = appManager.loadOpenComponentUrlGrayById(componentId);
        return new AjaxResult(openComponentUrlGrayVO);
    }

    @RequestMapping("/deleteAppComponentById")
    @ResponseBody
    public AjaxResult deleteAppComponentById(@RequestBody OpenAppComponentVO openAppComponentVO) {
        checkParam(openAppComponentVO, "请选择组件");
        checkParam(openAppComponentVO.getComponentId(), "组件id不能为空");
        appManager.deleteAppComponentById(openAppComponentVO.getComponentId());
        return SUCCESS;
    }

    @RequestMapping("/updateAppScopes")
    @ResponseBody
    public AjaxResult updateAppScopes(@RequestBody OpenAppScopeVO openAppScopeVO) {
        checkParam(openAppScopeVO, "请填写表单");
        checkParam(openAppScopeVO.getAppId(), "请选择应用");
        checkParam(openAppScopeVO.getAppScopeVOList(), "没有选择权限");
        appManager.updateAppScopes(openAppScopeVO, ScopeOwnerEnum.APP);
        return SUCCESS;
    }

    @RequestMapping("/updateAppStatus")
    @ResponseBody
    public AjaxResult updateAppStatus(@RequestParam(value = "appId", required = false) String appId, @RequestParam
            (value = "status", required = false) Integer status) {
        if (StringUtils.isBlank(appId) || null == status ){
            return new AjaxResult(AjaxCode.PARAM_ERROR, "appId，status不能为空!");
        }
        OpenAppDO openAppDO = new OpenAppDO();
        openAppDO.setAppId(appId);
        openAppDO.setStatus(status);
        if (!appManager.updateAppStatus(openAppDO)){
            return new AjaxResult(AjaxCode.PARAM_ERROR, "更新app状态失败!");
        }
        return new AjaxResult(openAppDO);
    }

    @RequestMapping("/addServiceBind")
    @ResponseBody
    public AjaxResult addServiceBind(@RequestBody AppServiceRefVO appServiceRefVO) {
        if (appServiceRefVO ==null ||
                StringUtils.isBlank(appServiceRefVO.getAppId()) ||
                StringUtils.isBlank(appServiceRefVO.getServiceId())){
            return new AjaxResult(AjaxCode.PARAM_ERROR, "appId，serviceId 不能为空!");
        }
        appManager.addServiceBind(appServiceRefVO.getAppId(), appServiceRefVO.getServiceId());
        return SUCCESS;
    }

    @RequestMapping("/deleteServiceBind")
    @ResponseBody
    public AjaxResult deleteServiceBind(@RequestBody AppServiceRefVO appServiceRefVO) {
        if (appServiceRefVO ==null ||
                StringUtils.isBlank(appServiceRefVO.getAppId()) ||
                StringUtils.isBlank(appServiceRefVO.getServiceId())){
            return new AjaxResult(AjaxCode.PARAM_ERROR, "appId，serviceId 不能为空!");
        }
        appManager.deleteServiceBind(appServiceRefVO.getAppId(), appServiceRefVO.getServiceId());
        return SUCCESS;
    }

    @RequestMapping("/modifyServiceBind")
    @ResponseBody
    public AjaxResult modifyServiceBind(@RequestBody AppServiceRefVO appServiceRefVO) {
        if (appServiceRefVO ==null ||
                StringUtils.isBlank(appServiceRefVO.getAppId()) ||
                StringUtils.isBlank(appServiceRefVO.getServiceId()) ||
                StringUtils.isBlank(appServiceRefVO.getNewServiceId()) ){
            return new AjaxResult(AjaxCode.PARAM_ERROR, "appId，serviceId，newServiceId 不能为空!");
        }
        appManager.modifyServiceBind(appServiceRefVO.getAppId(), appServiceRefVO.getServiceId(), appServiceRefVO.getNewServiceId());
        return SUCCESS;
    }
}
