package com.facishare.open.app.center.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 示例应用logo.
 * Created by zenglb on 2016/3/17.
 */
public class OpenDemoAppDO implements Serializable{

    /**
     * id.
     */
    private String id;
    /**
     * 企业账号.
     */
    private String fsEa;
    /**
     * 应用id.
     */
    private String appId;

    /**
     * 示例应用状态.
     */
    private Integer status;
    /**
     * 创建时间.
     */
    private Date gmtCreate;
    /**
     * 最后修改时间.
     */
    private Date gmtModified;
    /**
     * 引导状态 1.未引导，2.已自动引导.
     */
    private Integer guideStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getGuideStatus() {
        return guideStatus;
    }

    public void setGuideStatus(Integer guideStatus) {
        this.guideStatus = guideStatus;
    }

    @Override
    public String toString() {
        return "OpenDemoAppDO{" +
                "id='" + id + '\'' +
                ", fsEa='" + fsEa + '\'' +
                ", appId='" + appId + '\'' +
                ", status=" + status +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", guideStatus=" + guideStatus +
                '}';
    }
}
