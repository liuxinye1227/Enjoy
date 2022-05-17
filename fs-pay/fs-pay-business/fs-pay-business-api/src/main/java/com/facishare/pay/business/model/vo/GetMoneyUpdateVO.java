package com.facishare.pay.business.model.vo;

import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.common.utils.MD5Util;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

/**
 * 修改提现结果
 * Created by wangtao on 2015/10/23.
 */
public class GetMoneyUpdateVO implements Serializable {

    private static final long serialVersionUID = -8060237866989178379L;
    
    /**
     * 提现状态
     */
     private GetMoneyStatus getMoneyStatus;

    /**
     * 订单号
     */
     private String orderNo;
     
     /**
      * 企业号
      * */
     private String enterpriseAccount;
     
     /**
      * 用户id
      * */
     private Long fsUserId;

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
     * 企业号+用户ID+订单号+提现状态+auditor+teller+invoice+operTime+busiTradeNo  md5 加密
     */
    private String verifyMd5;

    public GetMoneyStatus getGetMoneyStatus() {
        return getMoneyStatus;
    }

    public void setGetMoneyStatus(GetMoneyStatus getMoneyStatus) {
        this.getMoneyStatus = getMoneyStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getVerifyMd5() {
        return verifyMd5;
    }

    public void setVerifyMd5(String verifyMd5) {
        this.verifyMd5 = verifyMd5;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("getMoneyStatus", getMoneyStatus)
            .add("orderNo", orderNo)
            .add("auditor", auditor)
            .add("teller", teller)
            .add("invoice", invoice)
            .add("operTime", operTime)
            .add("failReason", failReason)
            .add("batchId", batchId)
            .add("batchNo", batchNo)
            .add("busiTradeNo", busiTradeNo)
            .add("enterpriseAccount", enterpriseAccount)
            .add("fsUserId", fsUserId)
            .toString();
    }
    
    /**
     * 企业号+用户ID+订单号+提现状态+auditor+teller+invoice+operTime+busiTradeNo  md5 加密
     */
    /**
     * 判断参数是否有效  有效返回true  
     * @return
     */
    public boolean isValidateParam() {
        if (StringUtils.isBlank(enterpriseAccount) 
                || fsUserId == null 
                || orderNo == null 
                || getMoneyStatus == null
                || !toMD5Str().equals(verifyMd5)) {
            return false;
        }
        return true;
    }
    
    // 企业号+用户ID+订单号+提现状态+auditor+teller+invoice+operTime+busiTradeNo  md5 加密
    public String toMD5Str() {
        StringBuilder sb = new StringBuilder();
        sb.append(enterpriseAccount)
            .append(fsUserId)
            .append(orderNo)
            .append(getMoneyStatus.getIndex());
        if (StringUtils.isNotBlank(auditor)) {
            sb.append(auditor);
        }
        if (StringUtils.isNotBlank(teller)) {
            sb.append(teller);
        }
        if (StringUtils.isNotBlank(invoice)) {
            sb.append(invoice);
        }
        if (operTime != null) {
            sb.append(operTime.getTimeInMillis());
        }
        if (StringUtils.isNotBlank(busiTradeNo)) {
            sb.append(busiTradeNo);
        }
        return MD5Util.MD5Encode(sb.toString());
    }
}
