package com.facishare.pay.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.manage.impl.OperateLogManageImpl;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;
import com.facishare.pay.business.service.OperateLogService;
import com.facishare.pay.common.result.ModelResult;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogManageImpl operateLogManageImpl;
    
    @Override
    public ModelResult<Pager<OperateLogDo>> queryOperateLog(
            Pager<OperateLogDo> page, OperateLogVO operateLogVO) {
        return operateLogManageImpl.queryOperateLog(page, operateLogVO);
    }

}
