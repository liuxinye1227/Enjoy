package com.facishare.pay.business.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.business.service.UserCardInfoService;
import com.facishare.pay.common.result.ModelResult;

public class TestCase {
    
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserCardInfoService userCardInfoService = (UserCardInfoService)ac.getBean("userCardInfoService");
        ModelResult<List<UserCardInfoDo>> listModelResult = userCardInfoService.queryUserCardInfoByUserInfoId(101l);
        for (UserCardInfoDo result : listModelResult.getResult()) {
            System.out.println(result.toString());
        }
    }
}
