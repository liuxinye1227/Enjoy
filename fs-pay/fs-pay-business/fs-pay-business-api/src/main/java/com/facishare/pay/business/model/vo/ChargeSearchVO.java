package com.facishare.pay.business.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.business.constant.ChargeStatus;
import com.google.common.base.MoreObjects;

public class ChargeSearchVO implements Serializable {

    private static final long serialVersionUID = 477120996626620900L;
    
    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    /**
     * 充值流水编号
     * */
    private String chargeNo;

    /**
     * 充值方式(银行)
     * */
    private Long chargeWay;

    /**
     * 充值状态 0 初始化 1 成功 2失败
     * */
    private ChargeStatus status;

    /**
     * 充值金额
     * */
    private BigDecimal minAmount;
    
    /**
     * 充值金额
     * */
    private BigDecimal maxAmount;

    /**
     * 充值渠道()
     * */
    private Long channelId;

    /**
     * 请求时间
     * */
    private Calendar requestBeginTime;
    
    /**
     * 请求时间
     * */
    private Calendar requestEndTime;

    /**
     * 响应时间
     * */
    private Calendar responseBeginTime;
    
    /**
     * 响应时间
     * */
    private Calendar responseEndTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public Long getFsUserId() {
        return fsUserId;
    }

    public void setFsUserId(Long fsUserId) {
        this.fsUserId = fsUserId;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public Long getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(Long chargeWay) {
        this.chargeWay = chargeWay;
    }

    public ChargeStatus getStatus() {
        return status;
    }

    public void setStatus(ChargeStatus status) {
        this.status = status;
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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Calendar getRequestBeginTime() {
        return requestBeginTime;
    }

    public void setRequestBeginTime(Calendar requestBeginTime) {
        this.requestBeginTime = requestBeginTime;
    }

    public Calendar getRequestEndTime() {
        return requestEndTime;
    }

    public void setRequestEndTime(Calendar requestEndTime) {
        this.requestEndTime = requestEndTime;
    }

    public Calendar getResponseBeginTime() {
        return responseBeginTime;
    }

    public void setResponseBeginTime(Calendar responseBeginTime) {
        this.responseBeginTime = responseBeginTime;
    }

    public Calendar getResponseEndTime() {
        return responseEndTime;
    }

    public void setResponseEndTime(Calendar responseEndTime) {
        this.responseEndTime = responseEndTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderNo", orderNo)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("chargeNo", chargeNo)
                .add("chargeWay", chargeWay)
                .add("status", status)
                .add("channelId", channelId)
                .add("minAmount", minAmount)
                .add("maxAmount", maxAmount)
                .add("requestBeginTime", requestBeginTime)
                .add("requestEndTime", requestEndTime)
                .add("responseBeginTime", responseBeginTime)
                .add("responseEndTime", responseEndTime).toString();
    }

}
