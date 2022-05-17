package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.OpenDictDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户可见的vo对象
 *
 * @author zenglinbao
 * @date 2015年8月11日
 */
public class UserCanViewListVO implements Serializable {
    private static final long serialVersionUID = -2179086077341990412L;

    /**
     * 组件id
     */
    private String componentId;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 组件所属的应用Id
     */
    private String appId;

    /**
     * 组件标签
     */
    private String componentLabel;

    /**
     * 应用的标签
     */
    private OpenDictDO label;

    /**
     * 绑定时间
     */
    private Date bindTime;

    /**
     * 所属应用类型
     */
    private Integer appType;

    /**
     * 是否为fs创建的用户
     */
    private Integer isFsApp;

    /**
     * 头像url
     */
    private String imageUrl;

    /**
     * 付费类型
     */
    private PayTypeEnum payTypeEnum;

    /**
     * 查询跳转地址.
     */
    private String callBackUrl;

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserCanViewListVO() {

    }

    public UserCanViewListVO(String componentId, String componentName, String componentLabel) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentLabel = componentLabel;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentLabel() {
        return componentLabel;
    }

    public void setComponentLabel(String componentLabel) {
        this.componentLabel = componentLabel;
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

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    @Override
    public String toString() {
        return "UserCanViewListVO{" +
                "componentId='" + componentId + '\'' +
                ", componentName='" + componentName + '\'' +
                ", appId='" + appId + '\'' +
                ", componentLabel='" + componentLabel + '\'' +
                ", label=" + label +
                ", bindTime=" + bindTime +
                ", appType=" + appType +
                ", isFsApp=" + isFsApp +
                ", imageUrl='" + imageUrl + '\'' +
                ", payTypeEnum=" + payTypeEnum +
                '}';
    }
}
