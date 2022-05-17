package com.facishare.pay.business.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 提现流水
 * @author guom
 * @date 2015/10/12 14:34
 */
public class UserGetMoneyDo extends BaseDO {

    private static final long serialVersionUID = 2416463744749896120L;

    /**
     * 自增ID
     * */
    private Long id;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    /**
     * 金额
     * */
    private BigDecimal amount;

    /**
     * 应收手续费
     * */
    private BigDecimal fee;

    /**
     * 实际收的手续费
     * */
    private BigDecimal actualFee;

    /**
     * 开户行行号
     * */
    private Integer bankNo;

    /**
     * 银行卡号
     * */
    private String bankName;

    /**
     * 创建时间
     * */
    private Calendar createTime;

    /**
     * 审核人
     * */
    private String auditor;

    /**
     * 出纳人
     * */
    private String teller;

    /**
     * 银行交易凭证
     * */
    private String invoice;

    /**
     * 操作时间
     * */
    private Calendar operTime;

    /**
     * 城市
     * */
    private String city;

    /**
     * 省份
     * */
    private String province;

    /**
     * 开户行支行
     * */
    private String bankBranchName;
    
    /**
     * 卡号
     * */
    private String cardNo;

    /**
     * 提款失败原因
     * */
    private String failReason;

    /**
     * 提款批次ID
     * */
    private String batchId;

    /**
     * 提款批次号
     * */
    private String batchNo;

    /**
     * 商户交易号
     * */
    private String busiTradeNo;
    
    /**
     * 状态
     * */
    private GetMoneyStatus status;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Calendar getOperTime() {
        return operTime;
    }

    public void setOperTime(Calendar operTime) {
        this.operTime = operTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getBankNo() {
        return bankNo;
    }

    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBusiTradeNo() {
        return busiTradeNo;
    }

    public void setBusiTradeNo(String busiTradeNo) {
        this.busiTradeNo = busiTradeNo;
    }

    public GetMoneyStatus getStatus() {
        return status;
    }

    public void setStatus(GetMoneyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("orderNo", orderNo)
                    .add("enterpriseAccount", enterpriseAccount)
                    .add("fsUserId", fsUserId)
                    .add("amount", amount)
                    .add("fee", fee)
                    .add("actualFee", actualFee)
                    .add("bankNo", bankNo)
                    .add("bankName", bankName)
                    .add("bankBranchName", bankBranchName)
                    .add("cardNo", cardNo)
                    .add("createTime", createTime)
                    .add("auditor", auditor)
                    .add("teller", teller)
                    .add("invoice", invoice)
                    .add("operTime", operTime)
                    .add("city", city)
                    .add("province", province)
                    .add("failReason", failReason)
                    .add("batchId", batchId)
                    .add("batchNo", batchNo)
                    .add("status", status)
                    .add("busiTradeNo", busiTradeNo).toString();
    }

    @Override
    public boolean validateParamNotNull() {
        if (StringUtils.isBlank(orderNo)
                || StringUtils.isBlank(enterpriseAccount)
                || fsUserId == null
                || amount == null) {
            return false;
        }
        return true;
    }
    
    
}
