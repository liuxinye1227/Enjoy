package com.facishare.pay.bill.constant;

import com.google.common.base.MoreObjects;

/**
 * Created by wangtao on 2015/10/12.
 */
public enum BillCodeEnum {

    SUCCESS(0, "success", "成功"),

    SERVER_BUSY(-1, "server is busy","系统繁忙"),

    SYSTEM_ERROR(-2, "system error","系统错误"),

    PARAMS_ERROR(62001, "params error", "参数错误")
    ;


    private int errorCode;

    private String errorMessage;

    private String description;


    private BillCodeEnum(int errorCode, String errorMessage, String description) {
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
