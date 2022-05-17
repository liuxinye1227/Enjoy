package com.facishare.open.manage.manager;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.operating.center.api.model.OperationLogVO;
import com.facishare.open.operating.center.api.model.QueryOperationLogVO;

/**
 * Created by huyue on 2016/8/16.
 */
public interface OperationLogManager {

    /**
     * 读取日志事件
     * @param pager  分页
     * @param queryOperationLogVO  查询对象
     * @return
     */
    Pager<OperationLogVO> findOperationLog(Pager<OperationLogVO> pager, QueryOperationLogVO queryOperationLogVO);
}
