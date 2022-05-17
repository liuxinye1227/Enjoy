package com.facishare.pay.bill.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;

/**
 * 用户对账记录详情
 * */
public class BillResultDetailDo extends BaseDO {
    
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
     * 正确的余额(上一次对账余额+流水)
     * */
    private BigDecimal balance;

    /**
     * 状态
     * */
    private Integer status;
    
    /**
     * 创建时间
     * */
    private Calendar updateTime;

    
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("balance", balance)
                .add("updateTime", updateTime != null ? updateTime.getTime() : "")
                .add("status", status).toString();
    }
    
}
