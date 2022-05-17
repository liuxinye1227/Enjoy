package com.facishare.open.manage.controller;

/**
 * @author chenzengyong
 * @date on 2017/4/18.
 */

import com.facishare.open.manage.ajax.result.AjaxResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})

public class DevControllerTest {

    @Resource
    DevController devController;

    @Test
    public void testUpdateAppList() {
        String ea = "53614";
        AjaxResult ajaxResult = devController.updateAppList(ea);
        System.out.println(ajaxResult);
    }


    @Test
    public void setServiceNumName(){
        String appId="";
        String ea="";
        String name="";
        String logo="";

        devController.setServiceNumName(appId, ea, name, logo);
    }


    @Test
    public void updateAppEaStop(){
        String appId="FSAID_5f5e276";
        String ea="57252";
        AjaxResult ajaxResult = devController.updateAppEaStop(ea, appId);
        System.out.println(ajaxResult);
    }

    @Test
    public void updateAppEaStart(){
        String appId="FSAID_5f5e276";
        String ea="57252";
        AjaxResult ajaxResult = devController.updateAppEaStart(ea, appId);
        System.out.println(ajaxResult);
    }

}
