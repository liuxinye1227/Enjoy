package com.facishare.open.manage.manager.impl;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import com.facishare.open.app.center.api.model.OpenDictDO;
import com.facishare.open.app.center.api.model.enums.*;
import com.facishare.open.app.center.api.model.property.OpenAppProperties;
import com.facishare.open.app.center.api.model.vo.IconFileVO;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import com.facishare.open.app.center.api.result.*;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.service.*;
import com.facishare.open.appaccesscontrol.result.AppIpWhiteListResult;
import com.facishare.open.appaccesscontrol.service.AppIpWhiteListService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.manager.AppManager;
import com.facishare.open.manage.manager.AppVisibleManager;
import com.facishare.open.manage.model.AppScopeVO;
import com.facishare.open.manage.model.OpenAppComponentVO;
import com.facishare.open.manage.model.OpenAppScopeVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.manage.utils.BizCommonUtils;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.oauth.model.AppDO;
import com.facishare.open.oauth.model.AppServiceRefDO;
import com.facishare.open.oauth.model.ScopeDO;
import com.facishare.open.oauth.model.arg.AppArg;
import com.facishare.open.oauth.model.enums.AppTypeEnum;
import com.facishare.open.oauth.model.enums.ScopeOwnerEnum;
import com.facishare.open.oauth.result.AppListResult;
import com.facishare.open.oauth.result.*;
import com.facishare.open.oauth.service.AppService;
import com.facishare.open.oauth.service.AppServiceRefService;
import com.facishare.open.oauth.service.ScopeService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangwz on 2015/9/15.
 */
@Service
public class AppManagerImpl implements AppManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppService appService;
    @Autowired
    private OpenAppService openAppService;
    @Autowired
    private OpenAppScopeOrderService openAppScopeOrderService;
    @Autowired
    private AppIconService appIconService;
    @Autowired
    private ScopeService scopeService;
    @Autowired
    private OpenDictService openDictService;
    @Autowired
    private OpenAppComponentService openAppComponentService;
    @Autowired
    private AppVisibleManager appVisibleManager;
    @Autowired
    private OpenComponentUrlGrayService openComponentUrlGrayService;
    @Autowired
    private OpenComponentLoginUrlService openComponentLoginUrlService;
    @Autowired
    private AppIpWhiteListService appIpWhiteListService;
    @Autowired
    private AppServiceRefService appServiceRefService;

    @Value("${fs.open.app.center.image.url}")
    private String imageUrl;

    @SuppressWarnings("all")
    private void processIcon(String appId, IconType type, IconFileVO icon) {
        // 目前同一张图像适用于所有类型
        List<IconType> types = Arrays.asList(type);

        // 目前裁剪方案为取短边，裁剪中间
        int width = icon.getWidth();
        int height = icon.getHeight();
        // 取短边
        int value = width < height ? width : height;
        double w = (double) width;
        double h = (double) height;
        int x = (int) Math.round((w - value) / 2);
        int y = (int) Math.round((h - value) / 2);
        Rectangle rectangle = new Rectangle(x, y, value, value);

        BaseResult result = appIconService.update(appId, types, icon.getData(), icon.getExt(), rectangle);
        if (!result.isSuccess()) {
            logger.warn("Add icon fail. result: {}.", result);
        }
    }

    @Override
    public OpenDevAppVO loadOpenApp(String appId) {
        AppResult appResult = openAppService.loadOpenApp(appId);
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在");
        }
        //OpenAppDO=> OpenDevAppVO
        OpenDevAppVO result = new OpenDevAppVO();
        OpenAppDO openApp = appResult.getResult();
        result.setAppCreater(openApp.getAppCreater());
        if (null != openApp.getOpenDevDO()) {
            result.setDevName(openApp.getOpenDevDO().getDevName());
        }
        result.setGmtCreate(openApp.getGmtCreate());
        result.setAppId(openApp.getAppId());
        result.setAppDesc(openApp.getAppDesc());
        result.setAppName(openApp.getAppName());
        Integer appType = openApp.getAppType();
        result.setAppType(appType);
        if (AppCenterEnum.AppType.DEV_APP.value() == appType) {
            result.setAppTypeName("扩展应用");
        } else if (AppCenterEnum.AppType.BASE_APP.value() == appType) {
            result.setAppTypeName("基础应用");
        } else if (AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appType) {
            result.setAppTypeName("扩展服务号");
        } else if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appType) {
            result.setAppTypeName("基础服务号");
        }
        result.setStatus(openApp.getStatus());
        result.setAppLogoUrl(queryAppIconUrl(openApp.getAppId(), IconType.WEB));
        DictResult dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_STATUS, "" + openApp.getStatus());
        result.setStatusName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
        result.setAppClass(openApp.getAppClass());
        dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_CLASS, "" + openApp.getAppClass());
        result.setAppClassName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
        result.setPayType(openApp.getPayType());
        if (openApp.getTryType() == null) {
            result.setTryType(TryTypeEnum.NOT_TRY.getCode());
        } else {
            result.setTryType(openApp.getTryType());
        }

        PayTypeEnum payTypeEnum = PayTypeEnum.getByPayType(openApp.getPayType());
        if (null != payTypeEnum) {
            result.setPayTypeName(payTypeEnum.getTypeDesc());
        }

        OpenAppProperties openAppProperties = OpenAppProperties.fromJson(openApp.getProperties());

        if (openAppProperties == null) {
            openAppProperties = new OpenAppProperties();
        }

        if (!CollectionUtils.isEmpty(openAppProperties.getDetailImageUrl())) {
            List<String> collect = openAppProperties.getDetailImageUrl().stream().map(s -> {

                if (s.contains("group1/M00") || s.contains("group0/M00")) { //老图片系统
                    return imageUrl + s;
                } else {
                    return ConfigCenter.BJ_IMAGE_VIEW_URL + s;
                }
            })
                    .collect(Collectors.toList());

            result.setDetailImageUrls(collect);
        }

        result.setAppIntro(openAppProperties.getAppIntro());
        result.setNeedAuth(openAppProperties.getNeedAuth() == null ? 2 : openAppProperties.getNeedAuth());
        result.setIsOfficialApp(
                openAppProperties.getIsOfficialApp() == null ? 2 : openAppProperties.getIsOfficialApp());
        result.setIsRecommendedApp(openAppProperties.getIsRecommendedApp() == null ? 2 : openAppProperties.getIsRecommendedApp());
        result.setPayDesc(openAppProperties.getPayDesc());
        result.setTrialDays(openAppProperties.getTrialDays());
        result.setPurchaseOnline(openAppProperties.getPurchaseOnline());

        //获取 oauth信息
        com.facishare.open.oauth.result.AppResult app = appService.getAppInfo(null, null, appId);
        if (!app.isSuccess()) {
            logger.warn("app not in oauth : " + openApp.getAppId());
            throw new BizException(AjaxCode.BIZ_EXCEPTION, app, "应用不存在");
        }
        result.setAppSecret(app.getAppSecret());
        result.setCallBackDomain(app.getCallBackDomain());
        result.setCallBackMsgUrl(app.getCallBackMsgUrl());
        result.setToken(app.getToken());
        result.setEncodingAesKey(app.getEncodingAesKey());
        OpenAppScopeOrderListResult openAppScopeOrderListResult = openAppScopeOrderService.getAppScopeByAppId(appId);
        if (openAppScopeOrderListResult.isSuccess()) {
            result.setScopes(openAppScopeOrderListResult.getResult());
        }

        AppIpWhiteListResult appIpWhiteListResult = appIpWhiteListService.getAppIpWhiteList(openApp.getAppId());
        if (appIpWhiteListResult.isSuccess()) {
            result.setIpWhiteList(appIpWhiteListResult.getIpList());
        } else {
            result.setIpWhiteList(null);
            logger.warn("getAppIpWhiteList failed, appId[{}], appIpWhiteListResult[{}]", openApp.getAppId(), appIpWhiteListResult);
        }

        //获取应用所有组件
        BaseResult<List<OpenAppComponentDO>> openComponentsResult = openAppComponentService.queryAllStatusComponentListByAppId(null, appId);
        if (!openComponentsResult.isSuccess()) {
            logger.warn("failed to call loadOpenApp, appId={}, result={}",
                    appId, openComponentsResult.toString());
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "加载应用的组件列表错误");
        }
        //转换组件
        List<OpenAppComponentVO> componentVOList = new ArrayList<OpenAppComponentVO>();
        Map<String, OpenAppComponentVO> voIndex = new HashMap<>();
        List<String> componentIds = new ArrayList<String>();
        OpenAppComponentVO vo = null;


        if(!CollectionUtils.isEmpty(openComponentsResult.getResult())){
            for (OpenAppComponentDO appComponentDO : openComponentsResult.getResult()) {
                vo = new OpenAppComponentVO();
                vo.setAppId(appComponentDO.getAppId());
                vo.setComponentId(appComponentDO.getComponentId());
                vo.setComponentName(appComponentDO.getComponentName());
                vo.setComponentLabel(appComponentDO.getComponentLabel());
                dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_LABEL, "" + appComponentDO.getComponentLabel());
                vo.setComponentLabelName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
                vo.setComponentType(appComponentDO.getComponentType());
                vo.setStatus(appComponentDO.getStatus());
                vo.setComponentDesc(appComponentDO.getComponentDesc());
                vo.setGmtCreate(appComponentDO.getGmtCreate());
                vo.setGmtModified(appComponentDO.getGmtModified());
                vo.setComponentLogoUrl(queryAppIconUrl(appComponentDO.getComponentId(), IconType.WEB));

                //获取组件灰度地址
                BaseResult<OpenComponentUrlGrayVO> baseResult = openComponentUrlGrayService.queryByComponentId(null, appComponentDO.getComponentId());
                if (!baseResult.isSuccess()) {
                    logger.warn("openComponentUrlGrayService.queryByComponentId faild, componentId={}, result={}",
                            appComponentDO.getComponentId(), baseResult.toString());
                    throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取组件灰度地址错误");
                }
                vo.setOpenComponentUrlGrayVO(baseResult.getResult());
                componentVOList.add(vo);
                componentIds.add(appComponentDO.getComponentId());
                voIndex.put(appComponentDO.getComponentId(), vo);
            }
            //加载组件的跳转地址
            AppListResult infoBatchResult = appService.getComponentInfoBatch(null, null, componentIds);
            if (!infoBatchResult.isSuccess()) {
                logger.warn("getComponentInfoBatch failed, appId={}, result={}",
                        appId, infoBatchResult.toString());
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "加载应用的组件列表错误");
            }
            for (AppDO appDO : infoBatchResult.getAppList()) {
                vo = voIndex.get(appDO.getAppId());
                if (null != vo) {
                    vo.setLoginUrl(appDO.getCallBackWebLoginUrl());
                    if (AppComponentTypeEnum.APP.getType() == vo.getComponentType()) {
                        vo.setLoginUrl(appDO.getCallBackLoginUrl());
                    }
                }
            }
        }

        result.setAppComponents(componentVOList);

        //基础应用和扩展应用：获取应用绑定的服务号信息
        if (AppCenterEnum.AppType.BASE_APP.value() == appType || AppCenterEnum.AppType.DEV_APP.value() == appType) {
            GetServiceRefAppResult getServiceRefAppResult = appServiceRefService.getServiceRefAppList(appId, null);
            if (!getServiceRefAppResult.isSuccess()) {
                logger.warn("getServiceRefAppList failed, appId[{}]", appId);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取应用绑定的服务号信息错误");
            }

            List<AppServiceRefDO> appServiceRefDOs = getServiceRefAppResult.getAppServiceRefDOs();
            if (null == appServiceRefDOs || appServiceRefDOs.isEmpty()) {
                result.setHasRefService(2);   //应用没有绑定的服务号
            } else {
                result.setHasRefService(1);
                result.setRefServiceAppId(appServiceRefDOs.get(0).getServiceId());

                //查询服务号的appType
                AppResult serviceResult = openAppService.loadOpenApp(result.getRefServiceAppId());
                if (!serviceResult.isSuccess() || null == serviceResult.getResult()) {
                    throw new BizException(AjaxCode.BIZ_EXCEPTION, serviceResult, "应用关联的服务号不存在");
                }
                result.setRefServiceAppType(serviceResult.getResult().getAppType());
                result.setRefServiceAppName(serviceResult.getResult().getAppName());
            }
        }
        return result;
    }

    @Override
    public Pager<OpenAppDO> queryOpenAppByDev_ddd(Pager<OpenAppDO> pager) {
        List<Integer> statusList = new ArrayList<Integer>();
        for (AppStatus status : AppStatus.values()) {
            if (AppStatus.DELETED != status) {
                statusList.add(status.status());
            }
        }
        pager.addParam("statusList", statusList);
        AppPagerResult appPagerResult = openAppService.queryOpenAppByDev(pager);
        if (!appPagerResult.isSuccess()) {
            logger.warn(" queryOpenAppByDev error, {}", JsonKit.object2json(pager));
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appPagerResult, "查询第三方应用列表异常");
        }
        return appPagerResult.getResult();
    }

    @Override
    public Pager<OpenDevAppVO> queryOpenAppByDev(Pager<OpenDevAppVO> pager) {
        Pager<OpenAppDO> param = new Pager<OpenAppDO>();
        param.setParams(pager.getParams());
        param.setCurrentPage(pager.getCurrentPage());
        param.setPageSize(pager.getPageSize());
        List<Integer> statusList = new ArrayList<Integer>();
        for (AppStatus status : AppStatus.values()) {
            if (AppStatus.DELETED != status) {
                statusList.add(status.status());
            }
        }
        param.addParam("statusList", statusList);
        AppPagerResult appPagerResult = openAppService.queryOpenAppByDev(param);
        if (!appPagerResult.isSuccess()) {
            logger.warn(" queryOpenAppByDev error, {}", JsonKit.object2json(param));
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appPagerResult, "查询第三方应用列表异常");
        }
        Pager<OpenAppDO> pagerDevApps = appPagerResult.getResult();
        pager.setRecordSize(pagerDevApps.getRecordSize());

        if (null != pagerDevApps.getData()) {
            OpenDevAppVO appVO = null;
            List<String> appIds = pagerDevApps.getData().stream().map(OpenAppDO::getAppId).collect(Collectors.toList());
            List<String> visibleAppIds = appVisibleManager.queryAppIdVisibleConfig(appIds);
            for (OpenAppDO appDO : pagerDevApps.getData()) {
                appVO = new OpenDevAppVO();
                appVO.setAppId(appDO.getAppId());
                appVO.setAppName(appDO.getAppName());
                appVO.setAppCreater(appDO.getAppCreater());
                if (null != appDO.getOpenDevDO()) {
                    appVO.setDevName(appDO.getOpenDevDO().getDevName());
                }
                appVO.setPayType(appDO.getPayType());
                PayTypeEnum payTypeEnum = PayTypeEnum.getByPayType(appDO.getPayType());
                if (null != payTypeEnum) {
                    appVO.setPayTypeName(payTypeEnum.getTypeDesc());
                }
                appVO.setStatus(appDO.getStatus());
                DictResult dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_STATUS, "" + appDO.getStatus());
                appVO.setStatusName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
                appVO.setAppClass(appDO.getAppClass());
                dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_CLASS, "" + appDO.getAppClass());
                appVO.setAppClassName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
                appVO.setOnLineDate(appDO.getGmtCreate());
                appVO.setAppLogoUrl(queryAppIconUrl(appDO.getAppId(), IconType.WEB));

                //添加灰度
                appVO.setVisibleStatus(VisibleEnum.ALL.getCode());
                appVO.setVisibleStatusName(VisibleEnum.ALL.getName());
                if (AppStatus.ON_LINE.getStatus() != appDO.getStatus()) {
                    appVO.setVisibleStatus(VisibleEnum.NONE.getCode());
                    appVO.setVisibleStatusName(VisibleEnum.NONE.getName());
                }
                if (visibleAppIds.contains(appDO.getAppId())) {
                    appVO.setVisibleStatus(VisibleEnum.PART.getCode());
                    appVO.setVisibleStatusName(VisibleEnum.PART.getName());
                }
                pager.getData().add(appVO);
            }
        }
        return pager;
    }

    @Override
    public void deleteDevApp(String devId, String appId) {
        StatusResult deleteResult = openAppService.updateDevAppStatus(devId, appId, AppStatus.DELETED);
        if (!deleteResult.isSuccess()) {
            logger.warn("delete dev app error , devId [{}] appId[{}] result : {}", devId, appId, deleteResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, deleteResult, "删除应用失败");
        }
    }

    @Override
    public boolean checkAppName(String appId, String newAppName) {
        AppResult appResult = openAppService.loadOpenAppFast(appId);
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在");
        }
        // 只有开发者创建的类型可以重名验证（基础应用、扩展应用、基础服务号、扩展服务号）
        if (!(AppCenterEnum.AppType.BASE_APP.value() == appResult.getResult().getAppType()
                || AppCenterEnum.AppType.DEV_APP.value() == appResult.getResult().getAppType()
                || AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appResult.getResult().getAppType()
                || AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appResult.getResult().getAppType())) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用类型不正确");
        }

        // 名称重名验证
        OpenAppDO currentEntity = appResult.getResult();
        if (!currentEntity.getAppName().equals(newAppName)) {          //和原来的appName不一样
            AppCenterEnum.AppType appType = AppCenterEnum.AppType.BASE_APP;
            if (AppCenterEnum.AppType.DEV_APP.value() == currentEntity.getAppType()) {
                appType = AppCenterEnum.AppType.DEV_APP;
            } else if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == currentEntity.getAppType()) {
                appType = AppCenterEnum.AppType.BASE_SERVICE_APP;
            } else if (AppCenterEnum.AppType.EXT_SERVICE_APP.value() == currentEntity.getAppType()) {
                appType = AppCenterEnum.AppType.EXT_SERVICE_APP;
            }
            return !this.existsDevAppName(newAppName, appType.value());
        }

        return true;
    }

    @Override
    public boolean existsDevAppName(String appName, Integer appType) {
        //除了"已删除"之外的状态
        List<Integer> statusList = new ArrayList<Integer>();
        for (AppStatus status : AppStatus.values()) {
            if (AppStatus.DELETED != status) {
                statusList.add(status.status());
            }
        }

        BaseResult<Boolean> result = openAppService.existsDevAppName(appName, appType, statusList);
        if(!result.isSuccess()) {
            logger.warn("call openAppService.existsAppName fail appName[{}], appType[{}], statusList[{}], appResult[{}]",
                    appName, appType, statusList, result);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, result, "验证是否存在同名应用失败");
        }
        return result.getResult();
    }

    @Override
    public void createDevApp(OpenDevAppVO appForm) {
        IconFileVO logoIcon = null;
        try {
            logoIcon = IconFileVO.create(appForm.getLogoIconFile());
        } catch (Exception e) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "图片异常");
        }
        //oauth创建 app，返回appId
        CreateAppResult appResult = createOauthApp(appForm);
        String appId = appResult.getAppId();

        //设置应用的logo
        if (StringUtils.isNotBlank(appId) && null != logoIcon) {
            processIcon(appResult.getAppId(), IconType.LOGO, logoIcon);
        }
        //设置应用的权限
        if (null != appForm.getScopeGroup() && appForm.getScopeGroup().length > 0) {
            saveAppScopeOrder(appId, appForm.getScopeGroup(), toScopeOwn(appForm.getAppType()));
        }

        //创建应用
        OpenAppDO app = new OpenAppDO();
        app.setAppId(appResult.getAppId());
        app.setAppName(appForm.getAppName());
        app.setServiceName(appForm.getAppName());
        app.setAppDesc(appForm.getAppDesc());
        app.setAppType(appForm.getAppType());
        app.setTryType(appForm.getTryType());
        // 创建就为审核通过状态
        app.setStatus(AppStatus.AUDIT_PASS.status());

        app.setAppCreater(appForm.getAppCreater());
        app.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);

        app.setAppMode(CommonConstant.APP_MODE_COMMON);
        app.setAppClass(appForm.getAppClass());
        if (null == appForm.getAppClass()) {
            app.setAppClass(CommonConstant.APP_CLASS_ENTERPRISE);
        }
        if (null == appForm.getPayType()) {
            app.setPayType(PayTypeEnum.CHARGE.getPayType());
        } else {
            app.setPayType(appForm.getPayType());
        }
        OpenAppProperties properties = new OpenAppProperties();
        if (!CollectionUtils.isEmpty(appForm.getDetailImageUrls())) {
            List<String> collect = appForm.getDetailImageUrls().stream().map(s -> s.replace(imageUrl, "").replace(ConfigCenter.BJ_IMAGE_VIEW_URL,"")).collect(
                    Collectors.toList());
            properties.setDetailImageUrl(collect);
        }

        if (StringUtils.isNotEmpty(appForm.getAppIntro())) {
            properties.setAppIntro(appForm.getAppIntro());
        }
        if (StringUtils.isNotEmpty(appForm.getPayDesc())) {
            properties.setPayDesc(appForm.getPayDesc());
        }
        properties.setTrialDays(appForm.getTrialDays());
        properties.setPurchaseOnline(appForm.getPurchaseOnline());
        properties.setNeedAuth(appForm.getNeedAuth());
        if (appForm.getIsOfficialApp() != null) {
            properties.setIsOfficialApp(appForm.getIsOfficialApp());
        }
        if (appForm.getIsRecommendedApp() != null) {
            properties.setIsRecommendedApp(appForm.getIsRecommendedApp());
        }

        app.setProperties(properties.getJsonString());
        app.setGmtCreate(new Date());
        app.setGmtModified(new Date());
        app.setTryType(appForm.getTryType());

        AppResult createResult = openAppService.createOpenApp(app);
        if (!createResult.isSuccess()) {
            logger.error("create custom app error , result:{}", createResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createResult, createResult.getErrDescription());
        }

        //对于基础服务号和扩展服务号：创建服务号组件
        if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appForm.getAppType() ||
                AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appForm.getAppType()) {
            OpenAppComponentVO openAppComponentVOTemp = new OpenAppComponentVO();

            openAppComponentVOTemp.setLogoIconFile(appForm.getLogoIconFile());
            openAppComponentVOTemp.setLoginUrl(CommonConstant.SERVICE_LOGIN_URL);    // 和自建服务号一样
            openAppComponentVOTemp.setComponentDesc(appForm.getAppDesc());
            openAppComponentVOTemp.setComponentName(appForm.getAppName());
            openAppComponentVOTemp.setComponentType(AppComponentTypeEnum.SERVICE.getType());

            appForm.setAppComponents(Arrays.asList(openAppComponentVOTemp));
        }

        //创建应用组件
        for (OpenAppComponentVO openAppComponentVO : appForm.getAppComponents()) {
            createAppComponent(appId, app.getAppType(), openAppComponentVO);
        }
    }

    private void saveAppScopeOrder(String appId, String[] scopeGroup, ScopeOwnerEnum scopeOwn) {
        List<OpenAppScopeOrderDO> appScopeOrderDOs = new ArrayList<OpenAppScopeOrderDO>();
        List<OpenAppScopeOrderDO> scopeDOs = listScope(scopeOwn);
        for (String scope : scopeGroup) {
            for (OpenAppScopeOrderDO scopeDO : scopeDOs) {
                if (scopeDO.getScope().equals(scope)) {
                    scopeDO.setAppId(appId);
                    appScopeOrderDOs.add(scopeDO);
                    break;
                }
            }
        }
        StatusResult result = openAppScopeOrderService.saveAppScopes(appId, appScopeOrderDOs);
        if (!result.isSuccess()) {
            logger.warn("save app scope fail , appId [{}] scopes[{}]", appId, scopeGroup);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, result, "保存权限。");
        }
    }

    private CreateAppResult createOauthApp(OpenDevAppVO appForm) {
        // 1.构造 AppArg
        AppArg appArg = new AppArg();
        //appType转换 AppCenterEnum 转换为 AppTypeEnum
        if (AppCenterEnum.AppType.DEV_APP.value() == appForm.getAppType()) {
            appArg.setAppType(AppTypeEnum.DEV_APP);
        } else if (AppCenterEnum.AppType.BASE_APP.value() == appForm.getAppType()) {
            appArg.setAppType(AppTypeEnum.BASE_APP);
        } else if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appForm.getAppType()) {
            appArg.setAppType(AppTypeEnum.BASE_SERVICE_APP);
        } else if (AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appForm.getAppType()) {
            appArg.setAppType(AppTypeEnum.EXT_SERVICE_APP);
        }

        appArg.setCallBackDomain(appForm.getCallBackDomain());
        appArg.setCallBackMsgUrl(appForm.getCallBackMsgUrl());
        appArg.setToken(appForm.getToken());
        appArg.setEncodingAesKey(appForm.getEncodingAesKey());
        appArg.setDevId(appForm.getAppCreater());
        appArg.setDevSig(null);

        // 2.oauth 创建应用
        CreateAppResult appResult = appService.createApp(appArg);
        if (!appResult.isSuccess()) {
            logger.warn("create oauth app error , {}", appResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "创建应用主体信息失败。");
        }

        // 3.添加 ip白名单
        String[] ipWhiteList = appForm.getIpWhiteList();
        if (null != ipWhiteList && ipWhiteList.length > 0) {
            com.facishare.open.appaccesscontrol.result.CommonResult commonResult = appIpWhiteListService.setAppIpWhiteList(appResult.getAppId(), ipWhiteList);
            if (!commonResult.isSuccess()) {
                logger.warn("setAppIpWhiteList error , appId [{}], ipWhiteList [{}], commonResult[{}]", appResult.getAppId(), ipWhiteList, commonResult);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, commonResult, "新增应用白名单失败。");
            }
        }
        return appResult;
    }

    @Override
    public void updateDevAppBase(OpenDevAppVO appForm) {
        AppResult appResult = openAppService.loadOpenApp(appForm.getAppId());
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.PARAM_ERROR, appResult, "应用不存在");
        }

        if (null != appForm.getLogoIconFile()) {
            IconFileVO logoIcon = null;
            try {
                logoIcon = IconFileVO.create(appForm.getLogoIconFile());
            } catch (Exception e) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "图片异常");
            }
            if (StringUtils.isNotBlank(appForm.getAppId()) && null != logoIcon) {
                processIcon(appForm.getAppId(), IconType.LOGO, logoIcon);
            }
        }

        if (null != appForm.getScopeGroup() && appForm.getScopeGroup().length > 0) {
            List<OpenAppScopeOrderDO> appScopeOrderDOs = new ArrayList<OpenAppScopeOrderDO>();
            List<OpenAppScopeOrderDO> scopeDOs = listScope(toScopeOwn(appResult.getResult().getAppType()));
            for (String scope : appForm.getScopeGroup()) {
                for (OpenAppScopeOrderDO scopeDO : scopeDOs) {
                    if (scopeDO.getScope().equals(scope)) {
                        scopeDO.setAppId(appForm.getAppId());
                        appScopeOrderDOs.add(scopeDO);
                        break;
                    }
                }
            }
            StatusResult result = openAppScopeOrderService.saveAppScopes(appForm.getAppId(), appScopeOrderDOs);
            if (!result.isSuccess()) {
                logger.warn("save app scope error , appId [{}] scopes[{}]", appForm.getAppId(), appForm.getScopes());
                throw new BizException(AjaxCode.BIZ_EXCEPTION, result, "保存权限。");
            }
        }

        OpenAppProperties openAppProperties;

        openAppProperties = OpenAppProperties.fromJson(appResult.getResult().getProperties());
        if (openAppProperties == null) {
            openAppProperties = new OpenAppProperties();
        }

        if (!CollectionUtils.isEmpty(appForm.getDetailImageUrls())) {
            List<String> collect = appForm.getDetailImageUrls().stream().map(s -> s.replace(imageUrl, "").replace(
                    ConfigCenter.BJ_IMAGE_VIEW_URL, ""))
                    .collect(Collectors.toList());
            openAppProperties.setDetailImageUrl(collect);
        }

        openAppProperties.setAppIntro(appForm.getAppIntro());

        if (appForm.getNeedAuth() != null) {
            openAppProperties.setNeedAuth(appForm.getNeedAuth());
        }

        if (appForm.getIsOfficialApp() != null) {
            openAppProperties.setIsOfficialApp(appForm.getIsOfficialApp());
        }

        if (appForm.getIsRecommendedApp() != null) {
            openAppProperties.setIsRecommendedApp(appForm.getIsRecommendedApp());
        }

        if (appForm.getPayDesc() != null) {
            openAppProperties.setPayDesc(appForm.getPayDesc());
        }

        if (appForm.getTrialDays() != null) {
            openAppProperties.setTrialDays(appForm.getTrialDays());
        }
        if (appForm.getPurchaseOnline() != null) {
            openAppProperties.setPurchaseOnline(appForm.getPurchaseOnline());
        }

        OpenAppDO app = new OpenAppDO();
        app.setAppId(appForm.getAppId());
        app.setAppName(appForm.getAppName());
        app.setServiceName(appForm.getAppName());
        app.setAppDesc(appForm.getAppDesc());
        app.setAppCreater(appForm.getAppCreater());
        app.setAppClass(appForm.getAppClass());
        app.setPayType(appForm.getPayType());
        app.setTryType(appForm.getTryType());
        app.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);
        app.setGmtModified(new Date());
        app.setProperties(openAppProperties.getJsonString());
        AppResult createResult = openAppService.updateOpenApp(app);
        if (!createResult.isSuccess()) {
            logger.error("update custom app error , result:{}", createResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createResult, createResult.getErrDescription());
        }
    }

    @Override
    public void updateDevAppDev(OpenDevAppVO appForm) {
        AppResult appResult = openAppService.loadOpenApp(appForm.getAppId());
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.PARAM_ERROR, appResult, "应用不存在");
        }
        // 2.创建 appId
        AppDO appDO = new AppDO();
        appDO.setAppId(appForm.getAppId());
        appDO.setToken(appForm.getToken());
        appDO.setEncodingAesKey(appForm.getEncodingAesKey());
        appDO.setCallBackDomain(appForm.getCallBackDomain());
        appDO.setCallBackMsgUrl(appForm.getCallBackMsgUrl());
        appDO.setDevId(appForm.getAppCreater());
        appDO.setGmtModified(new Date());
        UpdateAppResult updateAppResult = appService.updateApp(null, null, appDO);
        if (!updateAppResult.isSuccess()) {
            logger.warn("update app on oauth error , {}", updateAppResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateAppResult, "创建应用主体信息失败。");
        }

        String[] ipWhiteList = appForm.getIpWhiteList();
        if (null != ipWhiteList && ipWhiteList.length > 0) {
            com.facishare.open.appaccesscontrol.result.CommonResult commonResult = appIpWhiteListService.setAppIpWhiteList(appForm.getAppId(), ipWhiteList);
            if (!commonResult.isSuccess()) {
                logger.warn("setAppIpWhiteList error, appId [{}], ipWhiteList [{}], commonResult[{}]", appForm.getAppId(), ipWhiteList, commonResult);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, commonResult, "更新应用白名单失败。");
            }
        } else {
            com.facishare.open.appaccesscontrol.result.CommonResult commonResult = appIpWhiteListService.deleteAppIpWhiteList(appForm.getAppId());
            if (!commonResult.isSuccess()) {
                logger.warn("deleteAppIpWhiteList error, appId [{}], commonResult[{}]", appForm.getAppId(), commonResult);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, commonResult, "删除应用白名单失败。");
            }
        }
    }

    @Override
    public void updateDevApp(OpenDevAppVO appForm) {
        AppResult appResult = openAppService.loadOpenApp(appForm.getAppId());
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.PARAM_ERROR, appResult, "应用不存在");
        }

        IconFileVO logoIcon = null;

        try {
            logoIcon = IconFileVO.create(appForm.getLogoIconFile());
        } catch (Exception e) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "图片异常");
        }

        if (StringUtils.isNotBlank(appForm.getAppId()) && null != logoIcon) {
            processIcon(appForm.getAppId(), IconType.LOGO, logoIcon);
        }
        // 2.创建 appId
        AppDO appArg = new AppDO();
        appArg.setAppId(appForm.getAppId());
        appArg.setToken(appForm.getToken());
        appArg.setCallBackDomain(appForm.getCallBackDomain());
        appArg.setCallBackMsgUrl(appForm.getCallBackMsgUrl());
        appArg.setDevId(appForm.getAppCreater());
        appArg.setGmtModified(new Date());
        UpdateAppResult updateAppResult = appService.updateApp(null, null, appArg);
        if (!updateAppResult.isSuccess()) {
            logger.warn("update app on oauth error , {}", updateAppResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateAppResult, "创建应用主体信息失败。");
        }

        if (null != appForm.getScopeGroup() && appForm.getScopeGroup().length > 0) {
            List<OpenAppScopeOrderDO> appScopeOrderDOs = new ArrayList<OpenAppScopeOrderDO>();
            List<OpenAppScopeOrderDO> scopeDOs = listScope(toScopeOwn(appResult.getResult().getAppType()));
            for (String scope : appForm.getScopeGroup()) {
                for (OpenAppScopeOrderDO scopeDO : scopeDOs) {
                    if (scopeDO.getScope().equals(scope)) {
                        scopeDO.setAppId(appForm.getAppId());
                        appScopeOrderDOs.add(scopeDO);
                        break;
                    }
                }
            }
            StatusResult result = openAppScopeOrderService.saveAppScopes(appForm.getAppId(), appScopeOrderDOs);
            if (!result.isSuccess()) {
                logger.warn("save app scope error , appId [{}] scopes[{}]", appForm.getAppId(), appForm.getScopes());
                throw new BizException(AjaxCode.BIZ_EXCEPTION, result, "保存权限。");
            }
        }
        OpenAppDO app = new OpenAppDO();
        app.setAppId(appForm.getAppId());
        app.setAppName(appForm.getAppName());
        app.setServiceName(appForm.getAppName());
        app.setAppDesc(appForm.getAppDesc());
        app.setAppCreater(appForm.getAppCreater());
        app.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);
        app.setGmtModified(new Date());

        AppResult createResult = openAppService.updateOpenApp(app);
        if (!createResult.isSuccess()) {
            logger.error("update custom app error , result:{}", createResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createResult, createResult.getErrDescription());
        }
    }

    /**
     * appType转为scopeOwn
     */
    private ScopeOwnerEnum toScopeOwn(int appType) {
        ScopeOwnerEnum scopeOwn;
        if (AppCenterEnum.AppType.BASE_APP.value() == appType || AppCenterEnum.AppType.DEV_APP.value() == appType) {
            scopeOwn = ScopeOwnerEnum.APP;     //应用
        }else if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appType ||  AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appType) {
            scopeOwn = ScopeOwnerEnum.SERVICE; //服务号
        }else {
            throw new BizException(AjaxCode.PARAM_ERROR,  "应用或服务号类型错误");
        }
        return scopeOwn;
    }

    @Override
    public List<OpenAppScopeOrderDO> listScope(ScopeOwnerEnum scopeOwn) {
        GetScopesResult scopesResult = scopeService.getScopesByOwn("", "", scopeOwn);
        if (!scopesResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, scopesResult, "获取应用权限列表失败");
        }
        List<OpenAppScopeOrderDO> result = new ArrayList<OpenAppScopeOrderDO>();
        Iterator<ScopeDO> it = scopesResult.getScopeDOs().iterator();
        HashSet<String> trim = new HashSet<String>();
        ScopeDO scope = null;
        while (it.hasNext()) {
            scope = it.next();
            if (trim.add(scope.getScopeGroup())) {
                result.add(new OpenAppScopeOrderDO(null, scope.getScopeGroup(), scope.getName()));
            }
        }
        return result;
    }

    @Override
    public List<OpenDictDO> listAppClass() {
        DictListResult dictListResult = openDictService.queryList(CommonConstant.DICT_TYPE_APP_CLASS);
        if (!dictListResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, dictListResult, "获取应用分类列表失败");
        }
        return dictListResult.getResult();
    }


    @Override
    public void applyForOnline(String devId, String appId) {
        OpenAppDO entity = new OpenAppDO();
        entity.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);
        entity.setAppCreater(devId);
        entity.setAppId(appId);
        entity.setStatus(AppStatus.ON_LINE.getStatus());
        entity.setGmtModified(new Date());

        AppResult updateResult = openAppService.updateOpenApp(entity);
        if (!updateResult.isSuccess()) {
            logger.warn("applyForOnline app error , devId [{}] appId[{}] result : {}", devId, appId, updateResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateResult, "上线应用失败");
        }
    }

    @Override
    public List<OpenDictDO> listAppLabel() {
        DictListResult dictListResult = openDictService.queryList(CommonConstant.DICT_TYPE_APP_LABEL);
        if (!dictListResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, dictListResult, "获取应用标签列表失败");
        }
        return dictListResult.getResult();
    }

    @Override
    public void createAppComponent(String appId, Integer appType, OpenAppComponentVO openAppComponentVO) {
        IconFileVO logoIcon = null;
        try {
            logoIcon = IconFileVO.create(openAppComponentVO.getLogoIconFile());
        } catch (Exception e) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "图片异常");
        }

        AppArg arg = new AppArg();
        // appType转换 ：AppCenterEnum 转换为 AppTypeEnum
        if (AppCenterEnum.AppType.DEV_APP.value() == appType) {
            arg.setAppType(AppTypeEnum.DEV_APP);
        } else if (AppCenterEnum.AppType.BASE_APP.value() == appType) {
            arg.setAppType(AppTypeEnum.BASE_APP);
        } else if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appType) {
            arg.setAppType(AppTypeEnum.BASE_SERVICE_APP);
        } else if (AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appType) {
            arg.setAppType(AppTypeEnum.EXT_SERVICE_APP);
        }

        if (openAppComponentVO.getComponentType() == AppComponentTypeEnum.APP.getType()) {           //APP组件
            arg.setCallBackLoginUrl(openAppComponentVO.getLoginUrl());
        } else if (openAppComponentVO.getComponentType() == AppComponentTypeEnum.WEB.getType()) {    //WEB组件
            arg.setCallBackWebLoginUrl(openAppComponentVO.getLoginUrl());
        } else if (openAppComponentVO.getComponentType() == AppComponentTypeEnum.SERVICE.getType()) { //服务号组件
            arg.setCallBackLoginUrl(openAppComponentVO.getLoginUrl());
            arg.setCallBackWebLoginUrl(openAppComponentVO.getLoginUrl());
        }

        // oauth创建组件
        CreateComponentResult createComponentResult = appService.createComponent(appId, arg);
        if (!createComponentResult.isSuccess()) {
            logger.warn("createAppComponentOauth failed,appId[{}],openAppComponentVO [{}] result[{}]", appId,
                    JsonKit.object2json(openAppComponentVO), createComponentResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createComponentResult, "无法创建组件");
        }

        // 保存 logo
        if (null != logoIcon) {
            processIcon(createComponentResult.getComponentId(), IconType.LOGO, logoIcon);
        }

        // 应用中心创建组件
        OpenAppComponentDO entity = new OpenAppComponentDO();
        entity.setAppId(appId);
        entity.setComponentId(createComponentResult.getComponentId());
        entity.setComponentDesc(openAppComponentVO.getComponentDesc());
        entity.setComponentName(openAppComponentVO.getComponentName());
        entity.setComponentLabel(openAppComponentVO.getComponentLabel());
        if (null == entity.getComponentLabel()) {
            entity.setComponentLabel(CommonConstant.APP_LABEL_OTHER);
        }
        entity.setComponentType(openAppComponentVO.getComponentType());
        entity.setStatus(AppComponentStatus.VALID.getStatus());
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        BaseResult<Void> baseResult = openAppComponentService.createOpenAppComponent(null, entity);
        if (!baseResult.isSuccess()) {
            logger.warn("createOpenAppComponentCenter failed,appId[{}],openAppComponentVO [{}] result[{}]", appId,
                    JsonKit.object2json(openAppComponentVO), baseResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, "无法创建组件");
        }
        resetComponentLoginUrlCacheBatch(null, Lists.newArrayList(createComponentResult.getComponentId()));
    }

    @Override
    public void updateAppComponent(String componentId, OpenAppComponentVO openAppComponentVO) {
        if (null != openAppComponentVO.getLogoIconFile()) {
            IconFileVO logoIcon = null;
            try {
                logoIcon = IconFileVO.create(openAppComponentVO.getLogoIconFile());
            } catch (Exception e) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "图片异常");
            }

            if (null != logoIcon) {
                processIcon(componentId, IconType.LOGO, logoIcon);
            }
        }

        AppDO app = new AppDO();
        app.setAppId(openAppComponentVO.getComponentId());
        app.setGmtModified(new Date());
        if (openAppComponentVO.getComponentType() == AppComponentTypeEnum.APP.getType()) {
            app.setCallBackLoginUrl(openAppComponentVO.getLoginUrl());
        } else {
            app.setCallBackWebLoginUrl(openAppComponentVO.getLoginUrl());
        }
        CommonResult updateComponentResult = appService.updateComponent(null, null, app);
        if (!updateComponentResult.isSuccess()) {
            logger.warn("updateComponentOauth failed,openAppComponentVO [{}] result[{}]",
                    JsonKit.object2json(openAppComponentVO), updateComponentResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateComponentResult, "无法更新组件");
        }
        OpenAppComponentDO entity = new OpenAppComponentDO();
        entity.setAppId(openAppComponentVO.getAppId());
        entity.setComponentId(openAppComponentVO.getComponentId());
        entity.setComponentDesc(openAppComponentVO.getComponentDesc());
        entity.setComponentName(openAppComponentVO.getComponentName());
        entity.setComponentLabel(openAppComponentVO.getComponentLabel());
        entity.setGmtModified(new Date());
        if (openAppComponentVO.getStatus() != null) {
            entity.setStatus(openAppComponentVO.getStatus());
        }
        BaseResult<Void> baseResult = openAppComponentService.updateOpenAppComponent(null, entity);
        if (!baseResult.isSuccess()) {
            logger.warn("updateOpenAppComponentCenter failed,openAppComponentVO [{}] result[{}]",
                    JsonKit.object2json(openAppComponentVO), baseResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, "无法更新组件");
        }
        resetComponentLoginUrlCacheBatch(null, Lists.newArrayList(openAppComponentVO.getComponentId()));
    }

    @Override
    public OpenAppComponentVO loadAppComponentById(String componentId) {
        BaseResult<OpenAppComponentDO> baseResult = openAppComponentService.loadOpenAppComponentById(null, componentId);
        if (!baseResult.isSuccess()) {
            logger.warn("loadAppComponentById failed,componentId[{}] result[{}]",
                    componentId, baseResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, "无法加载组件");
        }
        OpenAppComponentDO appComponentDO = baseResult.getResult();
        OpenAppComponentVO vo = new OpenAppComponentVO();
        vo.setAppId(appComponentDO.getAppId());
        vo.setComponentId(appComponentDO.getComponentId());
        vo.setComponentName(appComponentDO.getComponentName());
        vo.setComponentLabel(appComponentDO.getComponentLabel());
        vo.setComponentType(appComponentDO.getComponentType());
        vo.setStatus(appComponentDO.getStatus());
        vo.setComponentDesc(appComponentDO.getComponentDesc());
        vo.setGmtCreate(appComponentDO.getGmtCreate());
        vo.setGmtModified(appComponentDO.getGmtModified());
        vo.setComponentLogoUrl(queryAppIconUrl(appComponentDO.getComponentId(), IconType.WEB));

        // 加载组件的跳转地址
        com.facishare.open.oauth.result.AppResult infoBatchResult = appService
                .getComponentInfo(null, null, componentId);
        if (!infoBatchResult.isSuccess()) {
            logger.warn("appService.getComponentInfo faild, componentId={}, result={}", componentId,
                    infoBatchResult.toString());
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "加载应用的组件列表错误");
        }
        vo.setLoginUrl(infoBatchResult.getCallBackWebloginUrl());
        if (AppComponentTypeEnum.APP.getType() == vo.getComponentType()) {
            vo.setLoginUrl(infoBatchResult.getCallBackloginUrl());
        }
        return vo;
    }

    @Override
    public void saveOpenComponentUrlGray(OpenComponentUrlGrayVO form) {
        BaseResult<String> baseResult = openComponentUrlGrayService.saveOpenComponentUrlGray(null, form);
        if (!baseResult.isSuccess()) {
            logger.warn("openComponentUrlGrayService.saveOpenComponentUrlGray failed, form={}, result={}", form,
                    baseResult.toString());
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, "保存组件灰度地址错误");
        }
        resetComponentLoginUrlCacheBatch(null, Lists.newArrayList(form.getComponentId()));
    }

    @Override
    public OpenComponentUrlGrayVO loadOpenComponentUrlGrayById(String componentId) {
        BaseResult<OpenComponentUrlGrayVO> baseResult = openComponentUrlGrayService.queryByComponentId(null, componentId);
        if (!baseResult.isSuccess()) {
            logger.warn("openComponentUrlGrayService.queryByComponentId failed, componentId={}, result={}",
                    componentId, baseResult.toString());
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取组件灰度地址错误");
        }
        return baseResult.getResult();
    }

    /**
     * 重置组件灰度地址缓存
     *
     * @param fsUserVO        用户信息
     * @param componentIdList 组件灰度地址集合
     */
    private void resetComponentLoginUrlCacheBatch(FsUserVO fsUserVO, List<String> componentIdList) {
        BaseResult<Void> baseResult = openComponentLoginUrlService.resetComponentLoginUrlCacheBatch(fsUserVO, componentIdList);
        if (!baseResult.isSuccess()) {
            logger.warn("openComponentLoginUrlService.resetComponentLoginUrlCacheBatch failed, fsUserVO={}, componentIdList={}, result={}", fsUserVO, componentIdList,
                    baseResult.toString());
        }
    }

    @Override
    public void deleteAppComponentById(String componentId) {
        BaseResult<Void> deleteCustomComponentResult = openAppComponentService.deleteCustomComponent(null, componentId);
        if (!deleteCustomComponentResult.isSuccess()) {
            logger.warn("openAppComponentService.deleteCustomComponent failed,componentId{} result[{}]",
                    componentId, deleteCustomComponentResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, deleteCustomComponentResult, "删除组件失败");
        }
        resetComponentLoginUrlCacheBatch(null, Lists.newArrayList(componentId));
    }

    @Override
    public void updateAppScopes(OpenAppScopeVO openAppScopeVO, ScopeOwnerEnum scopeOwn) {
        String appId = openAppScopeVO.getAppId();
        AppResult appResult = openAppService.loadOpenApp(appId);
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            throw new BizException(AjaxCode.PARAM_ERROR, appResult, "应用不存在");
        }
        List<AppScopeVO> scopeGroupVOList = openAppScopeVO.getAppScopeVOList();
        if (!CollectionUtils.isEmpty(scopeGroupVOList)) {
            List<OpenAppScopeOrderDO> appScopeOrderDOs = new ArrayList<>();
            List<OpenAppScopeOrderDO> scopeDOs = listScope(scopeOwn);
            for (AppScopeVO appScopeVO : scopeGroupVOList) {
                for (OpenAppScopeOrderDO scopeDO : scopeDOs) {
                    if (scopeDO.getScope().equals(appScopeVO.getScope())) {
                        scopeDO.setAppId(appId);
                        appScopeOrderDOs.add(scopeDO);
                        break;
                    }
                }
            }
            StatusResult result = openAppScopeOrderService.saveAppScopes(appId, appScopeOrderDOs);
            if (!result.isSuccess()) {
                logger.warn("openAppScopeOrderService.saveAppScopes error , appId [{}] scopes[{}]", appId, scopeGroupVOList);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, result, "保存权限失败");
            }
        }
    }

    @Override
    public boolean updateAppStatus(OpenAppDO openAppDO) {
        AppResult appResult = openAppService.loadOpenApp(openAppDO.getAppId());
        if (!appResult.isSuccess() || null == appResult.getResult()) {
            return false;
        }
        if (appResult.getResult().getStatus().equals(openAppDO.getStatus())) {
            return true;
        }
        AppResult updateOpenAppResult = openAppService.updateOpenApp(openAppDO);
        if (!updateOpenAppResult.isSuccess()) {
            logger.warn("openAppService.updateOpenApp error , result:{}", updateOpenAppResult);
            return false;
        }
        return true;
    }

    /**
     * 查询iconUrl
     *
     * @param appIdOrComponentId 应用或者组件ID
     * @param type               图片类型
     * @return iconUrl
     */
    private String queryAppIconUrl(String appIdOrComponentId, IconType type) {
        BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(appIdOrComponentId, type);
        if (!iconUrlResult.isSuccess()) {
            logger.warn("queryAppIconUrl failed, appIdOrComponentId[{}], type[{}], result[{}] : " + appIdOrComponentId, type, iconUrlResult);
        }
        return iconUrlResult.getResult();
    }

    @Override
    public void addServiceBind(String appId, String serviceId) {
        //添加之前，先判断appId是否已经绑定了serviceId
        GetServiceRefAppResult getServiceRefAppResult = appServiceRefService.getServiceRefAppList(appId, serviceId);
        if (!getServiceRefAppResult.isSuccess()) {
            logger.warn("getServiceRefAppList failed, appId[{}], serviceId[{}]", appId, serviceId);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取应用绑定的服务号信息错误");
        }
        List<AppServiceRefDO> appServiceRefDOsExist = getServiceRefAppResult.getAppServiceRefDOs();
        if (null != appServiceRefDOsExist && !appServiceRefDOsExist.isEmpty()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "应用已经绑定了该服务号了");  //appId已经绑定了serviceId
        }

        //应用绑定服务号：添加绑定关系
        AppServiceRefDO appServiceRefDO = new AppServiceRefDO();
        appServiceRefDO.setAppId(appId);
        appServiceRefDO.setServiceId(serviceId);
        appServiceRefDO.setGmtCreate(new Date());
        appServiceRefDO.setGmtModified(new Date());
        List<AppServiceRefDO>  appServiceRefDOs = Arrays.asList(appServiceRefDO);

        CommonResult commonResult = appServiceRefService.batchSaveServiceRefApps(appServiceRefDOs);
        if (!commonResult.isSuccess()) {
            logger.warn("batchSaveServiceRefApps failed, appServiceRefDOs[{}]", appServiceRefDOs);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "添加应用绑定的服务号信息错误");
        }
    }

    @Override
    public void deleteServiceBind(String appId, String serviceId) {
        //暂时不放开删除，先加上灰度
        if (BizCommonUtils.isAllowAppDeleteRefService(appId)){
            // TODO: 2016/10/27  是否需要解除授权、删除可见范围

            //应用绑定服务号：删除绑定关系
            CommonResult commonResult = appServiceRefService.deleteServiceRefApp(serviceId, appId);
            if (!commonResult.isSuccess()) {
                logger.warn("deleteServiceRefApp failed, appId[{}] , serviceId[{}]", appId, serviceId);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "删除应用绑定的服务号错误");
            }
        }
    }

    @Override
    public void modifyServiceBind(String appId, String serviceId, String newServiceId) {
        //暂时不放开修改，先加上灰度
        if (BizCommonUtils.isAllowAppModifyRefService(appId)){
            deleteServiceBind(appId, serviceId);
            addServiceBind(appId, newServiceId);
        }
    }
}
