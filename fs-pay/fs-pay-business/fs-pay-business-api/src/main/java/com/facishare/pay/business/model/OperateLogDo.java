package com.facishare.pay.business.model;

import java.util.Calendar;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 操作日志
 * @author guom
 * @date 2015/10/29 14:34
 */
public class OperateLogDo extends BaseDO {

    private static final long serialVersionUID = 4761566074115491771L;

    /**
     * 自增ID
     * */
    private Long id;

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
     * 操作参数
     * */
    private String requestInfo;
    
    /**
     * 创建时间
     * */
    private Calendar createTime;


    public OperateLogDo() {
    }

    public OperateLogDo(String orderNo, BizTypeEnum busiNo, TransTypeEnum transType,
            OperateType operateType, String requestInfo, Calendar createTime) {
        this.orderNo = orderNo;
        this.busiNo = busiNo;
        this.transType = transType;
        this.operateType = operateType;
        this.requestInfo = requestInfo;
        this.createTime = createTime;
    }

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("orderNo", orderNo)
                .add("transType", transType)
                .add("operateType", operateType)
                .add("requestInfo", requestInfo)
                .add("createTime", createTime)
                .add("busiNo", busiNo).toString();
    }

    @Override
    public boolean validateParamNotNull() {
        if (StringUtils.isBlank(orderNo) 
                || transType == null
                || operateType == null
                || busiNo == null) {
            return false;
        }
        return true;
    }
    
    
}
