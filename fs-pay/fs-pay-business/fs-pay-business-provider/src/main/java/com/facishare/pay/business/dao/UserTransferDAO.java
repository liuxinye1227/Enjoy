package com.facishare.pay.business.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.TransferStatus;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.model.vo.TransferSearchVO;

import java.util.Calendar;

/**
 * 转账流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserTransferDAO {

    /**
     * 转账流水
     * @param  page 分页标签
     * @param  transferSearchVo 查询条件
     * @return Pager<UserTransferDo>结果集
     * */
    Pager<UserTransferDo> queryUserTransfer(Pager<UserTransferDo> page, final TransferSearchVO transferSearchVo);

    /**
     * 添加用户转账记录
     * @param userTransferDo
     * @return
     */
    UserTransferDo addUserTransferDo(UserTransferDo userTransferDo);


    /**
     *
     * @param orderNo
     * @param status
     * @param updateTime
     * @param oldStatus
     * @return
     */
    boolean updateUserTransfer(String orderNo, TransferStatus status, Calendar updateTime, TransferStatus oldStatus);
    
    /**
     * 转账流水
     * @param  String orderNo
     * @return UserTransferDo结果集
     * */
    UserTransferDo queryUserTransferByOrderNo(String orderNo);
    
    /**
     * 修改转账状态（后台使用）
     * @param Long id
     * @param TransferStatus status
     * @return boolean
     * */
    boolean updateTransferStatus(Long id, TransferStatus status);
}
