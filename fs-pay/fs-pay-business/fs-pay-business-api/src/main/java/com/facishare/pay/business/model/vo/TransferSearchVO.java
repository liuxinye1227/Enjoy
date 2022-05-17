package com.facishare.pay.business.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.business.constant.TransferStatus;
import com.google.common.base.MoreObjects;

public class TransferSearchVO implements Serializable {

    private static final long serialVersionUID = -6226744666769848028L;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 金额
     * */
    private BigDecimal minAmount;
    
    /**
     * 金额
     * */
    private BigDecimal maxAmount;

    /**
     * 赠送者
     * */
    private Long fromUserId;

    /**
     * 受赠人
     * */
    private Long toUserId;

    /**
     * 赠送人企业号
     * */
    private String fromEnterpriseAccount;

    /**
     * 受赠人企业号
     * */
    private String toEnterpriseAccount;

    /**
     * 转账时间
     * */
    private Calendar beginTime;
    
    /**
     * 转账时间
     * */
    private Calendar endTime;

    /**
     * 状态1转账中 2 转账完成 3 过期
     * */
    private TransferStatus status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromEnterpriseAccount() {
        return fromEnterpriseAccount;
    }

    public void setFromEnterpriseAccount(String fromEnterpriseAccount) {
        this.fromEnterpriseAccount = fromEnterpriseAccount;
    }

    public String getToEnterpriseAccount() {
        return toEnterpriseAccount;
    }

    public void setToEnterpriseAccount(String toEnterpriseAccount) {
        this.toEnterpriseAccount = toEnterpriseAccount;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderNo", orderNo)
                .add("fromUserId", fromUserId)
                .add("toUserId", toUserId)
                .add("fromEnterpriseAccount", fromEnterpriseAccount)
                .add("toEnterpriseAccount", toEnterpriseAccount)
                .add("status", status)
                .add("minAmount", minAmount)
                .add("maxAmount", maxAmount)
                .add("beginTime", beginTime)
                .add("endTime", endTime).toString();
    }

}
