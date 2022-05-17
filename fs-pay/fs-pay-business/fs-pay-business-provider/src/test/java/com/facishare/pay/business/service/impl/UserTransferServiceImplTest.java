package com.facishare.pay.business.service.impl;

import static org.junit.Assert.*;

import com.facishare.pay.business.model.vo.TransferInitVO;
import com.facishare.pay.business.model.result.TransferResult;
import com.facishare.pay.business.model.vo.TransferVO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.service.UserTransferService;
import com.facishare.pay.common.result.ModelResult;

import java.math.BigDecimal;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserTransferServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserTransferService userTransferService;
    
    @Test
    public void testQueryUserTransfer() {
        ModelResult<Pager<UserTransferDo>> result = userTransferService.queryUserTransfer(new Pager<UserTransferDo>(), null);
        assertNotNull(result.getResult().getData());
        if (result.getResult().getData().size() > 0) {
            for (UserTransferDo userTransferLogDo : result.getResult().getData()) {
                System.out.println(userTransferLogDo.getId());
                System.out.println(userTransferLogDo.toString());
            }
        }
    }


    @Test
    public void testUserTransferInit() throws Exception {
        TransferInitVO transferInitVO = new TransferInitVO();
        transferInitVO.setFromEnterpriseAccount("fs");
        transferInitVO.setAmount(new BigDecimal(80));
        transferInitVO.setFromUserId(1008L);
        transferInitVO.setCreateTime(Calendar.getInstance());
        transferInitVO.setToEnterpriseAccount("fs");
        transferInitVO.setToUserId(1018L);
        ModelResult<TransferResult> result = userTransferService.userTransferInit(transferInitVO);
        assertEquals(result.getErrorCode(), 0);



    }

    @Test
    public void testUserTransferIn() throws Exception {
        TransferInitVO transferInitVO = new TransferInitVO();
        transferInitVO.setFromEnterpriseAccount("fs");
        transferInitVO.setAmount(new BigDecimal(80));
        transferInitVO.setFromUserId(1008L);
        transferInitVO.setCreateTime(Calendar.getInstance());
        transferInitVO.setToEnterpriseAccount("fs");
        transferInitVO.setToUserId(1018L);
        TransferVO transferVO = new TransferVO();
        transferVO.setCreateTime(Calendar.getInstance());
        transferVO.setToEnterpriseAccount("fs");
        transferVO.setAmount(new BigDecimal(80));
        transferVO.setOrderNo("");
        transferVO.setFsUserId(1008L);
        transferVO.setToFsUserId(1018L);
        ModelResult<Boolean> result = userTransferService.userTransferIn(transferVO);

    }

    @Test
    public void testUserTransferFail() throws Exception {
        TransferVO transferVO = new TransferVO();
        transferVO.setCreateTime(Calendar.getInstance());
        transferVO.setToEnterpriseAccount("fs");
        transferVO.setAmount(new BigDecimal(80));
        transferVO.setOrderNo("");
        transferVO.setFsUserId(1008L);
        transferVO.setToFsUserId(1018L);
        userTransferService.userTransferFail(transferVO);
    }
}
