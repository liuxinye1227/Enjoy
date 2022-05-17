package com.facishare.open.app.center.mq.item;

import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:创建自定义应用和服务号事件
 * User: zhouq
 * Date: 2016/6/23
 */
public class AppOrServiceCreateItem extends ProtoBase implements Serializable{

    private static final long serialVersionUID = 4931624838072167933L;

    public static final Integer FLAG = 11;

    public static final String TOPIC = "AppAndServiceCreateEvent";

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
     * 应用管理员账号.
     */
    @Tag(4)
    private List<Integer> appAdmins;

    /**
     * 应用名称.
     */
    @Tag(5)
    private String appName;

    /**
     * 应用类型. 5：服务号   1： 自定义应用
     */
    @Tag(6)
    private Integer appType;

    /**
     * 操作时间
     */
    @Tag(7)
    private Date operationTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public List<Integer> getAppAdmins() {
        return appAdmins;
    }

    public void setAppAdmins(List<Integer> appAdmins) {
        this.appAdmins = appAdmins;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "AppOrServiceCreateItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", userId=" + userId +
                ", appAdmins=" + appAdmins +
                ", appName='" + appName + '\'' +
                ", appType=" + appType +
                ", operationTime=" + operationTime +
                '}';
    }
}
