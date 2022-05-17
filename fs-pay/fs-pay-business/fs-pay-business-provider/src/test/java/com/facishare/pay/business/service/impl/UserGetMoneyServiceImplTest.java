package com.facishare.pay.business.service.impl;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.business.model.UserGetMoneyDo;
import com.facishare.pay.business.model.result.GetMoneyResult;
import com.facishare.pay.business.model.vo.GetMoneyUpdateVO;
import com.facishare.pay.business.model.vo.UserGetMoneyVO;
import com.facishare.pay.business.service.UserGetMoneyService;
import com.facishare.pay.common.result.ModelResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserGetMoneyServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserGetMoneyService userGetMoneyService;
    
    @Test
    public void testQueryUserGetMoneyLog() {
        ModelResult<Pager<UserGetMoneyDo>> result = userGetMoneyService.queryUserGetMoney(new Pager<UserGetMoneyDo>(), null);
        assertNotNull(result.getResult().getData());
        if (result.getResult().getData().size() > 0) {
            for (UserGetMoneyDo userGetMoneyLogDo : result.getResult().getData()) {
                System.out.println(userGetMoneyLogDo.getId());
                System.out.println(userGetMoneyLogDo.toString());
            }
        }
    }
    
    @Test
    public void testGetMoneyApply() {
        /*
         * UserGetMoneyVO(BigDecimal amount, String enterpriseAccount,
            Long fsUserId, Calendar createTime, Long cardInfoId,
            String chargeNo, int chargeWay, int channelId,
            String password)
         * */
        UserGetMoneyVO userGetMoneyVO = new UserGetMoneyVO(new BigDecimal(100)
        , "fs", 1048l, Calendar.getInstance(), 11l, "chargeNo", 2, 11, "e10adc3949ba59abbe56e057f20f883e");
        ModelResult<GetMoneyResult> result = userGetMoneyService.getMoneyApply(userGetMoneyVO);
        assertNotNull(result.getResult());
    }
    
    @Test
    public void testUpdateGetMoneyAuditor() {
        GetMoneyUpdateVO getMoneyUpdateVO = new GetMoneyUpdateVO();
        getMoneyUpdateVO.setOrderNo("201510301034370000001001");
        getMoneyUpdateVO.setEnterpriseAccount("fs");
        getMoneyUpdateVO.setFsUserId(1048l);
        getMoneyUpdateVO.setAuditor("auditor");
        getMoneyUpdateVO.setGetMoneyStatus(GetMoneyStatus.AUDITED);
        getMoneyUpdateVO.setVerifyMd5(getMoneyUpdateVO.toMD5Str());
        assertNotNull(userGetMoneyService.updateGetMoneyAuditor(getMoneyUpdateVO).getResult());
        
    }
    
    @Test
    public void testUpdateGetMoneyTeller() {
        GetMoneyUpdateVO getMoneyUpdateVO = new GetMoneyUpdateVO();
        getMoneyUpdateVO.setOrderNo("201510301034370000001001");
        getMoneyUpdateVO.setEnterpriseAccount("fs");
        getMoneyUpdateVO.setFsUserId(1048l);
        getMoneyUpdateVO.setTeller("teller");
        getMoneyUpdateVO.setGetMoneyStatus(GetMoneyStatus.TELLER);
        getMoneyUpdateVO.setVerifyMd5(getMoneyUpdateVO.toMD5Str());
        assertNotNull(userGetMoneyService.updateGetMoneyTeller(getMoneyUpdateVO).getResult());
    }
    
    @Test
    public void testUpdateGetMoneyFinish() {
        GetMoneyUpdateVO getMoneyUpdateVO = new GetMoneyUpdateVO();
        getMoneyUpdateVO.setOrderNo("201510301034370000001001");
        getMoneyUpdateVO.setEnterpriseAccount("fs");
        getMoneyUpdateVO.setFsUserId(1048l);
        getMoneyUpdateVO.setGetMoneyStatus(GetMoneyStatus.FINISH);
        getMoneyUpdateVO.setVerifyMd5(getMoneyUpdateVO.toMD5Str());
        assertNotNull(userGetMoneyService.updateGetMoneyFinish(getMoneyUpdateVO).getResult());
    }

}
