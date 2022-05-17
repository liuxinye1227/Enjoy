package com.facishare.pay.bill.model.vo;

import java.io.Serializable;
import java.util.Calendar;

import com.google.common.base.MoreObjects;

/**
 * 对账表记录
 * */
public class BillLogVO implements Serializable {
    

    private static final long serialVersionUID = 5577731741689007595L;

    
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
     * */
    private Calendar beginTime;

    /**
     * 请求时间
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
                .add("beginTime", beginTime != null ? beginTime.getTime() : "")
                .add("endTime", endTime != null ? endTime.getTime() : "").toString();
    }
    
}
