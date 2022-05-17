package com.facishare.pay.bill.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillResultDo;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BillResultServiceImplTest {
    
    @Autowired
    private BillResultServiceImpl bllResultServiceImpl;

    @Test
    public void testQueryBillResult() {
        ModelResult<Pager<BillResultDo>>  result = bllResultServiceImpl.queryBillResult(new Pager<BillResultDo>(), null, null);
        assertNotNull(result.getResult().getData());
    }

    @Test
    public void testDealBill() {
        bllResultServiceImpl.dealBill(getBeginCalendar(), getEndCalendar(), "", 2);
    }

    private static Calendar getBeginCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar = DateUtils.formatCalendar(calendar);
        return calendar;
    }
    
    private static Calendar getEndCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar = DateUtils.formatCalendar(calendar);
        return calendar;
    }
}
