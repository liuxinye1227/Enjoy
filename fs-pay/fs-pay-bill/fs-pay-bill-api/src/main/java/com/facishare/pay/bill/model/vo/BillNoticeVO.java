package com.facishare.pay.bill.model.vo;


import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by wangtao on 2015/10/21.
 */
public class BillNoticeVO implements Serializable {


    private static final long serialVersionUID = -6428717031618074515L;

    /**
     * 企业号
     *
     */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     */
    private Long fsUserId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 业务编号
     */
    private Integer busiNo;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易完成后的余额（可用余额+冻结余额）
     */
    private BigDecimal balance;

    /**
     * 操作收取的手续费
     */
    private BigDecimal sysfee;

    /**
     * 请求时间
     */
    private Calendar requestTime;
    
    /**
     * 业务子类型
     * */
    private Integer transType;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(Integer busiNo) {
        this.busiNo = busiNo;
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

    public BigDecimal getSysfee() {
        return sysfee;
    }

    public void setSysfee(BigDecimal sysfee) {
        this.sysfee = sysfee;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("enterpriseAccount", enterpriseAccount).add("fsUserId", fsUserId)
            .add("orderNo", orderNo).add("busiNo", busiNo).add("amount", amount).add("balance", balance)
            .add("sysfee", sysfee).add("requestTime", requestTime).add("transType", transType).toString();
    }
}
