package com.facishare.pay.bill.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.bill.constant.BillErrorResultStatus;
import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;

/**
 * 用户对账记录详情
 * */
public class BillErrorResultDetailDo extends BaseDO {
    
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
     * 实际的余额
     * */
    private BigDecimal actualBalance;
    
    /**
     * 正确的余额(上一次对账余额+流水)
     * */
    private BigDecimal balance;

    /**
     * 状态
     * */
    private BillErrorResultStatus status;
    
    /**
     * 处理人
     * */
    private String deal;
    
    /**
     * 备注
     * */
    private String remark;
    
    /**
     * 创建时间
     * */
    private Calendar createTime;

    
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

    public BigDecimal getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(BigDecimal actualBalance) {
        this.actualBalance = actualBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BillErrorResultStatus getStatus() {
        return status;
    }

    public void setStatus(BillErrorResultStatus status) {
        this.status = status;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("actualBalance", actualBalance)
                .add("balance", balance)
                .add("remark", remark)
                .add("deal", deal)
                .add("createTime", createTime)
                .add("status", status).toString();
    }
    
}
