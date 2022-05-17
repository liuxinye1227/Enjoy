package com.facishare.pay.business.service.impl;

import com.facishare.pay.bank.model.vo.UserBalanceTransferVO;
import com.facishare.pay.bank.model.vo.UserBalanceVO;
import com.facishare.pay.bank.service.UserWalletWriteService;
import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.business.constant.TransferStatus;
import com.facishare.pay.business.dao.UserInfoDAO;
import com.facishare.pay.business.manage.AlarmManage;
import com.facishare.pay.business.manage.OperateLogManage;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.model.vo.TransferInitVO;
import com.facishare.pay.business.model.result.TransferResult;
import com.facishare.pay.business.model.vo.TransferVO;
import com.facishare.pay.business.utils.LevelType;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.utils.MD5Util;
import com.facishare.pay.order.constant.OrderStatus;
import com.facishare.pay.order.constant.OrderType;
import com.facishare.pay.order.model.OrderDo;
import com.facishare.pay.order.service.OrderService;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.dao.UserTransferDAO;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.model.vo.TransferSearchVO;
import com.facishare.pay.business.service.UserTransferService;
import com.facishare.pay.common.result.ModelResult;

import java.math.BigDecimal;
import java.util.Calendar;


/**
 * 转账流水
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserTransferServiceImpl implements UserTransferService {
    
    public static final Logger logger = LoggerFactory.getLogger(UserTransferServiceImpl.class);
    
    @Autowired
    private UserTransferDAO userTransferDAO;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserWalletWriteService userWalletWriteService;

    @Autowired
    private OperateLogManage operateLogManage;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private AlarmManage alarmManage;

    @Override
    public ModelResult<Pager<UserTransferDo>> queryUserTransfer(
            Pager<UserTransferDo> page, final TransferSearchVO userTransferLogVo) {
        try {
            return new ModelResult<Pager<UserTransferDo>>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userTransferDAO.queryUserTransfer(page,
                userTransferLogVo));
        } catch(Exception e) {
            logger.error("UserTransferLogVo: {}, 查询用户转账流水异常: {}", userTransferLogVo.toString(), e);
            return new ModelResult<Pager<UserTransferDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<TransferResult> userTransferInit(TransferInitVO transferInitVO) {

        try {
            // 入口验证数据合法性
            if (transferInitVO == null == !transferInitVO.isValidateParam()) {
                return  new ModelResult<TransferResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum
                    .PARAMS_ERROR.getErrorMessage());
            }

            // 2.验证 二次密码
            UserInfoDo userInfoDo = userInfoDAO.queryUserInfo(transferInitVO.getFromEnterpriseAccount(), transferInitVO
                .getFromUserId());
            if (!transferInitVO.getPassword().equals(MD5Util.MD5Encode(userInfoDo.getPassword()))) {
                return new ModelResult<TransferResult>(BusinessCodeEnum.PAY_PASSWORD_ERROR.getErrorCode(), BusinessCodeEnum
                    .PAY_PASSWORD_ERROR.getErrorMessage());
            }
            // 1. 生成订单
            OrderDo orderDo = new OrderDo();
            orderDo.setEnterpriseAccount(transferInitVO.getFromEnterpriseAccount());
            orderDo.setFsUserId(transferInitVO.getFromUserId());
            orderDo.setStatus(OrderStatus.INIT_ORDER);
            orderDo.setOrderType(OrderType.TRANSFER);
            orderDo.setAmount(transferInitVO.getAmount());
            orderDo.setCreateTime(transferInitVO.getCreateTime());
            orderDo.setRemark("freeze able");
            ModelResult<OrderDo> orderResult = orderService.addOrder(orderDo);
            if (!orderResult.isSuccess()) {
                return new ModelResult<TransferResult>(orderResult.getErrorCode(), orderResult.getErrorMessage());
            }
            // 1.1 生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(orderResult.getResult().getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_INIT,
                    OperateType.TR_CREATE_ORDER, new Gson().toJson(transferInitVO), Calendar.getInstance()));
            // 2. 生成转账流水
            UserTransferDo userTransferDo = new UserTransferDo();
            userTransferDo.setCreateTime(transferInitVO.getCreateTime());
            userTransferDo.setAmount(transferInitVO.getAmount());
            userTransferDo.setFromEnterpriseAccount(transferInitVO.getFromEnterpriseAccount());
            userTransferDo.setFromUserId(transferInitVO.getFromUserId());
            userTransferDo.setOrderNo(orderResult.getResult().getOrderNo());
            userTransferDo.setStatus(TransferStatus.IN_TRANSFER);
            userTransferDo.setToEnterpriseAccount(transferInitVO.getToEnterpriseAccount());
            userTransferDo.setToUserId(transferInitVO.getToUserId());
            userTransferDAO.addUserTransferDo(userTransferDo);
            // 2.1 生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(orderResult.getResult().getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_INIT,
                    OperateType.TR_CREATE_TRANSFER, new Gson().toJson(transferInitVO), Calendar.getInstance()));
            // 3. 冻结用户余额
            UserBalanceVO userBalanceVO = new UserBalanceVO(transferInitVO.getAmount(), transferInitVO.getFromEnterpriseAccount(), transferInitVO.getFromUserId(), TransTypeEnum.TRANSFER_INIT,
                orderResult.getResult().getOrderNo(), BigDecimal.ZERO, transferInitVO.getAmount(), transferInitVO
                .getCreateTime(), BizTypeEnum.TRANSFER);

            ModelResult<Boolean> balanceResult = userWalletWriteService.freezeUserBalance(userBalanceVO);
            if (!balanceResult.isSuccess()) {
                logger.error("call freezeUserBalance error :{}", balanceResult);

                return new ModelResult<TransferResult>(balanceResult.getErrorCode(), balanceResult.getErrorMessage());
            }

            // 3.1 生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(orderResult.getResult().getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_INIT,
                    OperateType.TR_FREEZE_MONEY, new Gson().toJson(transferInitVO), Calendar.getInstance()));

            return new ModelResult<>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS
                .getErrorMessage(), new TransferResult(orderResult.getResult().getOrderNo()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("userTransferInit error:[{}]", e);
            return new ModelResult<>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

    /**
     * 转账成功 解冻
     * @param transferVO
     * @return
     */
    @Override
    public ModelResult<Boolean> userTransferIn(TransferVO transferVO) {

        if (transferVO == null || !transferVO.isValidateParam()) {
            return  new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum
                .PARAMS_ERROR.getErrorMessage());
        }
        try {
            // 主人接收转账了 生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(transferVO.getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_IN,
                    OperateType.TR_TRANSFER_IN, new Gson().toJson(transferVO), Calendar.getInstance()));
           // 1 直接转账
            UserBalanceTransferVO userBalanceTransferVO =
                new UserBalanceTransferVO(transferVO.getAmount(), transferVO.getEnterpriseAccount(),
                    transferVO.getFsUserId(), transferVO.getToEnterpriseAccount(), transferVO.getToFsUserId(), true,
                    TransTypeEnum.TRANSFER_UNFREEZE_USED, BizTypeEnum.TRANSFER, transferVO.getOrderNo(),
                    transferVO.getCreateTime());

            ModelResult<Boolean> result =  userWalletWriteService.transferUserBalance(userBalanceTransferVO);
            if (!result.isSuccess()) {
                // 调用资金服务 报错 ，直接返回
                logger.error("call transferUserBalance return error:[{}]", result);
                return new ModelResult<Boolean>(result.getErrorCode(), result.getErrorMessage());
            }
            // 1.1 生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(transferVO.getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_IN,
                    OperateType.TR_TRANSFER_MONEY, new Gson().toJson(transferVO), Calendar.getInstance()));

            // 2. 修改转账流水状态
            try {
                boolean flag = userTransferDAO.updateUserTransfer(transferVO.getOrderNo(),
                    TransferStatus.FINISH_TRANSFER, transferVO.getCreateTime(), TransferStatus.IN_TRANSFER);
                if (!flag) {
                    // 转账流水报错了 发送告警
                    alarmManage.notifyError(transferVO.getOrderNo(), LevelType.WARN, TransTypeEnum.TRANSFER_IN,
                        "充值流水返回false", new Gson().toJson(transferVO), Calendar.getInstance());
                }
            } catch (Exception e) {
                alarmManage.notifyError(transferVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.TRANSFER_IN,
                    "充值流水调用异常false", new Gson().toJson(transferVO), Calendar.getInstance());
            }


            // 2.1 千钱都转过去了  生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(transferVO.getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_IN,
                    OperateType.TR_UPDATE_TRANSFER, new Gson().toJson(transferVO), Calendar.getInstance()));

            // 3. 修改订单
            try {
                ModelResult<Boolean> orderResult = orderService.updateOrderStatus(transferVO.getOrderNo(),
                    OrderStatus.INIT_ORDER, OrderStatus.ORDER_FINISH);
                if (!orderResult.isSuccess()) {
                    // 转账流水报错了 发送告警
                    alarmManage.notifyError(transferVO.getOrderNo(), LevelType.WARN, TransTypeEnum.TRANSFER_IN,
                        "订单服务返回false", new Gson().toJson(transferVO), Calendar.getInstance());
                }
            } catch (Exception e) {
                alarmManage.notifyError(transferVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.TRANSFER_IN,
                    "订单服务调用异常", new Gson().toJson(transferVO), Calendar.getInstance());
            }


            // 3.1 最后一步了  任务完成了
            operateLogManage.addOperateLog(
                new OperateLogDo(transferVO.getOrderNo(), BizTypeEnum.TRANSFER, TransTypeEnum.TRANSFER_IN,
                    OperateType.TR_UPDATE_ORDER, new Gson().toJson(transferVO), Calendar.getInstance()));
            return new ModelResult<>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS
                .getErrorMessage(), true);
        } catch (Exception e) {
            logger.error("userTransferIn error:[{}]", e);

            return new ModelResult<>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

    @Override
    public ModelResult<Boolean> userTransferFail(TransferVO transferVO) {
        try {
            if (transferVO == null || !transferVO.isValidateParam()) {
                return  new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum
                    .PARAMS_ERROR.getErrorMessage());
            }
            // 1. 修改转账流水状态

            userTransferDAO.updateUserTransfer(transferVO.getOrderNo(), TransferStatus.OUTTIME_TRANSFER, transferVO
                .getCreateTime(), TransferStatus.IN_TRANSFER);

            // 2. 修改订单
            ModelResult<Boolean>  result = orderService.updateOrderStatus(transferVO.getOrderNo(), OrderStatus
                    .INIT_ORDER, OrderStatus.HAS_PAY_ORDER);

            // 3. 解冻用户余额
            UserBalanceVO userBalanceVO =
                new UserBalanceVO(transferVO.getAmount(), transferVO.getEnterpriseAccount(), transferVO.getFsUserId(),
                    TransTypeEnum.TRANSFER_UNFREEZE_USED, transferVO.getOrderNo(), BigDecimal.ZERO,
                    transferVO.getAmount(), transferVO.getCreateTime(), BizTypeEnum.TRANSFER);

            result = userWalletWriteService.unfreezeUserBalanceToAble(userBalanceVO);


            return new ModelResult<>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS
                .getErrorMessage(), true);
        } catch (Exception e) {
            logger.error("userTransferIn error:[{}]", e);

            return new ModelResult<>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

    @Override
    public ModelResult<UserTransferDo> queryUserTransferByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return new ModelResult<UserTransferDo>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), 
                    BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null); 
        }
        try {
            return new ModelResult<UserTransferDo>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userTransferDAO.queryUserTransferByOrderNo(orderNo));
        } catch (Exception e) {
            logger.error("orderNo: {}, 查询用户提现流水异常: {}", orderNo, e);
            return new ModelResult<UserTransferDo>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<Boolean> updateTransferStatus(Long id, TransferStatus status) {
        if (id == null || status == null) {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR
                    .getErrorMessage(), false); 
        }
        try {
            return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userTransferDAO.updateTransferStatus(id, status));
        }  catch (Exception e) {
            logger.error("id: {}, TransferStatus: {}, updateTransferStatus error: {}", id, status, e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage());
        }
    }



}
