package com.facishare.pay.business.model;

import java.math.BigDecimal;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 渠道表
 * @author guom
 * @date 2015/10/12 14:34
 */
public class ChargeChannelDo extends BaseDO {

    private static final long serialVersionUID = -2911105730876035504L;

    /**
     * id
     * */
    private Long id;

    /**
     * 渠道名称
     * */
    private String channelName;

    /**
     * 充值手续费
     * */
    private BigDecimal chargeFee;

    /**
     * 是否可用
     * */
    private Integer enable;

    /**
     * 保底手续费
     * */
    private BigDecimal minFee;

    /**
     * 封顶手续费
     * */
    private BigDecimal maxFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public BigDecimal getMinFee() {
        return minFee;
    }

    public void setMinFee(BigDecimal minFee) {
        this.minFee = minFee;
    }

    public BigDecimal getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("channelName", channelName)
                .add("chargeFee", chargeFee)
                .add("enable", enable)
                .add("minFee", minFee)
                .add("maxFee", maxFee).toString();
    }
    
    
}
