package com.facishare.open.app.center.api.utils;

import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.common.enums.MonitorTypeEnum;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zenglb on 2015/12/25.
 */
public class AppStatLogKitTest {

    @Test
    public void testLog() throws Exception {
        //开启
        AppStatLogKit.log("appId001","", MonitorTypeEnum.STATISTICS_OPEN_ACCOUNT,"fsEa",115, AppCenterEnum.AppType.BASE_APP.value());
        //web 使用
        AppStatLogKit.log("appId001","componentId001", MonitorTypeEnum.STATISTICS_WEB_USE_ACCOUNT,"fsEa",115,AppCenterEnum.AppType.BASE_APP.value());

        //开启失败
        AppStatLogKit.log("appId001","", MonitorTypeEnum.STATISTICS_OPEN_FAIL_ACCOUNT,"fsEa",115,AppCenterEnum.AppType.BASE_APP.value());


        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    }
}