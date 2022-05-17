package com.facishare.pay.business.dao;

import com.facishare.pay.business.model.AlarmLogDo;

/**
 * Created by wangtao on 2015/10/30.
 */
public interface AlarmLogDAO {

    /**
     * 添加报警日志
     * @param alarmLogDo
     */
    void addAlarmLog(AlarmLogDo alarmLogDo);

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    boolean updateAlarmLog(Long id, Integer status);
}
