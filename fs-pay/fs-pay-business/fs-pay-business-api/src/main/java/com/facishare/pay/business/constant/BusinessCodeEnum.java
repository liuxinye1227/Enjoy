package com.facishare.pay.business.constant;

import com.google.common.base.MoreObjects;

/**
 * Created by wangtao on 2015/10/12.
 */
public enum BusinessCodeEnum {

    SUCCESS(0, "success", "成功"),

    SERVER_BUSY(-1, "server is busy","系统繁忙"),

    SYSTEM_ERROR(-2, "system error","系统错误"),

    PARAMS_ERROR(62501, "params error", "参数错误"),
    
    REPEAT_ERROR(62502, "params repeat", "重复提交"),
    
    UNEXIS_ERROR(62504, "not unexis", "不存在"),
    
    NO_ENOUGH_MONEY(625010, "no enough money", "余额不够"),
    
    MSG_CHANGED(62511, "message has changed", "消息被篡改"),
    
    THIRD_CHARGE_FAILD(62512, "charge faild", "充值第三方返回失败"),

    ADD_USERWALLET_FAILD(62513, "add_userwallet_faild", "添加用户钱包余额失败"),
    
    NO_ABLE_TO_FREEZE_LOG(62514, "no able to freeze log", "没有资金冻结流水"),
    
    ABLE_TO_FREEZE_ERROR(62515, "freeze error", "资金解冻失败"),

    PAY_PASSWORD_ERROR(62516, "pay_password_error", "支付密码错误"),
    
    ADD_TO_FREEZE_ERROR(62517, "freeze error", "资金冻结失败"),
    
    UPDATE_ORDERSTATUS_ERROR(62518, "update orderstatus error", "修改订单状态异常"),
    
    ADD_USERGETMONEY_ERROR(62519, "add user getmoney error", "添加用户提现申请异常"),
    
    UPDATE_USERGETMONEY_ERROR(62520, "add user getmoney error", "修改用户提现申请异常"),
    
    ADD_EXIST_USER(62521, "add exist user", "用户已经存着"),
    ;


    private int errorCode;

    private String errorMessage;

    private String description;


    private BusinessCodeEnum(int errorCode, String errorMessage, String description) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override public String toString() {
        return MoreObjects.toStringHelper(this).add("errorCode", errorCode)
                   .add("errorMessage", errorMessage)
                   .add("description", description)
                   .toString();
    }
}
