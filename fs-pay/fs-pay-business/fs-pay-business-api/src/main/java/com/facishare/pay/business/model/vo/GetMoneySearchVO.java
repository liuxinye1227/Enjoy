package com.facishare.pay.business.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.business.constant.BankType;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.google.common.base.MoreObjects;


/**
 * 提现流水
 * @author guom
 * @date 2015/10/12 14:34
 */
public class GetMoneySearchVO implements Serializable {

    private static final long serialVersionUID = 6668506945555790291L;

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
     * 金额
     * */
    private BigDecimal minAmount;
    
    /**
     * 金额
     * */
    private BigDecimal maxAmount;

    /**
     * 银行卡
     * */
    private BankType bankType;

    /**
     * 开始时间
     * */
    private Calendar beginTime;
    
    /**
     * 操作开始时间
     * */
    private Calendar operBeginTime;
    
    /**
     * 结束时间
     * */
    private Calendar endTime;
    
    /**
     * 操作结束时间
     * */
    private Calendar operEndTime;

    /**
     * 审核人
     * */
    private String auditor;

    /**
     * 出纳人
     * */
    private String teller;

    /**
     * 银行交易凭证
     * */
    private String invoice;

    /**
     * 城市
     * */
    private String city;

    /**
     * 省份
     * */
    private String province;

    /**
     * 支行
     * */
    private String bankBranchName;

    /**
     * 商户交易号
     * */
    private String busiTradeNo;
    
    /**
     * 状态
     * */
    private GetMoneyStatus status;

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

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getBusiTradeNo() {
        return busiTradeNo;
    }

    public void setBusiTradeNo(String busiTradeNo) {
        this.busiTradeNo = busiTradeNo;
    }

    public GetMoneyStatus getStatus() {
        return status;
    }

    public void setStatus(GetMoneyStatus status) {
        this.status = status;
    }

    public Calendar getOperBeginTime() {
        return operBeginTime;
    }

    public void setOperBeginTime(Calendar operBeginTime) {
        this.operBeginTime = operBeginTime;
    }

    public Calendar getOperEndTime() {
        return operEndTime;
    }

    public void setOperEndTime(Calendar operEndTime) {
        this.operEndTime = operEndTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderNo", orderNo)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("minAmount", minAmount)
                .add("maxAmount", maxAmount)
                .add("BankType", bankType)
                .add("auditor", auditor)
                .add("teller", teller)
                .add("invoice", invoice)
                .add("city", city)
                .add("province", province)
                .add("bankBranchName", bankBranchName)
                .add("beginTime", beginTime)
                .add("endTime", endTime)
                .add("operBeginTime", operBeginTime)
                .add("operEndTime", operEndTime)
                .add("status", status)
                .add("busiTradeNo", busiTradeNo).toString();
    }

    
}
