package com.facishare.pay.business.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;

/**
 * 用户充值流水
 * @author guom
 * @date 2015/10/12 14:34
 */
public class UserChargeDo extends BaseDO {

    private static final long serialVersionUID = -9138766280163088603L;

    public UserChargeDo() {
    }

    /**
     * 新增
     * @param orderNo
     * @param enterpriseAccount
     * @param fsUserId
     * @param chargeNo
     * @param chargeWay
     * @param sysFee
     * @param actualFee
     * @param status
     * @param amount
     * @param channelId
     * @param requestTime
     * @param requestInfo
     */
    public UserChargeDo(String orderNo, String enterpriseAccount, Long fsUserId, String chargeNo, Integer chargeWay,
        BigDecimal sysFee, BigDecimal actualFee, ChargeStatus status, BigDecimal amount, Long channelId,
        Calendar requestTime, String requestInfo) {
        this.orderNo = orderNo;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.chargeNo = chargeNo;
        this.chargeWay = chargeWay;
        this.sysFee = sysFee;
        this.actualFee = actualFee;
        this.status = status;
        this.amount = amount;
        this.channelId = channelId;
        this.requestTime = requestTime;
        this.requestInfo = requestInfo;
    }

    /**
     * 修改时用
     * @param orderNo
     * @param enterpriseAccount
     * @param fsUserId
     * @param responseTime
     * @param responseInfo
     * @param sucReturnUrl
     * @param failReturnUrl
     * @param chargePayNo
     * @param status
     */
    public UserChargeDo(String orderNo, String enterpriseAccount, Long fsUserId, Calendar responseTime,
        String responseInfo, String sucReturnUrl, String failReturnUrl, String chargePayNo, ChargeStatus status) {
        this.orderNo = orderNo;
        this.enterpriseAccount = enterpriseAccount;
        this.fsUserId = fsUserId;
        this.responseTime = responseTime;
        this.responseInfo = responseInfo;
        this.sucReturnUrl = sucReturnUrl;
        this.failReturnUrl = failReturnUrl;
        this.chargePayNo = chargePayNo;
        this.status = status;
    }

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
     * 充值流水编号
     * */
    private String chargeNo;

    /**
     * 充值方式(银行)
     * */
    private Integer chargeWay;

    /**
     * 系统成本手续费
     * */
    private BigDecimal sysFee;

    /**
     * 实际收取的手续费
     */
    private BigDecimal actualFee;

    /**
     * 充值状态 0 初始化 1 成功 2失败
     * */
    private ChargeStatus status;

    /**
     * 充值金额
     * */
    private BigDecimal amount;

    /**
     * 充值渠道()
     * */
    private Long channelId;

    /**
     * 请求时间
     * */
    private Calendar requestTime;

    /**
     * 请求信息
     * */
    private String requestInfo;

    /**
     * 响应时间
     * */
    private Calendar responseTime;
    
    /**
     * 响应信息
     * */
    private String responseInfo;

    /**
     * 成功返回路径
     * */
    private String sucReturnUrl;

    /**
     * 失败返回路径
     * */
    private String failReturnUrl;

    /**
     * 充值商对应的流水号（可根据流水号查询第三方交易信息）
     * */
    private String chargePayNo;

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



    public ChargeStatus getStatus() {
        return status;
    }

    public void setStatus(ChargeStatus status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public Calendar getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Calendar responseTime) {
        this.responseTime = responseTime;
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

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public BigDecimal getSysFee() {
        return sysFee;
    }

    public void setSysFee(BigDecimal sysFee) {
        this.sysFee = sysFee;
    }

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("orderNo", orderNo)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("chargeNo", chargeNo)
                .add("chargeWay", chargeWay)
                .add("sysFee", sysFee)
                .add("actualFee", actualFee)
                .add("status", status)
                .add("amount", amount)
                .add("channelId", channelId)
                .add("requestTime", requestTime != null ? requestTime.getTime() : "")
                .add("requestInfo", requestInfo)
                .add("responseTime", responseTime != null ? responseTime.getTime() : "")
                .add("responseInfo", responseInfo)
                .add("sucReturnUrl", sucReturnUrl)
                .add("failReturnUrl", failReturnUrl)
                .add("chargePayNo", chargePayNo).toString();
    }
    
    
}
