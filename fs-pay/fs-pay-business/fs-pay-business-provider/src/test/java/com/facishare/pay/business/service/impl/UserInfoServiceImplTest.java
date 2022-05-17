package com.facishare.pay.business.service.impl;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.UserStatus;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.service.UserInfoService;
import com.facishare.pay.common.result.ModelResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserInfoServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserInfoService userInfoService;
    
    @Test
    public void testUpdateUserInfo() {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setEnterpriseAccount("fs");
        userInfoDo.setFsUserId(102l);
        userInfoDo.setUserName("王武");
        assertTrue(userInfoService.updateUserInfo(userInfoDo).getResult());
    }

    @Test
    public void testAddUserInfo() {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setEnterpriseAccount("fs");
        userInfoDo.setFsUserId(103l);
        userInfoDo.setUserName("admin");
        userInfoDo.setStatus(UserStatus.ENABLED);
        userInfoDo.setType(2);
        assertTrue(userInfoService.addUserInfo(userInfoDo).getResult());
    }
    
    @Test 
    public void testQueryUserInfo() {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setEnterpriseAccount("fs");
        userInfoDo.setType(1);
        ModelResult<Pager<UserInfoDo>> result = userInfoService.queryUserInfo(new Pager<UserInfoDo>(), userInfoDo);
        assertNotNull(result.getResult());
        for (UserInfoDo userInfo : result.getResult().getData()) {
            System.out.println(userInfo.toString());
        }
    }

}
