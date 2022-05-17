package com.facishare.open.manage.stat.dao;

import com.facishare.open.manage.kits.DateKit;
import com.facishare.open.manage.stat.model.enums.StatTypeEnum;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-open-app-center-manager.xml"})
public class AppStatDAOTest extends TestCase {

    @Autowired
    private AppStatDAO appStatDAO;

    @Test
    public void testQueryAllAppStat4SearchCount() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("statType", 1);
        System.out.println(appStatDAO.queryAllAppStat4SearchCount(parameters));
    }

    @Ignore
    @Test
    public void testQueryAllAppStat4SearchList() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("statType", 1);
        System.out.println(appStatDAO.queryAllAppStat4SearchList(parameters));
    }

    @Test
    public void testQueryByStatDateAndAppIdAndType() throws ParseException {
        String appId = "appid1";
        Integer statType = StatTypeEnum.EA_OPEN.getType();
        Date startDate = DateKit.date(DateKit.date("2015-09-25"));
        Date endDate = DateKit.addDay(startDate, 1);
        System.err.println(appStatDAO.queryDtlByAppIdAndStatDate(appId, startDate, endDate));
    }
}
