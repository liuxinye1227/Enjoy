package com.facishare.pay.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.common.base.MoreObjects;

/**
 * 统计对账流水表
 * @author guom
 * @date 2015/10/22
 * */
public class BillLogResultDo implements Serializable {
    
    private static final long serialVersionUID = 5775758609472200107L;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    /**
     * 实际统计金额
     * */
    private BigDecimal actualBalance;
    
    /**
     * 真实金额
     * */
    private BigDecimal realBalance;


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

    public BigDecimal getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(BigDecimal realBalance) {
        this.realBalance = realBalance;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("actualBalance", actualBalance)
                .add("realBalance", realBalance).toString();
    }
    
}
