package com.facishare.pay.business.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.business.constant.TransferStatus;
import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 转账流水
 * @author guom
 * @date 2015/10/12 14:34
 */
public class UserTransferDo extends BaseDO {

    private static final long serialVersionUID = -5492583294071187055L;
    
    /**
     * 自增ID
     * */
    private Long id;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 金额
     * */
    private BigDecimal amount;

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
    private Calendar createTime;

    /**
     * 状态 1转账中 2 转账完成 3 过期 4 转账失败
     * */
    private TransferStatus status;

    /**
     * 更新时间
     * */
    private Calendar updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
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
                .add("orderNo", orderNo)
                .add("amount", amount)
                .add("fromUserId", fromUserId)
                .add("toUserId", toUserId)
                .add("fromEnterpriseAccount", fromEnterpriseAccount)
                .add("toEnterpriseAccount", toEnterpriseAccount)
                .add("createTime", createTime != null ? createTime.getTime() : "")
                .add("status", status)
                .add("updateTime", updateTime).toString();
    }
    
    
}
