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
public class UserChargeVO implements Serializable {

    private static final long serialVersionUID = -741434161124680768L;
    /**
     * 充值金额金额
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
     * 用户银行卡信息
     */
    private int cardInfoId;
    /**
     * 企业号+用户ID+充值金额+绑定的银行卡ID+创建时间  md5 加密
     */
    private String verifyMd5;

    /**
     * 支付密码 密文
     */
    private String password;

    /**
     * 充值渠道号
     */
    private String chargeNo;

    /**
     * 充值方式
     */
    private Integer chargeWay;

    /**
     * 充值渠道ID
     */
    private Long channelId;


    /**
     * 需要一个默认的构造器 ，不然hessian解析时，会报错:could not be instantiated
     */
    public UserChargeVO() {
    }

    public UserChargeVO(BigDecimal amount, String enterpriseAccount, Long fsUserId, Calendar createTime, int cardInfoId,
        String password, String chargeNo, Integer chargeWay, Long channelId) {
        this.amount = amount;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.createTime = createTime;
        this.cardInfoId = cardInfoId;
        this.password = password;
        this.chargeNo = chargeNo;
        this.chargeWay = chargeWay;
        this.channelId = channelId;
        String key = enterpriseAccount + fsUserId + amount.doubleValue()
            + cardInfoId + createTime.getTimeInMillis();
        this.verifyMd5 = MD5Util.MD5Encode(key, "utf-8");

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(int cardInfoId) {
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

    public Integer getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(Integer chargeWay) {
        this.chargeWay = chargeWay;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * 判断参数是否有效  有效返回true  
     * @return
     */
    public boolean isValidateParam() {
        // 企业号+用户ID+充值金额+绑定的银行卡ID+创建时间  md5 加密
        String key = enterpriseAccount + fsUserId + amount.doubleValue() + cardInfoId + createTime.getTimeInMillis();
        if (StringUtils.isBlank(enterpriseAccount) || fsUserId == null || createTime == null 
            || StringUtils.isBlank(password) || !MD5Util.MD5Encode(key).equals(verifyMd5)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("amount", amount)
            .add("enterpriseAccount", enterpriseAccount).add("fsUserId", fsUserId)
            .add("createTime", createTime).add("cardInfoId", cardInfoId).add("verifyMd5", verifyMd5).toString();
    }
}
