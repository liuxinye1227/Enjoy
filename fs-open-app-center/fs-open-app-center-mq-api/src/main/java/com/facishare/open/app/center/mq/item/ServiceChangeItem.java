package com.facishare.open.app.center.mq.item;

import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:服务号变更事件
 * User: zhouq
 * Date: 2016/6/23
 */
public class ServiceChangeItem extends ProtoBase implements Serializable {

    private static final long serialVersionUID = 4931624838072167933L;

    public static final Integer FLAG = 1000;

    public static final String TOPIC = "ServiceEvent";

    /**
     * 应用id.
     */
    @Tag(1)
    private String appId;

    /**
     * 企业账户
     */
    @Tag(2)
    private String enterpriseAccount;

    /**
     * 用户ID.
     */
    @Tag(3)
    private Integer userId;

    /**
     * 员工账号.
     */
    @Tag(4)
    private List<Integer> employees;

    /**
     * 部门.
     */
    @Tag(5)
    private List<Integer> departments;

    /**
     * 应用名称或者服务号名称
     */
    @Tag(6)
    private String appName;

    /**
     * 应用或者服务号的开关状态
     */
    @Tag(7)
    private Integer status;

    /**
     * 操作时间
     */
    @Tag(8)
    private Date operationTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Integer> employees) {
        this.employees = employees;
    }

    public List<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Integer> departments) {
        this.departments = departments;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "ServiceChangeItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", userId=" + userId +
                ", employees=" + employees +
                ", departments=" + departments +
                ", appName='" + appName + '\'' +
                ", status=" + status +
                ", operationTime=" + operationTime +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
