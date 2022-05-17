package com.facishare.pay.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bank.model.vo.UserBalanceVO;
import com.facishare.pay.bank.service.UserWalletWriteService;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.constant.TransferStatus;
import com.facishare.pay.business.dao.UserTransferDAO;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.model.vo.TransferSearchVO;
import com.facishare.pay.business.service.UnFreezeUserWalletService;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.order.constant.OrderStatus;
import com.facishare.pay.order.service.OrderService;

@Service
public class UnFreezeUserWalletServiceImpl implements UnFreezeUserWalletService {
    
    public static final Logger logger = LoggerFactory.getLogger(UnFreezeUserWalletServiceImpl.class);

    @Autowired
    private UserTransferDAO userTransferDAO;
    
    @Autowired
    private UserWalletWriteService UserWalletWriteService;
    
    @Autowired
    private OrderService orderService;
    
    @Override
    public ModelResult<Boolean> unFreezeUserWalletTask(Calendar beginTime,
            Calendar endTime) {
        // 扫描转账过期未接收的金额到转账用户的钱包
        Pager<UserTransferDo> page = new Pager<UserTransferDo>();
        page.setPageSize(100);
        TransferSearchVO transferSearchVo = new TransferSearchVO();
        transferSearchVo.setStatus(TransferStatus.IN_TRANSFER);
        transferSearchVo.setBeginTime(beginTime);
        transferSearchVo.setEndTime(endTime);
        List<UserTransferDo> list = null;
        List<UserBalanceVO> userBalanceList = null;
        do {
            userBalanceList = new ArrayList<UserBalanceVO>();
            list = userTransferDAO.queryUserTransfer(page, transferSearchVo).getData();
            // 先修改状态为过期
            for (UserTransferDo userTransferDo : list) {
                try {
                    boolean flag = userTransferDAO.updateUserTransfer(userTransferDo.getOrderNo()
                            , TransferStatus.OUTTIME_TRANSFER, Calendar.getInstance(), TransferStatus.IN_TRANSFER);
                    if (flag) {
                        UserBalanceVO userBalanceVO = new UserBalanceVO(
                                userTransferDo.getAmount(), userTransferDo.getFromEnterpriseAccount(),
                                userTransferDo.getFromUserId(), TransTypeEnum.TRANSFER_UNFREEZE_ABLE, userTransferDo.getOrderNo(),
                                BigDecimal.ZERO, userTransferDo.getAmount(), Calendar.getInstance(), BizTypeEnum.TRANSFER
                            );
                        userBalanceList.add(userBalanceVO);
                        // 修改订单状态
                        orderService.updateOrderStatus(userTransferDo.getOrderNo(), OrderStatus.INIT_ORDER, OrderStatus.HAS_PAY_ORDER);
                    }
                } catch (Exception e) {
                    logger.error("修改状态异常：{}", e);
                }
            }
            if (userBalanceList.size() > 0) {
                try {
                    UserWalletWriteService.unfreezeUserBalanceToAble(userBalanceList);
                } catch (Exception e) {
                    logger.error("系统异常：{}", e);
                }
            }
        } while (list != null && list.size() > 0);
        return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), true);
    }

}
