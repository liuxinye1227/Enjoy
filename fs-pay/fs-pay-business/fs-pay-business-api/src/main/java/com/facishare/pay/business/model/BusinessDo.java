package com.facishare.pay.business.model;

import com.facishare.pay.common.model.BaseDO;
import com.google.common.base.MoreObjects;


/**
 * 业务表
 * @author guom
 * @date 2015/10/12 14:34
 */
public class BusinessDo extends BaseDO {

    private static final long serialVersionUID = 2215916465372985784L;

    /**
     * 自增ID
     * */
    private Long id;

    /**
     * 业务描述
     * */
    private String desc;

    /**
     * 业务编号
     * */
    private String busiNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("desc", desc)
                .add("busiNo", busiNo).toString();
    }
    
    
}
