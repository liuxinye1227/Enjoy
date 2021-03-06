package com.facishare.open.pic;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.model.vo.IconFileVO;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.AppIconService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.custom.menu.api.exception.UnexpectedException;
import com.facishare.open.manage.dao.AppIconDAO;
import com.facishare.open.manage.model.AppIconDO;
import com.facishare.open.msg.result.MessageResult;
import com.facishare.open.msg.service.MsgSessionService;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by albeter on 17/3/8.
 */


//@RunWith(JUnit4.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class OldPicToNewPicTest {

    @Resource
    private AppIconService appIconService;

    @Resource
    private AppIconDAO appIconDAO;

    @Resource
    private MsgSessionService msgSessionService;

    @Resource
    private OpenAppService openAppService;

    @Test
    public void change() throws IOException {
        System.out.println("tt");
        List<AppIconDO> appIconDOs = appIconDAO.queryAllAppIcons();
//        System.out.println(appIconDOs);

        List<AppIconDO> collect = appIconDOs;

//        List<AppIconDO> collect = appIconDOs.stream().filter(
//                appIconDO -> "FSAID_989764".equals(
//                        appIconDO.getAppId())).collect(Collectors.toList());


        //?????????????????????????????????????????????????????????????????????-?????????
        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????? -?????????
        //???????????????112???????????????????????????????????????,??????????????????????????????????????????.
        //??????112/113????????????????????????????????????,?????????????????????????????????.-?????????

        //1.??????app_icon???,??????icon_key???group??????????????????????????????.


        //2.??????icon_key?????????app_id?????????????????????,??????????????????????????????.
        // ?????????????????????????????????????????????????????????????????????: OpenFsUserAppViewManagerImplupdateSessionNameLogo


        //3.???redis????????????app_id???????????????.


        for(AppIconDO appIconDO:collect){

            if (!appIconDO.getMasterIconId().startsWith("group"))  continue;


            String extName = getExtName(appIconDO.getMasterIconId());

            String fileName = "a." + extName;

            try {
                downloadFile("http://open.ceshi112.com/fscdn/img?imgId=" + appIconDO.getMasterIconId(), fileName);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }

//
//        String appId ="FSAID_9896e6";
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
//
//        BufferedImage image = ImageIO.read(fileInputStream);
//
//
//        //inputStream ?????????????????????? inputStream ?????????????????????byte??????????
            byte[] bytes1 = IOUtils.toByteArray(fileInputStream);
//
            List<IconType> types = new ArrayList<>();
            types.add(IconType.LOGO);
            Rectangle rectangle = new Rectangle(0,0,1,1);
            BaseResult result = appIconService.update(appIconDO.getAppId(), types, bytes1, extName, rectangle);
            System.out.println(appIconDO.getAppId());
            System.out.println(result);
            AppResult appResult = openAppService.loadOpenAppFast(appIconDO.getAppId());
            if(appResult != null && appResult.getResult()!=null){
                OpenAppDO openAppDO = appResult.getResult();

                if (IntStream.of(AppCenterEnum.AppType.SERVICE.value(), AppCenterEnum.AppType.OUT_SERVICE_APP.value())
                        .noneMatch(t -> t == appResult.getResult().getAppType())) {
                    continue;
                }

                //???????????????????????????session???????????????todo
                MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(
                        appIconDO.getAppId(),openAppDO.getAppName() , queryAppIconUrl(openAppDO.getAppId(),IconType.SERVICE));
                System.out.println(messageResult);
            }
        }
    }

    private String queryAppIconUrl(String appIdOrComponentId, IconType type){
        BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(appIdOrComponentId, type);
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

    private void processIcon(String appId, IconType type, IconFileVO icon) {
        // ??????????????????????????????????????????
        List<IconType> types = Arrays.asList(type);

        // ?????????????????????????????????????????????
        int width = icon.getWidth();
        int height = icon.getHeight();
        // ?????????
        int value = width < height ? width : height;
        double w = (double) width;
        double h = (double) height;
        int x = (int) Math.round((w - value) / 2);
        int y = (int) Math.round((h - value) / 2);
        Rectangle rectangle = new Rectangle(x, y, value, value);

        BaseResult result = appIconService.update(appId, types, icon.getData(), icon.getExt(), rectangle);
        if (!result.isSuccess()) {
//            logger.warn("Add icon fail. result: {}.", result);
            System.out.println("error");
        }
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


//    public static InputStream downloadFile2(String url, String localPath) throws IOException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpGet httpget = new HttpGet(url);
//            System.out.println("Executing request " + httpget.getRequestLine());
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            try {
//                System.out.println("----------------------------------------");
//                System.out.println(response.getStatusLine());
//                // Get hold of the response entity
//                HttpEntity entity = response.getEntity();
//                // If the response does not enclose an entity, there is no need
//                // to bother about connection release
//                if (entity != null) {
//                    InputStream in = entity.getContent();
//                    try {
//                        // do something useful with the response
//                        byte[] buffer = new byte[1024];
//                        BufferedInputStream bufferedIn = new BufferedInputStream(in);
//                        int len = 0;
//                        FileOutputStream fileOutStream = new FileOutputStream(new File(localPath));
//                        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutStream);
//                        while ((len = bufferedIn.read(buffer, 0, 1024)) != -1) {
//                            bufferedOut.write(buffer, 0, len);
//                        }
//                        bufferedOut.flush();
//                        bufferedOut.close();
//                    } catch (IOException ex) {
//                        // In case of an IOException the connection will be released
//                        // back to the connection manager automatically
//                        throw ex;
//                    } finally {
//                        // Closing the input stream will trigger connection release
//                        in.close();
//                    }
//                }
//            } finally {
//                response.close();
//            }
//        } finally {
//            httpclient.close();
//        }
//    }

}
