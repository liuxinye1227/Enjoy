package com.facishare.open.manage.manager.impl;

import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.OperationLogManager;
import com.facishare.open.operating.center.api.model.OperationLogVO;
import com.facishare.open.operating.center.api.model.QueryOperationLogVO;
import com.facishare.open.operating.center.api.service.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huyue on 2016/8/16.
 */

@Service
public class OperationLogManagerImpl implements OperationLogManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private OperationLogService operationLogService;

    /**
     * 读取日志事件
     *
     * @param pager             分页
     * @param queryOperationLogVO 查询对象
     * @return
     */
    @Override
    public Pager<OperationLogVO> findOperationLog(Pager<OperationLogVO> pager, QueryOperationLogVO queryOperationLogVO) {
        BaseResult<Pager<OperationLogVO>> operationLogResult = operationLogService.searchOperationLogByConditions(pager, queryOperationLogVO);
        if (!operationLogResult.isSuccess()) {
            logger.warn("failed to call operationLogService.searchOperationLogByConditions." +
                    "pager[{}], queryOperationLogVO[{}]", pager, queryOperationLogVO);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, operationLogResult, "应用中心后台读取操作日志事件失败！");
        }
        return operationLogResult.getResult();
    }
}
