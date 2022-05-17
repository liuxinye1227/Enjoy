package com.facishare.pay.bill.model.vo;

import java.io.Serializable;
import java.util.Calendar;

import com.facishare.pay.bill.constant.BillErrorResultStatus;
import com.google.common.base.MoreObjects;

/**
 * 对账记录查询vo
 * @author guom
 * @date 2015/10/21
 */
public class BillErrorResultVO implements Serializable {

    private static final long serialVersionUID = 2063302462277632754L;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;
    
    /**
     * 状态
     * */
    private BillErrorResultStatus status;
    
    /**
     * 开始时间
     * */
    private Calendar beginTime;

    /**
     * 结束时间
     * */
    private Calendar endTime;

    
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

    public BillErrorResultStatus getStatus() {
        return status;
    }

    public void setStatus(BillErrorResultStatus status) {
        this.status = status;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("status", status)
                .add("beginTime", beginTime != null ? beginTime.getTime() : "")
                .add("endTime", endTime != null ? endTime.getTime() : "").toString();
    }
}
