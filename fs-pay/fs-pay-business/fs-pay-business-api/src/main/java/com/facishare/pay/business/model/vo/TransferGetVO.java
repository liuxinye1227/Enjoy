package com.facishare.pay.business.model.vo;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 转账收钱
 * Created by wangtao on 2015/10/23.
 */
public class TransferGetVO implements Serializable {

    private static final long serialVersionUID = 6071097070845305420L;

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

    /**
     * 创建时间
     */
    private Calendar requestTime;


    /**
     * 企业号
     * */
    private String toEnterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long toFsUserId;

    /**
     * 订单号
     */
    private String orderNo;

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

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public String getToEnterpriseAccount() {
        return toEnterpriseAccount;
    }

    public void setToEnterpriseAccount(String toEnterpriseAccount) {
        this.toEnterpriseAccount = toEnterpriseAccount;
    }

    public Long getToFsUserId() {
        return toFsUserId;
    }

    public void setToFsUserId(Long toFsUserId) {
        this.toFsUserId = toFsUserId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("amount", amount).add("enterpriseAccount", enterpriseAccount)
            .add("fsUserId", fsUserId).add("requestTime", requestTime).add("toEnterpriseAccount", toEnterpriseAccount)
            .add("toFsUserId", toFsUserId).add("orderNo", orderNo).toString();

    }
}
