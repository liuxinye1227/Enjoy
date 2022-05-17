package com.facishare.open.dao;

import com.facishare.open.app.center.api.utils.JsonKit;
import com.facishare.open.manage.dao.AppIconDAO;
import com.facishare.open.manage.model.AppIconDO;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzengyong
 * @date on 2017/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common-dao.xml"})
public class AppIconDAOTest {

    @Resource
    private AppIconDAO appIconDAO;


    @Test
    public void queryByAppIds(){

        String appIds = "FSAID_10b07604,FSAID_10b0760b";
        List<String> strings = Arrays.asList(appIds.split(","));
        System.out.println(strings);

//        
//        ArrayList<String> stringArrayList = Lists.newArrayList("FSAID_10b07604", "FSAID_10b07610");
//        List<AppIconDO> appIconDOs = appIconDAO.queryByAppIds(stringArrayList);
//        System.out.println(JsonKit.object2json(appIconDOs));
    }

}
