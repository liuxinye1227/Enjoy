package com.facishare.pay.bill.task;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facishare.pay.bill.constant.BillResultStatus;
import com.facishare.pay.bill.service.impl.BillResultServiceImpl;
import com.facishare.pay.common.utils.DateUtils;


/**
 * 定时对账
 * @author guom
 * */
@Component
public class BillCheckJob {
    
    public static final Logger logger = LoggerFactory.getLogger(BillCheckJob.class);
    
    @Autowired
    BillResultServiceImpl billResultServiceImpl;
    
    public void execute() {
        logger.info("定时对账任务开始");
        try {
            billResultServiceImpl.dealBill(getBeginCalendar(), getEndCalendar(), "", BillResultStatus.AUTO_BILL);
        } catch (Exception e) {
            logger.error("定时对账任务异常: {}", e.getMessage());
        }finally{
            logger.info("定时对账任务结束");
        }
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