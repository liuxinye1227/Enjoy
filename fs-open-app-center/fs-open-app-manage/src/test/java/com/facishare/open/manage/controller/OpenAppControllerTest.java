package com.facishare.open.manage.controller;

import com.facishare.open.BaseMockTest;
import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.model.enums.AppStatus;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.model.OpenAppComponentVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-open-app-center-manager.xml"})
public class OpenAppControllerTest extends BaseMockTest {

    @Autowired
    private OpenAppController openAppController;

    String appId = "app_test_001";
    String callBackDomain = "www.baidu.com";
    String callBackMsgUrl = "http://www.baidu.com/aa";
    String token = "xxxxaaaa";
    String encodingAesKey = "fdsafdsafdsafdsa";
    String appCreater = "dev_001";
    String appName = "app_name_test_001";
    String appDesc = "app_desc_test_001";
    String appUrl = "http://www.baidu.com/app1";
    String webUrl = "http://www.baidu.com/web1";
    String componentId1 = "component_id_test_1";
    String componentId2 = "component_id_test_2";
    String[] scopeLst = new String[]{"get_user_info", "get_name"};

    @Test
    public void testQueryPager() {
    }

    @Test
    public void testListScope() {
        getScopes(0);
        AjaxResult result = openAppController.listScope();
        assertEquals(0, result.getErrCode().intValue());
    }

    @Test
    public void testLoadAppByDevIdAndAppId() {
    }

    @Test
    public void testDeleteDevApp() {
    }

    @Test
    public void testApplyForOnline() {
    }

    @Test
    public void testCreateDevApp() throws IOException {
        createApp(0, appId, callBackDomain, callBackMsgUrl, token, encodingAesKey, appCreater);
        getScopes(0, scopeLst);
        saveAppScopes(0, appId, scopeLst);
        createOpenApp(0, appId, appName, appDesc, CommonConstant.APP_CLASS_ENTERPRISE, appCreater, AppStatus.AUDIT_PASS.getStatus());

        createComponentOauth(0, appId, componentId1, appCreater, AppComponentTypeEnum.APP.getType(), appUrl);
        createComponentOauth(0, appId, componentId2, appCreater, AppComponentTypeEnum.WEB.getType(), webUrl);
        createOpenAppComponent(0, appId, componentId1, CommonConstant.APP_LABEL_OTHER);
        createOpenAppComponent(0, appId, componentId2, CommonConstant.APP_LABEL_OTHER);

        OpenDevAppVO appForm = new OpenDevAppVO();
        appForm.setAppCreater(appCreater);
        appForm.setAppName(appName);
        appForm.setAppDesc(appDesc);
        appForm.setCallBackDomain(callBackDomain);
        appForm.setCallBackMsgUrl(callBackMsgUrl);
        appForm.setToken(token);
        appForm.setEncodingAesKey(encodingAesKey);
        appForm.setAppClass(CommonConstant.APP_CLASS_ENTERPRISE);

        appForm.setScopeGroup(scopeLst);

        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream imgOut = new ByteArrayOutputStream();
        ImageIO.write(image, "xx.jpg", imgOut);

        appForm.setLogoIconFile(new MockMultipartFile("aa.jpg", "aa.jpg", "", imgOut.toByteArray()));


        List<OpenAppComponentVO> appComponents = new ArrayList<>();
        OpenAppComponentVO vo = new OpenAppComponentVO();
        vo.setComponentName(componentId1 + "_name");
        vo.setComponentDesc(componentId1 + "_desc");
        vo.setComponentType(AppComponentTypeEnum.APP.getType());
        vo.setComponentLabel(CommonConstant.APP_LABEL_OTHER);
        vo.setLoginUrl(appUrl);
        vo.setLogoIconFile(new MockMultipartFile("app1.jpg", "app1.jpg", "", imgOut.toByteArray()));
        appComponents.add(vo);

        vo = new OpenAppComponentVO();
        vo.setComponentName(componentId2 + "_name");
        vo.setComponentDesc(componentId2 + "_desc");
        vo.setComponentType(AppComponentTypeEnum.WEB.getType());
        vo.setComponentLabel(CommonConstant.APP_LABEL_OTHER);
        vo.setLoginUrl(webUrl);
        vo.setLogoIconFile(new MockMultipartFile("web1.jpg", "web1.jpg", "", imgOut.toByteArray()));
        appComponents.add(vo);

        appForm.setAppComponents(appComponents);

        AjaxResult ajax = openAppController.createDevApp(appForm);
        assertEquals(0, ajax.getErrCode().intValue());
    }

    @Test
    public void testUpdateDevAppBase() throws IOException {
        loadOpenApp(0, appId, appName);
        updateOpenApp(0, appId, appName + "xx", appDesc + "xx", appCreater, CommonConstant.APP_CLASS_ENTERPRISE);

        OpenDevAppVO appForm = new OpenDevAppVO();
        appForm.setAppId(appId);
        appForm.setAppCreater(appCreater);
        appForm.setAppName(appName + "xx");
        appForm.setAppDesc(appDesc + "xx");
        appForm.setAppClass(CommonConstant.APP_CLASS_ENTERPRISE);

        AjaxResult ajax = openAppController.updateDevAppBase(appForm);
        assertEquals(0, ajax.getErrCode().intValue());
    }

    @Test
    public void testUpdateDevAppDev() {
        loadOpenApp(0, appId, appName);
        updateAppOauth(0, appId, callBackDomain, callBackMsgUrl, token, encodingAesKey, appCreater);

        OpenDevAppVO appForm = new OpenDevAppVO();
        appForm.setAppId(appId);
        appForm.setToken(token);
        appForm.setEncodingAesKey(encodingAesKey);
        appForm.setCallBackMsgUrl(callBackMsgUrl);
        appForm.setCallBackDomain(callBackDomain);
        appForm.setAppCreater(appCreater);

        AjaxResult ajax = openAppController.updateDevAppDev(appForm);
        assertEquals(0, ajax.getErrCode().intValue());
    }

}
