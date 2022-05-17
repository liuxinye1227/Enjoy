package com.facishare.open.manage.stat.manager;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.kits.DateKit;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-open-app-center-manager.xml"})
public class AppStatManagerTest extends TestCase {

    @Autowired
    private AppStatManager appStatManager;

    @Test
    public void testQueryAllAppStat4Search() {
        Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
        System.out.println(appStatManager.queryAllAppStat4Search(pager));
    }

    @Test
    public void testQueryDtlByAppId() {
        String appId = "appid1";
        Date endDate = DateKit.now();
        Date startDate = DateKit.addDay(endDate, -7);
        System.out.println(appStatManager.queryDtlByAppId(appId, startDate, endDate));
    }
}
