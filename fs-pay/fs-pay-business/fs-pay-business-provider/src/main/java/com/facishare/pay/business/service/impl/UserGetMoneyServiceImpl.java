package com.facishare.pay.business.service.impl;

import java.util.Calendar;

import com.facishare.pay.business.manage.OperateLogManage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bank.model.UserWalletDO;
import com.facishare.pay.bank.model.UserWalletLogDO;
import com.facishare.pay.bank.model.vo.UserBalanceVO;
import com.facishare.pay.bank.model.vo.UserVO;
import com.facishare.pay.bank.model.vo.UserWalletLogVO;
import com.facishare.pay.bank.service.UserWalletReadService;
import com.facishare.pay.bank.service.UserWalletWriteService;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.business.dao.UserCardInfoDAO;
import com.facishare.pay.business.dao.UserGetMoneyDAO;
import com.facishare.pay.business.dao.UserInfoDAO;
import com.facishare.pay.business.manage.AlarmManage;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.business.model.UserGetMoneyDo;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.model.result.BillRuleResult;
import com.facishare.pay.business.model.result.GetMoneyResult;
import com.facishare.pay.business.model.result.GetMoneyUpdateResult;
import com.facishare.pay.business.model.vo.BillruleVO;
import com.facishare.pay.business.model.vo.GetMoneySearchVO;
import com.facishare.pay.business.model.vo.GetMoneyUpdateVO;
import com.facishare.pay.business.model.vo.UserGetMoneyVO;
import com.facishare.pay.business.service.UserGetMoneyService;
import com.facishare.pay.business.utils.BillRulesUtils;
import com.facishare.pay.business.utils.LevelType;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.MD5Util;
import com.facishare.pay.order.constant.OrderStatus;
import com.facishare.pay.order.constant.OrderType;
import com.facishare.pay.order.model.OrderDo;
import com.facishare.pay.order.service.OrderService;
import com.google.gson.Gson;


/**
 * 提现流水
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserGetMoneyServiceImpl implements UserGetMoneyService {
    
    public static final Logger logger = LoggerFactory.getLogger(UserGetMoneyServiceImpl.class);
    
    @Autowired
    private UserGetMoneyDAO userGetMoneyDAO;
    
    @Autowired
    private UserWalletReadService userWalletReadService;
    
    @Autowired
    private UserWalletWriteService userWalletWriteService;
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserCardInfoDAO UserCardInfoDao;
    
    @Autowired
    private OperateLogManage operateLogManage;
    
    @Autowired
    private UserInfoDAO userInfoDAO;
    
    @Autowired
    private AlarmManage alarmManage;
    
    @Override
    public ModelResult<Pager<UserGetMoneyDo>> queryUserGetMoney(Pager<UserGetMoneyDo> page
            , GetMoneySearchVO userGetMoneyLogVo) {
        try {
            return new ModelResult<Pager<UserGetMoneyDo>>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userGetMoneyDAO.queryUserGetMoney(page, userGetMoneyLogVo));
        } catch (Exception e) {
            logger.error("UserGetMoneyLogVo: {}, 查询用户提现流水异常: {}", userGetMoneyLogVo.toString(), e);
            return new ModelResult<Pager<UserGetMoneyDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }
    
    /**
     * 申请只能重做
     * */
    @Override
    public ModelResult<GetMoneyResult> getMoneyApply(UserGetMoneyVO userGetMoneyVO) {
        // 验证信息是否被串改
        if (userGetMoneyVO == null || !userGetMoneyVO.isValidateParam()) {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 2.验证 二次密码
        UserInfoDo userInfoDo = userInfoDAO.queryUserInfo(userGetMoneyVO.getEnterpriseAccount(), userGetMoneyVO
            .getFsUserId());
        if (!MD5Util.MD5Encode(userGetMoneyVO.getPassword()).equals(userInfoDo.getPassword())) {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.PAY_PASSWORD_ERROR.getErrorCode(), BusinessCodeEnum
                .PAY_PASSWORD_ERROR.getErrorMessage());
       }
        
        BillruleVO billRuleVo = new BillruleVO(userGetMoneyVO.getAmount()
                , userGetMoneyVO.getEnterpriseAccount(), userGetMoneyVO.getFsUserId());
        // 手续费计算
        BillRuleResult ruleResult = BillRulesUtils.getBillRule(billRuleVo);
        // 获取用户提款的卡号
        UserCardInfoDo userCardInfoDo = UserCardInfoDao.queryUserCardInfoById(userGetMoneyVO.getCardInfoId());
        if (userCardInfoDo == null) {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 查询用户可用余额
        UserVO userVo = new UserVO();
        userVo.setEnterpriseAccount(userGetMoneyVO.getEnterpriseAccount());
        userVo.setFsUserId(userGetMoneyVO.getFsUserId());
        UserWalletDO userWalletDo = userWalletReadService.getUserWalletById(userVo).getResult();
        // 如果不存着该用户，或者用户的可用余额小于提现余额 
        if (userWalletDo == null || userWalletDo.getAbleBalance().doubleValue() < userGetMoneyVO.getAmount().add(ruleResult.getSysFee()).doubleValue()) {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.NO_ENOUGH_MONEY.getErrorCode()
                    , BusinessCodeEnum.NO_ENOUGH_MONEY.getErrorMessage(), null);
        } 
        // 生成订单号
        OrderDo orderDo = new OrderDo(userGetMoneyVO.getEnterpriseAccount(), userGetMoneyVO.getFsUserId()
                , OrderType.DEPOSIT, userGetMoneyVO.getAmount(), userGetMoneyVO.getCreateTime(), OrderStatus.INIT_ORDER, userGetMoneyVO.getCreateTime());
        orderDo = orderService.addOrder(orderDo).getResult();
        if (orderDo == null) {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode()
                    , BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
        // 添加操作日志
        operateLogManage.addOperateLog(new OperateLogDo(orderDo.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_FREEZE
                , OperateType.GM_CREATE_ORDER, orderDo.toString(), Calendar.getInstance()));
        // 生成提现申请
        UserGetMoneyDo userGetMoneyDo = new UserGetMoneyDo();
        userGetMoneyDo.setAmount(userGetMoneyVO.getAmount());
        userGetMoneyDo.setEnterpriseAccount(userGetMoneyVO.getEnterpriseAccount());
        userGetMoneyDo.setFsUserId(userGetMoneyVO.getFsUserId());
        userGetMoneyDo.setCreateTime(userGetMoneyVO.getCreateTime());
        userGetMoneyDo.setOrderNo(orderDo.getOrderNo());
        userGetMoneyDo.setFee(ruleResult.getSysFee());
        userGetMoneyDo.setActualFee(ruleResult.getActualFee());
        userGetMoneyDo.setBankNo(userCardInfoDo.getBankNo());
        userGetMoneyDo.setBankName(userCardInfoDo.getBankName());
        userGetMoneyDo.setBankBranchName(userCardInfoDo.getBankBranchName());
        userGetMoneyDo.setCardNo(userCardInfoDo.getCardNo());
        userGetMoneyDo.setFee(ruleResult.getSysFee());
        userGetMoneyDo.setActualFee(ruleResult.getActualFee());
        userGetMoneyDo.setStatus(GetMoneyStatus.AUDITING);
        if (this.addUserGetMoney(userGetMoneyDo) == 1) {
            // 添加操作日志
            operateLogManage.addOperateLog(new OperateLogDo(orderDo.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_FREEZE
                    , OperateType.GM_CREATE_APPLY, userGetMoneyDo.toString(), Calendar.getInstance()));
            // 冻结余额
            UserBalanceVO userBalance = new UserBalanceVO(userGetMoneyVO.getAmount().add(ruleResult.getSysFee()), userGetMoneyVO.getEnterpriseAccount()
                    , userGetMoneyVO.getFsUserId(), TransTypeEnum.GETMONEY_FREEZE, orderDo.getOrderNo()
                    , ruleResult.getSysFee(), userGetMoneyVO.getAmount()
                    , userGetMoneyVO.getCreateTime() == null ? Calendar.getInstance() : userGetMoneyVO.getCreateTime(), BizTypeEnum.GETMONEY);
            if (userWalletWriteService.freezeUserBalance(userBalance).getResult()) {
                // 添加操作日志
                operateLogManage.addOperateLog(new OperateLogDo(orderDo.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_FREEZE
                        , OperateType.GM_FREEZE_MONEY, userBalance.toString(), Calendar.getInstance()));
                // 自动提现就调用第三方进行提现
                return new ModelResult<GetMoneyResult>(BusinessCodeEnum.SUCCESS.getErrorCode()
                        , BusinessCodeEnum.SUCCESS.getErrorMessage(), new GetMoneyResult());
            } else {
                return new ModelResult<GetMoneyResult>(BusinessCodeEnum.ADD_TO_FREEZE_ERROR.getErrorCode()
                        , BusinessCodeEnum.ADD_TO_FREEZE_ERROR.getErrorMessage(), null);
            }
        } else {
            return new ModelResult<GetMoneyResult>(BusinessCodeEnum.ADD_USERGETMONEY_ERROR.getErrorCode()
                    , BusinessCodeEnum.ADD_USERGETMONEY_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<GetMoneyUpdateResult> updateGetMoneyAuditor(GetMoneyUpdateVO getMoneyUpdateVO) {
        // 验证信息是否被串改
        if (getMoneyUpdateVO == null || !getMoneyUpdateVO.isValidateParam()) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 审核 只进行审核和不通过两种状态的
        if (StringUtils.isBlank(getMoneyUpdateVO.getOrderNo()) 
                || (getMoneyUpdateVO.getGetMoneyStatus().getIndex() != GetMoneyStatus.NO_PASS.getIndex()
                    && 
                    getMoneyUpdateVO.getGetMoneyStatus().getIndex() != GetMoneyStatus.AUDITED.getIndex()
                )) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 查询是否有资金冻结流水
        UserWalletLogVO userWalletLogVO = new UserWalletLogVO();
        userWalletLogVO.setOrderNo(getMoneyUpdateVO.getOrderNo());
        userWalletLogVO.setFsUserId(getMoneyUpdateVO.getFsUserId());
        userWalletLogVO.setEnterpriseAccount(getMoneyUpdateVO.getEnterpriseAccount());
        Pager<UserWalletLogDO> page = userWalletReadService.queryUserWalletLogList(new Pager<UserWalletLogDO>(), userWalletLogVO).getResult();
        if (page == null || page.getRecordSize() == 0) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorCode(), 
                    BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorMessage(), null);
        }
        UserWalletLogDO userWalletLogDO = page.getData().get(0);
        // 添加操作日志   开始审批
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_AUDITOR
                , OperateType.GM_START_AUDITOR, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        // 审批：不通过
        if (getMoneyUpdateVO.getGetMoneyStatus().getIndex() == GetMoneyStatus.NO_PASS.getIndex()) {
            //解冻资金转可用
            UserBalanceVO userBalance = new UserBalanceVO(userWalletLogDO.getAmount(), getMoneyUpdateVO.getEnterpriseAccount()
                    , getMoneyUpdateVO.getFsUserId(), TransTypeEnum.GETMONEY_UNFREEZE_NOPASS, getMoneyUpdateVO.getOrderNo()
                    , userWalletLogDO.getSysFee(), userWalletLogDO.getActualAmount()
                    , getMoneyUpdateVO.getOperTime() == null ? Calendar.getInstance() : getMoneyUpdateVO.getOperTime(), BizTypeEnum.GETMONEY);
            if (!userWalletWriteService.unfreezeUserBalanceToAble(userBalance).getResult()) {
                // 没有解冻资金转可用余额
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_AUDITOR,
                    "解冻用户的冻结余额返回0", new Gson().toJson(userBalance), Calendar.getInstance());
            }
            // 添加操作日志
            operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_AUDITOR
                    , OperateType.GM_NOPASS_AUDITOR_UNFREEZE, userBalance.toString(), Calendar.getInstance()));
        }
        // 修改审批状态
        try {
            if (userGetMoneyDAO.updateUserGetMoney(getMoneyUpdateVO) == 0) {
                // 审批状态修改错误
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_AUDITOR,
                    "审批状态修改返回0", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
            }
        } catch (Exception e) {
            logger.error("GetMoneyUpdateVO: {}, 更新提现流水异常: {}", getMoneyUpdateVO.toString(), e);
            // 审批状态修改异常
            alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.GETMONEY_AUDITOR,
                "审批状态修改异常", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
        }
        // 添加操作日志
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_AUDITOR
                , OperateType.GM_UPDATE_AUDITOR, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.SUCCESS.getErrorCode()
                , BusinessCodeEnum.SUCCESS.getErrorMessage(), new GetMoneyUpdateResult());
    }
    
    @Override
    public ModelResult<GetMoneyUpdateResult> updateGetMoneyTeller(GetMoneyUpdateVO getMoneyUpdateVO) {
        // 验证信息是否被串改
        if (getMoneyUpdateVO == null || !getMoneyUpdateVO.isValidateParam()) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 出纳 只进行审核和不通过两种状态的
        if (StringUtils.isBlank(getMoneyUpdateVO.getOrderNo()) 
                || (getMoneyUpdateVO.getGetMoneyStatus().getIndex() != GetMoneyStatus.NO_PASS.getIndex()
                    && 
                    getMoneyUpdateVO.getGetMoneyStatus().getIndex() != GetMoneyStatus.TELLER.getIndex()
                )) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 查询是否有资金冻结流水
        UserWalletLogVO userWalletLogVO = new UserWalletLogVO();
        userWalletLogVO.setOrderNo(getMoneyUpdateVO.getOrderNo());
        userWalletLogVO.setFsUserId(getMoneyUpdateVO.getFsUserId());
        userWalletLogVO.setEnterpriseAccount(getMoneyUpdateVO.getEnterpriseAccount());
        Pager<UserWalletLogDO> page = userWalletReadService.queryUserWalletLogList(new Pager<UserWalletLogDO>(), userWalletLogVO).getResult();
        if (page == null || page.getRecordSize() == 0) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorCode(), 
                    BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorMessage(), null);
        }
        UserWalletLogDO userWalletLogDO = page.getData().get(0);
        // 添加操作日志   
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_TELLER
                , OperateType.GM_START_TELLER, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        // 出纳：不通过
        if (getMoneyUpdateVO.getGetMoneyStatus().getIndex() == GetMoneyStatus.NO_PASS.getIndex()) {
            //解冻资金转可用
            UserBalanceVO userBalance = new UserBalanceVO(userWalletLogDO.getAmount(), getMoneyUpdateVO.getEnterpriseAccount()
                    , getMoneyUpdateVO.getFsUserId(), TransTypeEnum.GETMONEY_UNFREEZE_NOPASS, getMoneyUpdateVO.getOrderNo()
                    , userWalletLogDO.getSysFee(), userWalletLogDO.getActualAmount()
                    , getMoneyUpdateVO.getOperTime() == null ? Calendar.getInstance() : getMoneyUpdateVO.getOperTime(), BizTypeEnum.GETMONEY);
            // 没有解冻资金转可用余额
            if (!userWalletWriteService.unfreezeUserBalanceToAble(userBalance).getResult()) {
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_TELLER,
                        "解冻资金转可用余额返回0", new Gson().toJson(userBalance), Calendar.getInstance());
            }
            // 添加操作日志
            operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_TELLER
                    , OperateType.GM_NOPASS_TELLER_UNFREEZE, userBalance.toString(), Calendar.getInstance()));
        }
        // 修改出纳状态
        try {
            if (userGetMoneyDAO.updateUserGetMoney(getMoneyUpdateVO) ==0) {
                // 修改出纳状态失败
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_TELLER,
                        "修改出纳状态返回0", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
            }
        } catch (Exception e) {
            logger.error("GetMoneyUpdateVO: {}, 更新提现流水异常: {}", getMoneyUpdateVO.toString(), e);
            // 修改出纳状态异常
            alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.GETMONEY_TELLER,
                    "修改出纳状态异常", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
        }
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_TELLER
                , OperateType.GM_UPDATE_TELLER, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.SUCCESS.getErrorCode()
                , BusinessCodeEnum.SUCCESS.getErrorMessage(), new GetMoneyUpdateResult());
    }
    @Override
    public ModelResult<GetMoneyUpdateResult> updateGetMoneyFinish(GetMoneyUpdateVO getMoneyUpdateVO) {
        // 验证信息是否被串改
        if (getMoneyUpdateVO == null || !getMoneyUpdateVO.isValidateParam()) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 出纳 只进行审核和不通过两种状态的
        if (StringUtils.isBlank(getMoneyUpdateVO.getOrderNo()) 
                || getMoneyUpdateVO.getGetMoneyStatus().getIndex() != GetMoneyStatus.FINISH.getIndex()) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null);
        }
        // 查询是否有资金冻结流水
        UserWalletLogVO userWalletLogVO = new UserWalletLogVO();
        userWalletLogVO.setOrderNo(getMoneyUpdateVO.getOrderNo());
        userWalletLogVO.setFsUserId(getMoneyUpdateVO.getFsUserId());
        userWalletLogVO.setEnterpriseAccount(getMoneyUpdateVO.getEnterpriseAccount());
        Pager<UserWalletLogDO> page = userWalletReadService.queryUserWalletLogList(new Pager<UserWalletLogDO>(), userWalletLogVO).getResult();
        if (page == null || page.getRecordSize() == 0) {
            return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorCode(), 
                    BusinessCodeEnum.NO_ABLE_TO_FREEZE_LOG.getErrorMessage(), null);
        }
        UserWalletLogDO userWalletLogDO = page.getData().get(0);
        // 添加操作日志   已提现
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_UNFREEZE_USED
                , OperateType.GM_HAS_DEPOSIT, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        UserBalanceVO userBalance = new UserBalanceVO(userWalletLogDO.getAmount(), getMoneyUpdateVO.getEnterpriseAccount()
                , getMoneyUpdateVO.getFsUserId(), TransTypeEnum.GETMONEY_UNFREEZE_USED, getMoneyUpdateVO.getOrderNo()
                , userWalletLogDO.getSysFee(), userWalletLogDO.getActualAmount()
                , getMoneyUpdateVO.getOperTime() == null ? Calendar.getInstance() : getMoneyUpdateVO.getOperTime(), BizTypeEnum.GETMONEY);
        // 没有解冻资金为已用
        if (!userWalletWriteService.unfreezeUserBalanceToUsed(userBalance).getResult()) {
            alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_UNFREEZE_USED,
                    "解冻资金转已用返回0", new Gson().toJson(userBalance), Calendar.getInstance());
        }
        // 添加操作日志  已解冻为已用  
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_UNFREEZE_USED
                , OperateType.GM_FREEZE_TO_USE, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        // 修改出纳状态
        try {
            if (userGetMoneyDAO.updateUserGetMoney(getMoneyUpdateVO) == 0) {
                // 修改出纳状态失败
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_UNFREEZE_USED,
                        "修改出纳状态返回0", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
            }
        } catch (Exception e) {
            logger.error("GetMoneyUpdateVO: {}, 更新提现流水异常: {}", getMoneyUpdateVO.toString(), e);
            // 修改出纳状态异常
            alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.GETMONEY_UNFREEZE_USED,
                    "修改出纳状态异常", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
        }
        // 添加操作日志
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_UNFREEZE_USED
                , OperateType.GM_UPDATE_APPLY_FINISH, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        // 修改订单状态
        try {
            if (orderService.updateOrderStatus(getMoneyUpdateVO.getOrderNo(), OrderStatus.INIT_ORDER, OrderStatus.ORDER_FINISH).getResult()) {
                // 修改订单状态失败
                alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.WARN, TransTypeEnum.GETMONEY_UNFREEZE_USED,
                        "修改订单状态失败", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
            }
        }  catch (Exception e) {
            logger.error("GetMoneyUpdateVO: {}, 更新提现流水异常: {}", getMoneyUpdateVO.toString(), e);
            // 修改订单状态异常
            alarmManage.notifyError(getMoneyUpdateVO.getOrderNo(), LevelType.ERROR, TransTypeEnum.GETMONEY_UNFREEZE_USED,
                    "修改订单状态异常", new Gson().toJson(getMoneyUpdateVO), Calendar.getInstance());
        }
        // 添加操作日志
        operateLogManage.addOperateLog(new OperateLogDo(getMoneyUpdateVO.getOrderNo(), BizTypeEnum.GETMONEY, TransTypeEnum.GETMONEY_UNFREEZE_USED
                , OperateType.GM_UPDATE_ORDER_FINISH, getMoneyUpdateVO.toString(), Calendar.getInstance()));
        return new ModelResult<GetMoneyUpdateResult>(BusinessCodeEnum.SUCCESS.getErrorCode()
                , BusinessCodeEnum.SUCCESS.getErrorMessage(), new GetMoneyUpdateResult());
    }
    
    @Override
    public ModelResult<UserGetMoneyDo> queryUserGetMoneyById(Long id) {
        if (id == null) {
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), 
                    BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null); 
        }
        try {
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userGetMoneyDAO.queryUserGetMoneyById(id));
        } catch (Exception e) {
            logger.error("id: {}, 查询用户提现流水异常: {}", id, e);
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    private int addUserGetMoney(UserGetMoneyDo userGetMoneyDo) {
        if (userGetMoneyDo.validateParamNotNull()) {
            return userGetMoneyDAO.addUserGetMoney(userGetMoneyDo);
        }
        return 0;
    }

    @Override
    public ModelResult<UserGetMoneyDo> queryUserGetMoneyByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), 
                    BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), null); 
        }
        try {
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.SUCCESS.getErrorCode(), 
                    BusinessCodeEnum.SUCCESS.getErrorMessage(), userGetMoneyDAO.queryUserGetMoneyByOrderNo(orderNo));
        } catch (Exception e) {
            logger.error("orderNo: {}, 查询用户提现流水异常: {}", orderNo, e);
            return new ModelResult<UserGetMoneyDo>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<Boolean> updateGetMoney(Long id, GetMoneyStatus status) {
        if (id == null || status == null) {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR
                    .getErrorMessage(), false); 
        }
        try {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR
                    .getErrorMessage(), userGetMoneyDAO.updateGetMoneyStatus(id, status));
        }  catch (Exception e) {
            logger.error("id: {}, GetMoneyStatus: {}, updateGetMoney error: {}", id, status, e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), 
                    BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage());
        }
    }
}
