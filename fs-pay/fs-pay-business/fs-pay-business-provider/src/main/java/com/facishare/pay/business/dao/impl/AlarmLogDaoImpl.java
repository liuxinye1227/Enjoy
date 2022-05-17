package com.facishare.pay.business.dao.impl;

import com.facishare.pay.business.dao.AlarmLogDAO;
import com.facishare.pay.business.dao.OperateLogDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.AlarmLogDo;
import com.facishare.pay.business.model.OperateLogDo;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 2015/10/30.
 */

@Service
public class AlarmLogDaoImpl extends BusinessBaseDAO<AlarmLogDo> implements AlarmLogDAO {


    @Override
    public void addAlarmLog(AlarmLogDo alarmLogDo) {

        this.save("addAlarmLog", alarmLogDo);
    }

    @Override
    public boolean updateAlarmLog(Long id, Integer status) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("status", id);
        map.put("updateTime", Calendar.getInstance());
        return this.update("updateAlarmLog", map) == 1;
    }
}
