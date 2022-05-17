package com.facishare.pay.business.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;
import com.facishare.pay.business.service.UserChargeService;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserChargeServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserChargeService userChargeService;
    
    @Test
    public void testQueryUserChargeLog() {
        ChargeSearchVO params = new ChargeSearchVO();
        params.setChannelId(2l);
        params.setChargeNo("12345");
        params.setChargeWay(1234l);
        params.setEnterpriseAccount("fs");
        params.setFsUserId(101l);
        params.setMaxAmount(BigDecimal.valueOf(200));
        params.setMinAmount(BigDecimal.valueOf(100));
        params.setOrderNo("123456");
        params.setRequestBeginTime(DateUtils.parseYYYY_MM_DDToCalendar("2015-10-10"));
        params.setRequestEndTime(DateUtils.parseYYYY_MM_DDToCalendar("2015-10-14"));
        params.setStatus(ChargeStatus.SUCCESS_CHARGE);
        ModelResult<Pager<UserChargeDo>> result = userChargeService.queryUserCharge(new Pager<UserChargeDo>(), params);
        assertNotNull(result.getResult().getData());
        if (result.getResult().getData().size() > 0) {
            List<UserChargeDo> list = result.getResult().getData();
            for (UserChargeDo userChargeLogDo : list) {
                System.out.println(userChargeLogDo.toString());
            }
        }
    }

    @Test
    public void testCharge() throws Exception {

       // userChargeService
    }

    @Test
    public void testUpdateChargeStatus() throws Exception {


    }
}
