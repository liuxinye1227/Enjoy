package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.TransferStatus;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.model.vo.TransferInitVO;
import com.facishare.pay.business.model.result.TransferResult;
import com.facishare.pay.business.model.vo.TransferSearchVO;
import com.facishare.pay.business.model.vo.TransferVO;
import com.facishare.pay.common.result.ModelResult;

/**
 * 转账流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserTransferService {

    /**
     * 转账流水
     * @param Pager<UserTransferLogDo> page 分页标签
     * @param TransferSearchVO userTransferLogVo查询条件
     * @return ModelResult<Pager<UserTransferLogDo>>结果集
     * */
    ModelResult<Pager<UserTransferDo>> queryUserTransfer(Pager<UserTransferDo> page, final TransferSearchVO userTransferLogVo);


    /**
     * 用户发起转账
     * @param transferInitVO
     * @return
     */
    ModelResult<TransferResult> userTransferInit(TransferInitVO transferInitVO);

    /**
     * 用户转账 A从解冻金额到已用  B 添加可用金额
     * @return
     */
    ModelResult<Boolean> userTransferIn(TransferVO transferVO);

    /**
     * 用户转账失败，冻结金额解冻到可用
     * @param transferVO
     * @return
     */
    ModelResult<Boolean> userTransferFail(TransferVO transferVO);
    
    /**
     * 用户转账失败，冻结金额解冻到可用
     * @param String orderNo
     * @return ModelResult<UserTransferDo>
     */
    ModelResult<UserTransferDo> queryUserTransferByOrderNo(String orderNo);
    
    /**
     * 修改转账状态（后台使用）
     * @param Long id
     * @param TransferStatus status
     * @return ModelResult<Boolean>
     * */
    ModelResult<Boolean> updateTransferStatus(Long id, TransferStatus status);
}
