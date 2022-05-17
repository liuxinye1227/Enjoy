package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.OpenDictDO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanj
 * @date 2015年8月11日
 */
public class OpenAppVO implements Serializable {

    private static final long serialVersionUID = -2179086077341990412L;

    /**
     * 企业应用Id
     */
    private String appId;

    /**
     * 企业应用名称
     */
    private String appName;

    /**
     * 应用简介
     */
    private String appDesc;

    /**
     * 企业应用图标地址
     */
    private String logo;

    /**
     * 企业应用标签
     */
    private String appLabel;

    /**
     * 登录地址
     */
    private String loginAddress;

    private OpenDictDO label;

    /**
     * 绑定时间
     */
    private Date bindTime;

    /**
     * 应用类型
     */
    private Integer appType;

    /**
     * 是否为fs创建的用户
     */
    private Integer isFsApp;

    /**
     * 0 不付费 1付费
     */
    private int payType;

    /**
     * 企业服务号名称
     */
    private String serviceName;

    /**
     * 绑定状态,主要相对于企业的应用.1.为绑定.2为停用
     */
    private Integer bindStatus;

    /**
     * 创建者信息,createrType=1此处为fsuserId为 E.xxx.001中的xxx值
     * createType=2时为open_dev.idr
     */
    private String appCreater;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public OpenAppVO() {

    }

    public OpenAppVO(String appId, String appName, String logo, String appLabel) {
        this.appId = appId;
        this.appName = appName;
        this.logo = logo;
        this.appLabel = appLabel;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public OpenDictDO getLabel() {
        return label;
    }

    public void setLabel(OpenDictDO label) {
        this.label = label;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getIsFsApp() {
        return isFsApp;
    }

    public void setIsFsApp(Integer isFsApp) {
        this.isFsApp = isFsApp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getAppCreater() {
        return appCreater;
    }

    public void setAppCreater(String appCreater) {
        this.appCreater = appCreater;
    }

    @Override
    public String toString() {
        return "OpenAppVO{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appDesc='" + appDesc + '\'' +
                ", logo='" + logo + '\'' +
                ", appLabel='" + appLabel + '\'' +
                ", loginAddress='" + loginAddress + '\'' +
                ", label=" + label +
                ", bindTime=" + bindTime +
                ", appType=" + appType +
                ", isFsApp=" + isFsApp +
                ", payType=" + payType +
                ", serviceName='" + serviceName + '\'' +
                ", bindStatus=" + bindStatus +
                ", appCreater='" + appCreater + '\'' +
                '}';
    }
}
