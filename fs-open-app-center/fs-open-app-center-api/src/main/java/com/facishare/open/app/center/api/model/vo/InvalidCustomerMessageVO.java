package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 无效客服的系统消息。
 * Created by zenglb on 2016/12/21.
 */
public class InvalidCustomerMessageVO implements Serializable {
    private static final long serialVersionUID = -7666822038111834449L;

    /**
     * 客服id.
     */
    private Integer customerId;

    /**
     * 系统消息内容.
     */
    private String message;

    public InvalidCustomerMessageVO() {
    }

    public InvalidCustomerMessageVO(Integer customerId, String message) {
        this.customerId = customerId;
        this.message = message;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidCustomerMessageVO{" +
                "customerId=" + customerId +
                ", message='" + message + '\'' +
                '}';
    }
}
