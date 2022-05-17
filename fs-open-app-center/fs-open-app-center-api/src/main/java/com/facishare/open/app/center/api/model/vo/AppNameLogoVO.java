package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * Created by zenglb on 2016/3/8.
 */
public class AppNameLogoVO implements Serializable{
    /**
     * 应用id.
     */
    private String appId;
    /**
     * 应用名称.
     */
    private String appName;
    /**
     * 图片地址地址.
     */
    private String logoAddress;

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

    public String getLogoAddress() {
        return logoAddress;
    }

    public void setLogoAddress(String logoAddress) {
        this.logoAddress = logoAddress;
    }

    @Override
    public String toString() {
        return "AppNameLogoVO{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", logoAddress='" + logoAddress + '\'' +
                '}';
    }
}
