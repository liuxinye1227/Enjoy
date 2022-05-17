package com.facishare.pay.business.dao.impl;

import com.facishare.pay.business.dao.AlarmLogDAO;
import com.facishare.pay.business.model.AlarmLogDo;
import com.facishare.pay.common.constants.TransTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:db/spring-db.xml")
public class AlarmLogDaoImplTest {

    @Autowired
    AlarmLogDAO alarmLogDAO;
    @Test
    public void testAddAlarmLog() throws Exception {
        AlarmLogDo alarmLogDo = new AlarmLogDo();
        alarmLogDo.setCreateTime(Calendar.getInstance());
        alarmLogDo.setErrorMsg("errorMsg");
        alarmLogDo.setOrderNo("111");
        alarmLogDo.setRequestInfo("request");
        alarmLogDo.setStatus(0);
        alarmLogDo.setTransType(TransTypeEnum.CHARGE.getCode()+"");
        alarmLogDo.setType("WARN");
        alarmLogDAO.addAlarmLog(alarmLogDo);
    }
}
