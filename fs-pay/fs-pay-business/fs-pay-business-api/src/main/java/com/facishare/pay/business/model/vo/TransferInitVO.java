package com.facishare.pay.business.model.vo;

import com.facishare.pay.common.utils.MD5Util;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by wangtao on 2015/10/23.
 */
public class TransferInitVO implements Serializable {


    private static final long serialVersionUID = 4414630234958773832L;

    /**
     * 转账金额
     */
    private BigDecimal amount = BigDecimal.ZERO;

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
     * 创建时间
     */
    private Calendar createTime;

    /**
     * 二次支付密码
     */
    private String password;

    /**
     * 企业号+员工ID +交易金额+创建时间
     */
    private String verifyMd5;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isValidateParam() {
        String key = fromEnterpriseAccount + fromUserId + amount.doubleValue() + createTime
            .getTimeInMillis();
        if (StringUtils.isBlank(fromEnterpriseAccount) || fromUserId == null || createTime == null
            || !MD5Util.MD5Encode(key).equals(verifyMd5)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("amount", amount).add("fromUserId", fromUserId)
            .add("toUserId", toUserId).add("fromEnterpriseAccount", fromEnterpriseAccount)
            .add("toEnterpriseAccount", toEnterpriseAccount).add("createTime", createTime)
            .add("password", password)
            .add("verifyMd5", verifyMd5)
            .toString();
    }
}
