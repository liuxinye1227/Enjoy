package com.facishare.open;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.app.center.api.service.OpenAppComponentService;
import com.facishare.open.app.center.api.service.OpenAppScopeOrderService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.oauth.model.AppDO;
import com.facishare.open.oauth.model.ScopeDO;
import com.facishare.open.oauth.model.arg.AppArg;
import com.facishare.open.oauth.model.enums.AppTypeEnum;
import com.facishare.open.oauth.result.CreateAppResult;
import com.facishare.open.oauth.result.CreateComponentResult;
import com.facishare.open.oauth.result.GetScopesResult;
import com.facishare.open.oauth.result.UpdateAppResult;
import com.facishare.open.oauth.service.AppService;
import com.facishare.open.oauth.service.ScopeService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试mock
 *
 * @author zenglb
 * @date 2015年9月2日
 */
public class BaseMockTest extends BaseTest {

    @Autowired
    private ScopeService scopeService;
    @Autowired
    private AppService appService;
    @Autowired
    private OpenAppScopeOrderService openAppScopeOrderService;
    @Autowired
    private OpenAppService openAppService;
    @Autowired
    private OpenAppComponentService openAppComponentService;

    protected void getScopes(int code, String... scope) {
        GetScopesResult result = new GetScopesResult();
        result.setErrcode(code);
        result.setErrmsg("--");
        result.setErrDescription("--");
        List<ScopeDO> lst = new ArrayList<ScopeDO>();
        ScopeDO scopeDO = null;
        for (String string : scope) {
            scopeDO = new ScopeDO();
            scopeDO.setScopeGroup(string);
            scopeDO.setName("name___" + string);
            lst.add(scopeDO);
        }
        result.setScopeDOs(lst);
        Mockito.when(scopeService.getScopes("", "")).thenReturn(result);
    }

    protected void createApp(int code, String appId, String callBackDomain, String callBackMsgUrl, String token,
                             String encodingAesKey, String appCreater) {
        CreateAppResult result = new CreateAppResult();
        result.setErrcode(code);
        result.setErrmsg("--");
        result.setErrDescription("--");
        result.setAppId(appId);

        AppArg appArg = new AppArg();
        appArg.setAppType(AppTypeEnum.DEV_APP);
        appArg.setCallBackDomain(callBackDomain);
        appArg.setCallBackMsgUrl(callBackMsgUrl);
        appArg.setToken(token);
        appArg.setEncodingAesKey(encodingAesKey);
        appArg.setDevId(appCreater);
        appArg.setDevSig(null);
        Mockito.when(appService.createApp(appArg)).thenReturn(result);
    }

    protected void saveAppScopes(int code, String appId, String... scopes) {
        List<OpenAppScopeOrderDO> scopeList = new ArrayList<OpenAppScopeOrderDO>();
        for (String scope : scopes) {
            scopeList.add(new OpenAppScopeOrderDO(appId, scope, "name___" + scope));
        }
        StatusResult result = new StatusResult(new BizException(code, "--", "--"));
        Mockito.when(openAppScopeOrderService.saveAppScopes(appId, scopeList)).thenReturn(result);
    }

    protected void createOpenApp(int code, String appId, String appName, String appDesc, Integer appClass,
                                 String appCreater, Integer status) {
        OpenAppDO entity = new OpenAppDO();
        entity.setAppId(appId);
        entity.setAppName(appName);
        entity.setAppClass(appClass);
        entity.setAppDesc(appDesc);
        entity.setAppMode(CommonConstant.APP_MODE_COMMON);
        entity.setAppType(AppType.DEV_APP.value());
        entity.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);
        entity.setAppCreater(appCreater);
        entity.setServiceName(appName);
        entity.setStatus(status);
        AppResult result = new AppResult(new BizException(code, "--", "--"));
        Mockito.when(openAppService.createOpenApp(entity)).thenReturn(result);
    }

    protected void createComponentOauth(int code, String appId, String componentId, String appCreater,
                                        Integer componentType, String loginUrl) {
        AppArg componentArg = new AppArg();

        componentArg.setAppType(AppTypeEnum.DEV_APP);
        componentArg.setDevId(appCreater);
        if (componentType == AppComponentTypeEnum.APP.getType()) {
            componentArg.setCallBackLoginUrl(loginUrl);
        } else {
            componentArg.setCallBackWebLoginUrl(loginUrl);
        }
        CreateComponentResult result = new CreateComponentResult();
        result.setErrcode(code);
        result.setErrmsg("--");
        result.setErrDescription("--");
        result.setComponentId(componentId);
        Mockito.when(appService.createComponent(appId, componentArg)).thenReturn(result);
    }

    protected void createOpenAppComponent(int code, String appId, String componentId, Integer componentLabel) {
        OpenAppComponentDO entity = new OpenAppComponentDO();

        entity.setAppId(appId);
        entity.setComponentId(componentId);
        entity.setComponentName(componentId + "_name");
        entity.setComponentDesc(componentId + "_desc");
        entity.setComponentLabel(componentLabel);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());

        BaseResult<Void> result = new BaseResult<Void>(new BizException(code, "--", "--"));
        Mockito.when(openAppComponentService.createOpenAppComponent(null, entity)).thenReturn(result);
    }

    protected void loadOpenApp(int code, String appId, String appName) {
        OpenAppDO entity = new OpenAppDO();
        entity.setAppId(appId);
        entity.setAppName(appName);
        AppResult result = new AppResult(new BizException(code, "--", "--"));
        result.setResult(entity);
        Mockito.when(openAppService.loadOpenApp(appId)).thenReturn(result);
    }

    protected void updateOpenApp(int code, String appId, String appName,
                                 String appDesc, String appCreater, Integer appClass) {
        OpenAppDO entity = new OpenAppDO();
        entity.setAppId(appId);
        entity.setAppName(appName);
        entity.setServiceName(appName);
        entity.setAppDesc(appDesc);
        entity.setAppCreater(appCreater);
        entity.setAppClass(appClass);
        entity.setCreaterType(CommonConstant.APP_CREATER_DEV_ACCOUNT);
        entity.setGmtModified(new Date());
        AppResult result = new AppResult(new BizException(code, "--", "--"));
        result.setResult(entity);
        Mockito.when(openAppService.updateOpenApp(entity)).thenReturn(result);
    }

    protected void updateAppOauth(int code, String appId, String callBackDomain, String callBackMsgUrl, String token,
                                  String encodingAesKey, String appCreater) {
        UpdateAppResult result = new UpdateAppResult();
        result.setErrcode(code);
        result.setErrmsg("--");
        result.setErrDescription("--");

        AppDO appDO = new AppDO();
        appDO.setAppId(appId);
        appDO.setToken(token);
        appDO.setEncodingAesKey(encodingAesKey);
        appDO.setCallBackDomain(callBackDomain);
        appDO.setCallBackMsgUrl(callBackMsgUrl);
        appDO.setDevId(appCreater);

        appDO.setGmtModified(new Date());
        Mockito.when(appService.updateApp(null, null, appDO)).thenReturn(result);
    }

}
