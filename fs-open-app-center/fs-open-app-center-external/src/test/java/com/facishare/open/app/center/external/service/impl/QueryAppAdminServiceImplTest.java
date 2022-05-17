package com.facishare.open.app.center.external.service.impl;

import com.facishare.open.app.center.api.service.QueryAppAdminService;
import com.facishare.open.common.model.FsUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * test.
 * Created by zenglb on 2016/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-remote-dubbo.xml"})
public class QueryAppAdminServiceImplTest {

    @Resource
    private QueryAppAdminService queryAppAdminService;

    @Test
    public void testIsAppAdmin() throws Exception {
        System.out.println("xxxx ==== " + queryAppAdminService.isAppAdmin(new FsUserVO("fsfte2a", 1001), "FSAID_5f5e227"));
    }
}