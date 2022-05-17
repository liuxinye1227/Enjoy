package com.facishare.pay.bill.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;

/**
 * 对账表记录
 * */
public class BillLogDo extends BaseDO {
    
    private static final long serialVersionUID = -351230621738411846L;

    /**
     * 自增ID
     * */
    private Long id;
    
    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    /**
     * 创建时间
     * */
    private Calendar createTime;

    /**
     * 请求时间
     * */
    private Calendar requestTime;

    /**
     * 金额
     * */
    private BigDecimal amount;

    /**
     * 可用余额+冻结
     * */
    private BigDecimal balance;
    
    /**
     * 业务类型
     * */
    private Integer busiNo;
    
    /**
     * 业务子类型 
     * */
    private Integer transType;
    
    /**
     * 订单号
     * */
    private String orderNo;

    public BillLogDo() {
    }

    public BillLogDo(String enterpriseAccount, Long fsUserId,
            Calendar createTime, Calendar requestTime, BigDecimal amount,
            BigDecimal balance, Integer busiNo, String orderNo, Integer transType) {
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.createTime = createTime;
        this.requestTime = requestTime;
        this.amount = amount;
        this.balance = balance;
        this.busiNo = busiNo;
        this.orderNo = orderNo;
        this.transType = transType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(Integer busiNo) {
        this.busiNo = busiNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("createTime", createTime != null ? createTime.getTime() : "")
                .add("requestTime", requestTime != null ? requestTime.getTime() : "")
                .add("busiNo", busiNo)
                .add("amount", amount)
                .add("balance", balance)
                .add("transType", transType)
                .add("orderNo", orderNo).toString();
    }
    
}
