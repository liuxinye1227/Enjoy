package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 开平客服
 * Created by chenzs on 2016/11/2.
 */
public class OpenCustomerVO implements Serializable {

    private static final long serialVersionUID = 2360601316246773773L;

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
     * 姓名
     */
    private String userName;

    /**
     * 部门
     */
    private String departmentName;

    /**
     * 职务
     */
    private String job;

    /**
     * 外联服务号id
     */
    private String appId;

    /**
     * 来源类型, 1.手工添加 . 2 .crm同步
     *
     * @see com.facishare.open.app.center.api.model.enums.OpenCustomerSrcTypeEnum
     */
    private Integer srcType;

    /**
     * 来源类型名称
     */
    private String srcTypeName;

    /**
     * 微信服务号id
     */
    private String wxAppId;

    /**
     * 关联微信用户用户数
     */
    private Integer wxUserNum;

    /**
     * 客服角色. 1.普通客服. 2.专属客服. 3.客服主管
     *
     * @see com.facishare.open.app.center.api.model.enums.OpenCustomerRoleEnum
     */
    private Integer role;

    /**
     * 客服角色名称
     */
    private String roleName;

    /**
     * 状态. 1. 有效. 2.无效
     */
    private Integer status;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getSrcType() {
        return srcType;
    }

    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
    }

    public String getSrcTypeName() {
        return srcTypeName;
    }

    public void setSrcTypeName(String srcTypeName) {
        this.srcTypeName = srcTypeName;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public Integer getWxUserNum() {
        return wxUserNum;
    }

    public void setWxUserNum(Integer wxUserNum) {
        this.wxUserNum = wxUserNum;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OpenCustomerVO{" +
                "customerId='" + customerId + '\'' +
                ", fsEa='" + fsEa + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", job='" + job + '\'' +
                ", appId='" + appId + '\'' +
                ", srcType=" + srcType +
                ", srcTypeName='" + srcTypeName + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                ", wxUserNum=" + wxUserNum +
                ", role=" + role +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                '}';
    }
}
