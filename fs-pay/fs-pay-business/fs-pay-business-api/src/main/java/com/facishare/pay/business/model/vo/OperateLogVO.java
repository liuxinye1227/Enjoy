package com.facishare.pay.business.model.vo;

import java.io.Serializable;
import java.util.Calendar;

import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.google.common.base.MoreObjects;

/**
 * 操作日志查询
 * Created by guom on 2015/10/30.
 */
public class OperateLogVO implements Serializable {

    private static final long serialVersionUID = 8275319346361917698L;

    /**
     * 订单号
     * */
    private String orderNo;
    
    /**
     * 业务类型
     * */
    private BizTypeEnum busiNo;
    
    /**
     * 交易类型
     * */
    private TransTypeEnum transType;
    
    /**
     * 操作类型
     * */
    private OperateType operateType;
    
    /**
     * 开始时间
     * */
    private Calendar beginTime;
    
    /**
     * 结束时间
     * */
    private Calendar endTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BizTypeEnum getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(BizTypeEnum busiNo) {
        this.busiNo = busiNo;
    }

    public TransTypeEnum getTransType() {
        return transType;
    }

    public void setTransType(TransTypeEnum transType) {
        this.transType = transType;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
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
                .add("orderNo", orderNo)
                .add("transType", transType)
                .add("operateType", operateType)
                .add("beginTime", beginTime)
                .add("endTime", endTime).toString();
    }
}
