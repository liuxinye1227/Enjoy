package com.facishare.pay.business.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;

/**
 * 操作日志
 * @author guom
 * @date 2015/10/29
 */
public interface OperateLogDAO {
    
    /**
     * 添加操作日志
     * @param OperateLogDo operateLog
     * @return int
     * */
    int addOperateLog(OperateLogDo operateLog);

    /**
     * 查询操作日志
     * @param Pager<OperateLogDo> page
     * @param OperateLogVO operateLogVO
     * @return List<OperateLogDo>
     * */
    Pager<OperateLogDo> queryOperateLog(Pager<OperateLogDo> page, OperateLogVO operateLogVO);
}
