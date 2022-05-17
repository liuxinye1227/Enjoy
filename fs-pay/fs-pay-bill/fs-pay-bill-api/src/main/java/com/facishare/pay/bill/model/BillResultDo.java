package com.facishare.pay.bill.model;

import java.util.Calendar;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 对账记录 
 * @author guom
 * @date 2015/10/12 14:34
 */
public class BillResultDo extends BaseDO {
    
    private static final long serialVersionUID = -351230621738411846L;

    /**
     * 自增ID
     * */
    private Long id;
    
    /**
     * 应对账总数
     * */
    private Long total;
    
    /**
     * 实际对账总数
     * */
    private Long actualTotal;
    
    /**
     * 对账异常总人数
     * */
    private Long actualFailTotal;
    
    /**
     * 成功总数
     * */
    private Long actualSucTotal;
    
    /**
     * 状态
     * */
    private Integer status;
    
    /**
     * 开始时间
     * */
    private Calendar beginTime;

    /**
     * 结束时间
     * */
    private Calendar endTime;

    /**
     * 备注
     * */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Long actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Long getActualFailTotal() {
        return actualFailTotal;
    }

    public void setActualFailTotal(Long actualFailTotal) {
        this.actualFailTotal = actualFailTotal;
    }

    public Long getActualSucTotal() {
        return actualSucTotal;
    }

    public void setActualSucTotal(Long actualSucTotal) {
        this.actualSucTotal = actualSucTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("total", total)
                .add("actualTotal", actualTotal)
                .add("beginTime", beginTime !=null ? beginTime.getTime() : "")
                .add("endTime", endTime != null ? endTime.getTime() : "")
                .add("actualFailTotal", actualFailTotal)
                .add("actualSucTotal", actualSucTotal)
                .add("status", status)
                .add("remark", remark).toString();
    }
    
}
