package com.facishare.pay.business.model;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 用户银行卡绑定信息
 * @author guom
 * @date 2015/10/12 14:34
 */
public class UserCardInfoDo extends BaseDO {

    private static final long serialVersionUID = 7021676528823518793L;

    /**
     * 自增ID
     * */
    private Long id;

    /**
     * 开户行名称
     * */
    private String bankName;

    /**
     * 开户行行号
     * */
    private Integer bankNo;

    /**
     * 开户行支行
     * */
    private String bankBranchName;

    /**
     * 用户卡号
     * */
    private String cardNo;

    /**
     * 用户ID
     * */
    private Long userInfoId;

    /**
     * 创建时间
     * */
    private Calendar createTime;

    /**
     * 修改时间
     * */
    private Calendar updateTime;

    /**
     * 银行ID
     * */
    private String bankId;

    /**
     * 银行编码
     * */
    private String bankCode;

    /**
     * 备注
     * */
    private String remark;
    
    /**
     * 开户名
     * */
    private String cardName;
    
    /**
     * 绑定银行卡状态 1 可用（绑定） 2 不可用（解绑）
     * */
    private Integer status;
    
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankNo() {
        return bankNo;
    }

    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("bankName", bankName)
                .add("bankNo", bankNo)
                .add("bankBranchName", bankBranchName)
                .add("cardNo", cardNo)
                .add("userInfoId", userInfoId)
                .add("createTime", createTime != null ? createTime.getTime() : "")
                .add("updateTime", updateTime != null ? updateTime.getTime() : "")
                .add("bankId", bankId)
                .add("bankCode", bankCode)
                .add("cardName", cardName)
                .add("status", status == 1 ? "可用" : "解绑")
                .add("remark", remark).toString();
    }

    @Override
    public boolean validateParamNotNull() {
        if (StringUtils.isBlank(cardNo)
                || userInfoId == null) {
            return false;
        }
        return true;
    }
    
}
