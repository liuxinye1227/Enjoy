package com.facishare.pay.business.manage;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;
import com.facishare.pay.common.result.ModelResult;

import java.util.List;

/**
 * 操作日志
 * @author guom
 * @date 2015/10/29
 */
public interface OperateLogManage {


    /**
     * 分页查询操作日志
     * @param page
     * @param operateLogVO
     * @return
     */
    ModelResult<Pager<OperateLogDo>> queryOperateLog(Pager<OperateLogDo> page, OperateLogVO operateLogVO);

    /**
     * 添加操作日志
     * @param operateLogDo
     */
    void addOperateLog(OperateLogDo operateLogDo);
}
