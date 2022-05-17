package com.facishare.open.manage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanghl on 2016/8/9.
 *
 * @author huanghl
 */
public class DevConcernedAppVO implements Serializable {

    private String appId;

    private String appName;

    private String appLogoUrl;

    private Integer appType;

    private List<DevConcernedComponentVO> components;

    public DevConcernedAppVO(String appId, String appName, String appLogoUrl, Integer appType) {
        this.appId = appId;
        this.appName = appName;
        this.appLogoUrl = appLogoUrl;
        this.appType = appType;
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

    public String getAppLogoUrl() {
        return appLogoUrl;
    }

    public void setAppLogoUrl(String appLogoUrl) {
        this.appLogoUrl = appLogoUrl;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public List<DevConcernedComponentVO> getComponents() {
        if (components == null) {
            components = new ArrayList<>();
        }
        return components;
    }

    public void setComponents(List<DevConcernedComponentVO> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "DevConcernedAppVO{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appLogoUrl='" + appLogoUrl + '\'' +
                ", appType=" + appType +
                ", components=" + components +
                '}';
    }
}
