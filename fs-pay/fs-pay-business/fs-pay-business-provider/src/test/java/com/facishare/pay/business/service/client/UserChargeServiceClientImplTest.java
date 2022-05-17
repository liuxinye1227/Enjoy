package com.facishare.pay.business.service.client;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.BankType;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.constant.UserStatus;
import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;
import com.facishare.pay.business.model.vo.UserChargeVO;
import com.facishare.pay.business.service.UserCardInfoService;
import com.facishare.pay.business.service.UserChargeService;
import com.facishare.pay.business.service.UserInfoService;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.DateUtils;
import com.facishare.pay.common.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client/fs-pay-business-service-test.xml")
public class UserChargeServiceClientImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserChargeService userChargeService;

    @Autowired
    private UserCardInfoService userCardInfoService;

    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void testQueryUserCharge() {
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
        //
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setEnterpriseAccount("fsfte2c");
        userInfoDo.setFsUserId(1018L);
        userInfoDo.setUserName("wangt");
        userInfoDo.setStatus(UserStatus.ENABLED);
        userInfoDo.setType(2);
        userInfoDo.setPassword(MD5Util.MD5Encode("123456"));
        userInfoService.addUserInfo(userInfoDo);
        // 先绑卡
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
        userCardInfoService.addUserCardInfo(userCardInfoDo);

        UserChargeVO userChargeVO = new UserChargeVO(new BigDecimal(10), "fsfte2c", 1018L,
            Calendar.getInstance(), 1,MD5Util.MD5Encode("123456"), "1", 1, 1L);


       userChargeService.charge(userChargeVO);

    }

    @Test
    public void testUpdateChargeStatus() throws Exception {


    }



}
