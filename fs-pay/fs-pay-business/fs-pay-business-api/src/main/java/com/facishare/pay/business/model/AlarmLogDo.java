package com.facishare.pay.business.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by wangtao on 2015/10/30.
 */
public class AlarmLogDo implements Serializable {

    private static final long serialVersionUID = 1338853484255427236L;


    private Long id;

    private String orderNo;

    private String type;

    private String transType;

    private String errorMsg;

    private String requestInfo;

    private Calendar createTime;

    private int status;

    private Calendar updateTime;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("orderNo", orderNo)
            .add("type", type)
            .add("transType", transType)
            .add("errorMsg", errorMsg)
            .add("requestInfo", requestInfo)
            .add("createTime", createTime)
            .add("status", status)
            .add("updateTime", updateTime)
            .toString();
    }
}
