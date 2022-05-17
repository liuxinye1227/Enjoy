package com.facishare.pay.business.task;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facishare.pay.business.service.impl.UnFreezeUserWalletServiceImpl;


/**
 * 定时对账
 * @author guom
 * */
@Component
public class TransferCheckJob {
    
    public static final Logger logger = LoggerFactory.getLogger(TransferCheckJob.class);
    
    @Autowired
    private UnFreezeUserWalletServiceImpl unFreezeUserWalletServiceImpl;
    
    public void execute() {
        logger.info("定时扫描转账任务开始");
        try {
            unFreezeUserWalletServiceImpl.unFreezeUserWalletTask(getBeginCalendar(), getEndCalendar());
        } catch (Exception e) {
            logger.error("定时扫描转账任务异常: {}", e.getMessage());
        }finally{
            logger.info("定时扫描转账任务结束");
        }
    }
    
    private static Calendar getBeginCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        return calendar;
    }
    
    private static Calendar getEndCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar;
    }
    
}