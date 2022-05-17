package com.facishare.pay.business.service.impl;

import com.facishare.pay.bank.model.vo.UserBalanceVO;
import com.facishare.pay.bank.service.UserWalletWriteService;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.business.dao.UserCardInfoDAO;
import com.facishare.pay.business.dao.UserInfoDAO;
import com.facishare.pay.business.manage.AlarmManage;
import com.facishare.pay.business.manage.OperateLogManage;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.model.result.BillRuleResult;
import com.facishare.pay.business.model.vo.BillruleVO;
import com.facishare.pay.business.model.vo.UpdateChargeStatusVO;
import com.facishare.pay.business.model.vo.UserChargeVO;
import com.facishare.pay.business.service.UserCardInfoService;
import com.facishare.pay.business.utils.BillRulesUtils;
import com.facishare.pay.business.utils.LevelType;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.utils.MD5Util;
import com.facishare.pay.order.constant.OrderStatus;
import com.facishare.pay.order.constant.OrderType;
import com.facishare.pay.order.model.OrderDo;
import com.facishare.pay.order.service.OrderService;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.dao.UserChargeDAO;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;
import com.facishare.pay.business.service.UserChargeService;
import com.facishare.pay.common.result.ModelResult;

import java.util.Calendar;


/**
 * 充值流水
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserChargeServiceImpl extends BaseService implements UserChargeService {
    
    public static final Logger logger = LoggerFactory.getLogger(UserChargeServiceImpl.class);
    
    @Autowired
    private UserChargeDAO userChargeDao;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserWalletWriteService userWalletWriteService;

    @Autowired
    private OperateLogManage operateLogManage;

    @Autowired
    private AlarmManage alarmManage;

    @Autowired
    private UserCardInfoDAO userCardInfoDAO;

    @Override
    public ModelResult<Pager<UserChargeDo>> queryUserCharge(
            Pager<UserChargeDo> page, final ChargeSearchVO userChargeLogVo) {
        try {
            return new ModelResult<Pager<UserChargeDo>>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userChargeDao.queryUserCharge(page, userChargeLogVo));
        } 
        catch(Exception e) {
            logger.error("UserChargeLogVo: {}, 查询用户充值流水异常: {}", userChargeLogVo.toString(), e);
            return new ModelResult<Pager<UserChargeDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(),
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<UserChargeDo> charge(UserChargeVO userChargeVO) {

        try {

            // 1. 验证数据是否为空， md5
            if (userChargeVO == null || userChargeVO.isValidateParam()) {
                 return new ModelResult<UserChargeDo>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum
                    .PARAMS_ERROR.getErrorMessage());
            }

            // 2.验证 二次密码
            UserInfoDo userInfoDo = userInfoDAO.queryUserInfo(userChargeVO.getEnterpriseAccount(), userChargeVO
                .getFsUserId());
            if (!userChargeVO.getPassword().equals(MD5Util.MD5Encode(userInfoDo.getPassword()))) {
                 return new ModelResult<UserChargeDo>(BusinessCodeEnum.PAY_PASSWORD_ERROR.getErrorCode(), BusinessCodeEnum
                     .PAY_PASSWORD_ERROR.getErrorMessage());
            }
            // 验证 cardId 是否存在 TODO:接入第三方的时候  需要
            // userCardInfoDAO.queryUserCardInfoById(Long.valueOf(userChargeVO.getCardInfoId()));


            // 3. 生成订单号
            OrderDo orderDo = new OrderDo();
            orderDo.setEnterpriseAccount(userChargeVO.getEnterpriseAccount());
            orderDo.setCreateTime(userChargeVO.getCreateTime());
            orderDo.setFsUserId(userChargeVO.getFsUserId());
            orderDo.setAmount(userChargeVO.getAmount());
            orderDo.setOrderType(OrderType.CHARGE);
            orderDo.setStatus(OrderStatus.INIT_ORDER);
            ModelResult<OrderDo> result = orderService.addOrder(orderDo);

            if (!result.isSuccess()) {
                return new ModelResult<UserChargeDo>(result.getErrorCode(), result.getErrorMessage());
            }
            // 生成操作日志 充值的  CH_CREATE_ORDER
            operateLogManage.addOperateLog(
                new OperateLogDo(result.getResult().getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_CREATE_ORDER, new Gson().toJson(userChargeVO), Calendar.getInstance()));
            String chargeNo = this.getChargeNo();
            BillRuleResult billRuleResult = BillRulesUtils.getBillRule(new BillruleVO(userChargeVO.getAmount(),
                userChargeVO.getEnterpriseAccount(), userChargeVO.getFsUserId()));
            // 4. 生成充值流水 （状态为初始化）
            UserChargeDo userChargeDo = new UserChargeDo(
                result.getResult().getOrderNo(),userChargeVO.getEnterpriseAccount(),userChargeVO.getFsUserId(),
                chargeNo, userChargeVO.getChargeWay(), billRuleResult.getSysFee(), billRuleResult.getActualFee(),
                ChargeStatus.INIT_CHARGE, userChargeVO.getAmount(), userChargeVO.getChannelId(),
                userChargeVO.getCreateTime(), "");

            userChargeDao.addUserCharge(userChargeDo);

            // 生成操作日志 充值的  CH_CREATE_CHARGE
            operateLogManage.addOperateLog(
                new OperateLogDo(result.getResult().getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_CREATE_CHARGE, new Gson().toJson(userChargeVO), Calendar.getInstance()));

            // TODO:调用第三方接口充值
            // 5. 异步调用调用第三方接口充值


            // 生成操作日志 充值的  CH_SEND_THIRD
            operateLogManage.addOperateLog(
                new OperateLogDo(result.getResult().getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_SEND_THIRD, new Gson().toJson(userChargeVO), Calendar.getInstance()));
           return new ModelResult<>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage
               (), userChargeDo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("charge error:{}", e);
            return new ModelResult<UserChargeDo>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage());
        }
    }



    @Override
    public ModelResult<Boolean> updateChargeStatus(UpdateChargeStatusVO updateChargeStatusVO) {
        try {
            // 第三方回调，生成操作日志
            operateLogManage.addOperateLog(
                new OperateLogDo(updateChargeStatusVO.getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_BACK_THIRD, new Gson().toJson(updateChargeStatusVO), Calendar.getInstance()));

            // 1 判断第三方返回的是否成功 TODO: 判断第三方是否充值成功
            /*if (StringUtils.isNotBlank(updateChargeStatusVO.getFailReturnUrl())) {
                // 失败
                return new ModelResult(BusinessCodeEnum.THIRD_CHARGE_FAILD.getErrorCode(), BusinessCodeEnum
                    .THIRD_CHARGE_FAILD.getErrorMessage());
            }*/
            // 2.1 . 查询流水
            UserChargeDo userChargeDo = new UserChargeDo();
            userChargeDo.setOrderNo(updateChargeStatusVO.getOrderNo());
            userChargeDo.setStatus(updateChargeStatusVO.getChargeStatus().SUCCESS_CHARGE);
            userChargeDo = userChargeDao.getUserChargeByOrderNoAndStatus(userChargeDo);

            // 3 修改用户钱包余额
            UserBalanceVO userBalanceVO = new UserBalanceVO(userChargeDo.getActualFee().add(userChargeDo.getAmount()),
                userChargeDo.getEnterpriseAccount(), userChargeDo.getFsUserId(), TransTypeEnum.CHARGE,
                userChargeDo.getOrderNo(), userChargeDo.getSysFee(), userChargeDo.getAmount(), Calendar.getInstance(),
                BizTypeEnum.CHARGE);

            ModelResult<Boolean> result = userWalletWriteService.addUserAbleBalance(userBalanceVO);
            if (!result.isSuccess()) {
                //
                logger.error("call addUserAbleBalance error:{}", result);
                return new ModelResult<>(result.getErrorCode(), result.getErrorMessage());
            }

            // 用户余额修改了 ，记录下日志噢
            operateLogManage.addOperateLog(
                new OperateLogDo(updateChargeStatusVO.getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_WALLET_CHARGE, new Gson().toJson(updateChargeStatusVO), Calendar.getInstance()));
            // 4. 修改流水

            try {
                boolean chargeFlag = userChargeDao.updateUserCharge(updateChargeStatusVO);
                if (!chargeFlag) {
                    // 调用订单服务报错了 发送告警
                    alarmManage.notifyError(updateChargeStatusVO.getOrderNo(), LevelType.WARN, TransTypeEnum.CHARGE,
                        "充值流水返回false", new Gson().toJson(updateChargeStatusVO), Calendar.getInstance());
                }
            } catch (Exception e) {
                alarmManage.notifyError(updateChargeStatusVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.CHARGE,
                    "充值流水调用异常false", new Gson().toJson(updateChargeStatusVO), Calendar.getInstance());
            }

            // 4.1流水状态修改了，记录下日志
            operateLogManage.addOperateLog(
                new OperateLogDo(updateChargeStatusVO.getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_UPDATE_CHARGE, new Gson().toJson(updateChargeStatusVO), Calendar.getInstance()));

            // 5 修改订单状态

            try {

                ModelResult<Boolean> orderReulst = orderService.updateOrderStatus(updateChargeStatusVO.getOrderNo(), OrderStatus
                        .INIT_ORDER,
                    OrderStatus.ORDER_FINISH);
                if (!orderReulst.isSuccess()) {
                    // 调用订单服务报错了 发送告警
                    alarmManage.notifyError(updateChargeStatusVO.getOrderNo(), LevelType.WARN, TransTypeEnum.CHARGE,
                        "订单服务返回false", new Gson().toJson(updateChargeStatusVO), Calendar.getInstance());
                }
            } catch (Exception e) {
                // 调用订单服务报错了 发送告警
                alarmManage.notifyError(updateChargeStatusVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.CHARGE,
                    "订单服务调用异常false", new Gson().toJson(updateChargeStatusVO), Calendar.getInstance());

            }

            // 5.1 流水状态修改了，记录下日志 终于记完了
            operateLogManage.addOperateLog(
                new OperateLogDo(updateChargeStatusVO.getOrderNo(), BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                    OperateType.CH_UPDATE_ORDER, new Gson().toJson(updateChargeStatusVO), Calendar.getInstance()));

            if (result.isSuccess()) {
                return  new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                    .getErrorMessage());
            }
            return new ModelResult<>(BusinessCodeEnum.ADD_USERWALLET_FAILD.getErrorCode(), BusinessCodeEnum
                .ADD_USERWALLET_FAILD.getErrorMessage());
        } catch (Exception e) {
            logger.error("updateChargeStatus error:{}", e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

    @Override
    public ModelResult<UserChargeDo> getChargeByOrderNo(String orderNo) {

        try {
            UserChargeDo userChargeDo = userChargeDao.getUserChargeByOrderNo(orderNo);

            return new ModelResult<UserChargeDo>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(),
                userChargeDo);
        } catch (Exception e) {
            logger.error("getChargeByOrderNo error:{}", e);

            return new ModelResult<UserChargeDo>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateChargeStatus(Long id, ChargeStatus status) {
        if (id == null || status == null) {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR
                    .getErrorMessage(), false); 
        }
        try {
            return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), 
                    userChargeDao.updateChargeStatus(id, status));
        } catch (Exception e) {
            logger.error("updateChargeStatus error:{}", e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR
                .getErrorMessage());
        }
    }

}
