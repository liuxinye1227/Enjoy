package com.facishare.pay.business.service;

import java.util.Calendar;

import com.facishare.pay.common.result.ModelResult;

/**
 * 系统定时扫描解冻
 * @author guom
 * @date 2015/10/27
 */
public interface UnFreezeUserWalletService {

    /**
     * 解冻 某个时间范围内的资金 
     * 自动解冻：比如进行解冻时间为2015-10-28 1:00:00那么beginTime: 2015-10-25 1:00:00 endTime:2015-10-27 1:00:00
     * @param Calendar beginTime 开始时间
     * @param Calendar endTime 结束时间
     * @return ModelResult<Boolean>
     */
    ModelResult<Boolean> unFreezeUserWalletTask(Calendar beginTime, Calendar endTime);
    
}
