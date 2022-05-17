package com.facishare.pay.business.model.vo;

import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by wangtao on 2015/10/23.
 */
public class UpdateChargeStatusVO implements Serializable {

    private static final long serialVersionUID = 7124594447188061120L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 交易类型
     */
    private TransTypeEnum transType;

    /**
     * 充值状态  成功或失败
     */
    private ChargeStatus chargeStatus;

    /**
     * 第三方返回的时间
     */
    private Calendar responseTime;

    /**
     * 第三方返回的信息
     */
    private String responseInfo;

    /**
     * 成功返回的url
     *
     */
    private String sucReturnUrl;

    /**
     * 失败返回的url
     */
    private String failReturnUrl;

    /**
     * 第三方对应的流水号
     */
    private String chargePayNo;



    public Calendar getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Calendar responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public String getSucReturnUrl() {
        return sucReturnUrl;
    }

    public void setSucReturnUrl(String sucReturnUrl) {
        this.sucReturnUrl = sucReturnUrl;
    }

    public String getFailReturnUrl() {
        return failReturnUrl;
    }

    public void setFailReturnUrl(String failReturnUrl) {
        this.failReturnUrl = failReturnUrl;
    }

    public String getChargePayNo() {
        return chargePayNo;
    }

    public void setChargePayNo(String chargePayNo) {
        this.chargePayNo = chargePayNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public TransTypeEnum getTransType() {
        return transType;
    }

    public void setTransType(TransTypeEnum transType) {
        this.transType = transType;
    }

    public ChargeStatus getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
    }


    public boolean isParamNull() {
        if (orderNo == null || chargeStatus == null || responseTime == null || StringUtils.isBlank(chargePayNo)) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
             .add("orderNo", orderNo)
             .add("transType", transType)
             .add("chargeStatus", chargeStatus)
             .toString();
    }
}
