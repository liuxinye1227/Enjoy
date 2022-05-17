package com.facishare.pay.business.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.pay.business.constant.BankType;
import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.business.service.UserCardInfoService;
import com.facishare.pay.common.result.ModelResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserCardInfoServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserCardInfoService userCardInfoService;
    
    @Test
    public void testAddUserCardInfo() {
        UserCardInfoDo userCardInfoDo = new UserCardInfoDo();
        userCardInfoDo.setBankBranchName("梅陇支行");
        userCardInfoDo.setBankCode("1001");
        userCardInfoDo.setBankId("001");
        userCardInfoDo.setBankName(BankType.GSYH.getDescription());
        userCardInfoDo.setBankNo(BankType.GSYH.getIndex());
        userCardInfoDo.setCardNo("62148345457855448");
        userCardInfoDo.setCreateTime(Calendar.getInstance());
        userCardInfoDo.setUserInfoId(102l);
        userCardInfoDo.setRemark("test case");
        userCardInfoDo.setCardName("郭明");
        userCardInfoDo.setStatus(1);
        assertTrue(userCardInfoService.addUserCardInfo(userCardInfoDo).getResult());
    }

    @Test
    public void testQueryUserCardInfoByUserInfoId() {
        ModelResult<List<UserCardInfoDo>> listModelResult = userCardInfoService.queryUserCardInfoByUserInfoId(101l);
        assertNotNull(listModelResult.getResult());
        for (UserCardInfoDo result : listModelResult.getResult()) {
            System.out.println(result.toString());
        }
    }

    @Test
    public void testUnBoundUserCardInfo() {
        UserCardInfoDo userCardInfoDo = new UserCardInfoDo();
        userCardInfoDo.setBankBranchName("梅陇支行");
        userCardInfoDo.setBankCode("1001");
        userCardInfoDo.setBankId("001");
        userCardInfoDo.setBankName(BankType.GSYH.getDescription());
        userCardInfoDo.setBankNo(BankType.GSYH.getIndex());
        userCardInfoDo.setCardNo("456478956546");
        userCardInfoDo.setCreateTime(Calendar.getInstance());
        userCardInfoDo.setUserInfoId(106l);
        userCardInfoDo.setRemark("test case");
        userCardInfoDo.setStatus(1);
        userCardInfoDo.setCardName("王涛");
        userCardInfoService.addUserCardInfo(userCardInfoDo).getResult();
        assertTrue(userCardInfoService.unBoundUserCardInfo(106l, "456478956546").getResult());
    }

}
