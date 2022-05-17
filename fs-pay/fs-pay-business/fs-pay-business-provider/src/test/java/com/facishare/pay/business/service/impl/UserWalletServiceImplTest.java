package com.facishare.pay.business.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bank.model.UserWalletDO;
import com.facishare.pay.bank.model.UserWalletLogDO;
import com.facishare.pay.business.service.UserWalletService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserWalletServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserWalletService UserWalletService;
    
    @Test
    public void testQueryUserWalletStringLong() {
        UserWalletDO userWalletDO = UserWalletService.queryUserWallet("fs", 1048l).getResult();
        assertNotNull(userWalletDO);
        System.out.println(userWalletDO.toString());
    }

    @Test
    public void testQueryUserWalletPagerOfUserWalletLogDOUserWalletLogVO() {
        Pager<UserWalletLogDO> page = UserWalletService.queryUserWallet(new Pager<UserWalletLogDO>(), null).getResult();
        assertNotNull(page);
        for (UserWalletLogDO uDo : page.getData()) {
            System.out.println(uDo.toString());
        }
    }

}
