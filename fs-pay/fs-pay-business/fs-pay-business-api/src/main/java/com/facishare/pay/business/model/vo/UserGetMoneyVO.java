package com.facishare.pay.business.model.vo;

import com.facishare.pay.common.utils.MD5Util;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

/**
 * Created by wangtao on 2015/10/23.
 */
public class UserGetMoneyVO implements Serializable {
    private static final long serialVersionUID = 8382282214989432115L;

    /**
     * 提款金额
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
     * 用户提现的银行卡信息
     */
    private Long cardInfoId;
    /**
     * 企业号+用户ID+充值金额+绑定的银行卡ID+创建时间  md5 加密
     */
    private String verifyMd5;


    private String chargeNo;

    private int chargeWay;

    private int channelId;
    
    private String password;

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

    public Long getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(Long cardInfoId) {
        this.cardInfoId = cardInfoId;
    }

    public String getVerifyMd5() {
        return verifyMd5;
    }

    public void setVerifyMd5(String verifyMd5) {
        this.verifyMd5 = verifyMd5;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public int getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(int chargeWay) {
        this.chargeWay = chargeWay;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("amount", amount).add("enterpriseAccount", enterpriseAccount)
            .add("fsUserId", fsUserId).add("createTime", createTime).add("cardInfoId", cardInfoId)
            .add("verifyMd5", verifyMd5).add("chargeNo", chargeNo).add("chargeWay", chargeWay).add("password", password)
            .add("channelId", channelId).toString();
    }
    
    public UserGetMoneyVO() {
    }

    public UserGetMoneyVO(BigDecimal amount, String enterpriseAccount,
            Long fsUserId, Calendar createTime, Long cardInfoId,
            String chargeNo, int chargeWay, int channelId,
            String password) {
        this.amount = amount;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.createTime = createTime;
        this.cardInfoId = cardInfoId;
        this.chargeNo = chargeNo;
        this.chargeWay = chargeWay;
        this.channelId = channelId;
        this.password = password;
        this.verifyMd5 = MD5Util.MD5Encode(enterpriseAccount + fsUserId + amount.doubleValue() + cardInfoId + createTime.getTimeInMillis());
    }

    /**
     * 判断参数是否有效  有效返回true  
     * @return
     */
    public boolean isValidateParam() {
        // 企业号+用户ID+充值金额+绑定的银行卡ID+创建时间  md5 加密
        String key = enterpriseAccount + fsUserId + amount.doubleValue() + cardInfoId + createTime.getTimeInMillis();
        if (StringUtils.isBlank(enterpriseAccount) 
                || fsUserId == null 
                || createTime == null 
                || StringUtils.isBlank(password) 
                || amount == null
                || (amount.doubleValue() <= 0)
                || !MD5Util.MD5Encode(key).equals(verifyMd5)) {
            return false;
        }
        return true;
    }
}
