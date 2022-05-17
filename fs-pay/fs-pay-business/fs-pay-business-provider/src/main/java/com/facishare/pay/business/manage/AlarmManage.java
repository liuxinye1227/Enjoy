package com.facishare.pay.business.manage;

import com.facishare.pay.business.utils.LevelType;
import com.facishare.pay.common.constants.TransTypeEnum;

import java.util.Calendar;

/**
 * Created by wangtao on 2015/10/30.
 */
public interface AlarmManage {

    /**
     * 报警，发送企信消息
     * @param orderNo
     * @param transType
     * @param errorMsg
     * @param requestInfo
     * @param currTime
     */
    void notifyError(String orderNo, LevelType level, TransTypeEnum transType, String errorMsg, String requestInfo,
        Calendar currTime);

}
