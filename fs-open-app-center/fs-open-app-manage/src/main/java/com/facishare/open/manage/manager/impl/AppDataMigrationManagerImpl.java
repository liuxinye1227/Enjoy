package com.facishare.open.manage.manager.impl;

import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.app.center.api.service.OpenAppComponentService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.AppDataMigrationManager;
import com.facishare.open.oauth.model.AppDO;
import com.facishare.open.oauth.model.enums.AppTypeEnum;
import com.facishare.open.oauth.result.CommonResult;
import com.facishare.open.oauth.result.UpdateAppResult;
import com.facishare.open.oauth.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * impl.
 * Created by zenglb on 2016/7/1.
 */
@Service
public class AppDataMigrationManagerImpl implements AppDataMigrationManager {

    @Resource
    private OpenAppService openAppService;
    @Resource
    private AppService appService;
    @Resource
    private OpenAppAdminService openAppAdminService;
    @Resource
    private OpenAppComponentService openAppComponentService;


    @Override
    public OpenAppDO loadOpenAppFast(String appId) {
        AppResult loadOpenAppFastResult = openAppService.loadOpenAppFast(appId);
        if (!loadOpenAppFastResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, loadOpenAppFastResult, "查询应用信息失败.");
        }
        return loadOpenAppFastResult.getResult();
    }

    @Override
    public void modifyAppType(String appId, AppCenterEnum.AppType appCenterAppType) {
        // 1.修改应用中心的应用类型.
        OpenAppDO newServiceApp = new OpenAppDO();
        newServiceApp.setAppId(appId);
        newServiceApp.setAppType(appCenterAppType.value());
        AppResult appResult = openAppService.updateOpenApp(newServiceApp);
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "修改应用类型失败.");
        }
        // 2.修改oauth的应用类型.
        AppDO app = new AppDO();
        app.setAppId(appId);
        app.setAppType(AppTypeEnum.CUSTOM_APP.getValue());
        if (appCenterAppType == AppCenterEnum.AppType.SERVICE) {
            app.setAppType(AppTypeEnum.SERVICE_APP.getValue());
        }
        UpdateAppResult updateAppResult = appService.updateApp(null, null, app);
        if (!updateAppResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateAppResult, "修改应用的应用类型.");
        }

        // 3.修改应用管理员的应用类型.
        BaseResult<Void> modifyAppTypeResult = openAppAdminService.modifyAppType(appId, appCenterAppType);
        if (!modifyAppTypeResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, modifyAppTypeResult, "修改应用管理员中的应用类型");
        }
    }

    @Override
    public void updateOpenAppComponent(FsUserVO fsAdminUser, String appComponentId, String newAppId) {
        OpenAppComponentDO entity = new OpenAppComponentDO();
        entity.setAppId(newAppId);
        entity.setComponentId(appComponentId);
        BaseResult<Void> updateOpenAppComponentResult = openAppComponentService.updateOpenAppComponent(fsAdminUser, entity);
        if (!updateOpenAppComponentResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, updateOpenAppComponentResult, "修改组件的所属appId失败");
        }
        AppDO app = new AppDO();
        app.setAppId(appComponentId);
        app.setParentAppId(newAppId);
        CommonResult commonResult = appService.updateComponent(null, null, app);
        if (!commonResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, commonResult, "修改oauth的组件的所属应用id失败.");
        }
    }
}
