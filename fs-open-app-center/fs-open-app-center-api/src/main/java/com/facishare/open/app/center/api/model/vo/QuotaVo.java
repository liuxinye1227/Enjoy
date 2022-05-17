package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.enums.PayStatus;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xialf on 2/25/16.
 *
 * @author xialf
 */
public class QuotaVo implements Serializable {
    private String fsEa;
    private String appId;
    /**
     * 付费状态.
     */
    private PayStatus payStatus;
    /**
     * 配额数.
     */
    private int quota;
    /**
     * 到期时间.
     */
    private Date expireDate;

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fsEa", fsEa)
                .add("appId", appId)
                .add("payStatus", payStatus)
                .add("quota", quota)
                .add("expireDate", expireDate)
                .toString();
    }
}
