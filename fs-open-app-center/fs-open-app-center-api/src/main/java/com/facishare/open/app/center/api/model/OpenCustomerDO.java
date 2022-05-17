package com.facishare.open.app.center.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 开平客服
 *
 * @author chenzs
 * @date 2016年11月01日
 */
public class OpenCustomerDO implements Serializable {
    private static final long serialVersionUID = 7946668960323073922L;

    /**
     * 客服id
     */
    private String customerId;

    /**
     * 企业
     */
    private String fsEa;

    /**
     * 纷享用户id
     */
    private Integer userId;

    /**
     * 外联服务号id
     */
    private String appId;

    /**
     * 微信服务号id
     */
    private String wxAppId;

    /**
     * 来源类型, 1.手动添加 . 2 .crm同步
     *
     * @see com.facishare.open.app.center.api.model.enums.OpenCustomerSrcTypeEnum
     */
    private Integer srcType;

    /**
     * 客服角色. 1.普通客服. 2.专属客服. 3.客服主管
     *
     * @see com.facishare.open.app.center.api.model.enums.OpenCustomerRoleEnum
     */
    private Integer role;

    /**
     * 状态. 1. 有效. 2.无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;


    public OpenCustomerDO() {
    }

    public OpenCustomerDO(String customerId, String fsEa, Integer userId, String appId, String wxAppId, Integer srcType, Integer role, Integer status, Date gmtCreate, Date gmtModified) {
        this.customerId = customerId;
        this.fsEa = fsEa;
        this.userId = userId;
        this.appId = appId;
        this.wxAppId = wxAppId;
        this.srcType = srcType;
        this.role = role;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public Integer getSrcType() {
        return srcType;
    }

    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "OpenCustomerDO{" +
                "customerId='" + customerId + '\'' +
                ", fsEa='" + fsEa + '\'' +
                ", userId=" + userId +
                ", appId='" + appId + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                ", srcType=" + srcType +
                ", role=" + role +
                ", status=" + status +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}