package com.facishare.open.app.center.api.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @describe: 月活企业表
 * @author: xiaoweiwei
 * @date: 2016/6/24 14:13
 */
public class MonthActivityEaDO implements Serializable {

    private static final long serialVersionUID = -8816149799291798086L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 企业账号
     */
    private String ea;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthActivityEaDO that = (MonthActivityEaDO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (enterpriseId != null ? !enterpriseId.equals(that.enterpriseId) : that.enterpriseId != null) return false;
        if (ea != null ? !ea.equals(that.ea) : that.ea != null) return false;
        return gmtCreate != null ? gmtCreate.equals(that.gmtCreate) : that.gmtCreate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (ea != null ? ea.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        return result;
    }
}