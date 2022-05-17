package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;
import com.facishare.pay.common.result.ModelResult;

/**
 * 操作日志
 * @author guom
 * @date 2015/10/29
 */
public interface OperateLogService {

    /**
     * 查询操作日志
     * @param OperateLogVO operateLogVO
     * @param Pager<OperateLogDo> page
     * @return ModelResult<Pager<OperateLogDo>>
     * */
    ModelResult<Pager<OperateLogDo>> queryOperateLog(Pager<OperateLogDo> page, OperateLogVO operateLogVO);
}
