package com.facishare.pay.business.model.vo;

import com.facishare.pay.common.utils.MD5Util;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 转账处理 解冻
 * Created by wangtao on 2015/10/23.
 */
public class TransferVO implements Serializable {


    private static final long serialVersionUID = 3329416909622590560L;


    /**
     * 订单号
     */
    private String orderNo;

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
    private Calendar createTime;


    /**
     * 企业号
     * */
    private String toEnterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long toFsUserId;

    /**
     * 数据校验码  企业号+员工ID+订单号+转账金额+创建时间
     */
    private String verifyMd5;

    public TransferVO() {
    }

    public TransferVO(String orderNo, BigDecimal amount, String enterpriseAccount, Long fsUserId, Calendar createTime,
        String toEnterpriseAccount, Long toFsUserId) {
        this.orderNo = orderNo;
        this.amount = amount;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.createTime = createTime;
        this.toEnterpriseAccount = toEnterpriseAccount;
        this.toFsUserId = toFsUserId;
        String key = enterpriseAccount + fsUserId + orderNo+ amount.doubleValue() + createTime.getTimeInMillis();
        this.verifyMd5 = MD5Util.MD5Encode(key);
    }

    public String getVerifyMd5() {
        return verifyMd5;
    }

    public void setVerifyMd5(String verifyMd5) {
        this.verifyMd5 = verifyMd5;
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

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
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


    public boolean isValidateParam() {
        String key = enterpriseAccount + fsUserId + orderNo+ amount.doubleValue() + createTime.getTimeInMillis();
        if (StringUtils.isBlank(enterpriseAccount) || fsUserId == null || createTime == null
             || !MD5Util.MD5Encode(key).equals(verifyMd5)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("orderNo", orderNo)
            .add("amount", amount)
            .add("enterpriseAccount", enterpriseAccount)
            .add("fsUserId", fsUserId)
            .add("createTime", createTime)
            .add("toEnterpriseAccount", toEnterpriseAccount)
            .add("toFsUserId", toFsUserId)
            .toString();
    }
}
