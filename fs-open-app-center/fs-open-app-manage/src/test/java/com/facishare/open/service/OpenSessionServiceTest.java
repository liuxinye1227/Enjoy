package com.facishare.open.service;

import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.msg.result.MessageResult;
import com.facishare.open.msg.service.MsgSessionService;
import com.facishare.qixin.api.model.open.arg.OpenGetOSS1SessionDefinitionArg;
import com.facishare.qixin.api.model.open.result.GetOSS1SessionDefinitionResult;
import com.facishare.qixin.api.open.OpenSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author chenzengyong
 * @date on 2017/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class OpenSessionServiceTest {

    @Resource
    private OpenSessionService openSessionService;

    @Resource
    private MsgSessionService msgSessionService;

    @Test
    public void findSessionDefine() {
        String appId = "FSAID_bebd10bxxxx";
        OpenGetOSS1SessionDefinitionArg arg = new OpenGetOSS1SessionDefinitionArg();
        arg.setAppId(appId);
        GetOSS1SessionDefinitionResult oss1SessionDefinition =
                openSessionService.getOSS1SessionDefinition(arg);

        System.out.println(oss1SessionDefinition);
    }


    @Test
    public void updateUniversalSessionDefinition() {
        String appId = "FSAID_bebd10d";


        //调用军卫的接口判断session是否存在？todo
        MessageResult messageResult = msgSessionService.updateUniversalSessionDefinition(
                appId, null,
                "https://a3.fspage.com/FSC/EM/Avatar/GetAvatar?path=N_201703_22_0e19bc8b3eca49998da8b69b09725fce.png&size=150_150&ea=appCenter&type=WEB&width=60&height=60");


        System.out.println(messageResult);
    }




}
