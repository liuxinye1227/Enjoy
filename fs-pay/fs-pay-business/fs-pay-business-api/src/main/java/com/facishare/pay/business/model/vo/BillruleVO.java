package com.facishare.pay.business.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wangtao on 2015/10/26.
 */
public class BillruleVO implements Serializable {

    private static final long serialVersionUID = -1121120512247370999L;

    /**
     * 转账金额
     */
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    public BillruleVO() {
    }

    public BillruleVO(BigDecimal amount, String enterpriseAccount, Long fsUserId) {
        this.amount = amount;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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


}
