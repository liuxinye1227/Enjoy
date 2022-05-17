package com.facishare.open.app.center.api.model;

import java.io.Serializable;

/**
 * 应用名称，用于汇聚统计后查询应用名称接口.
 * Created by zenglb on 2016/1/19.
 */
public class AppNameVO implements Serializable {

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用名称.
     */
    private String appName;

    public AppNameVO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppNameVO appNameVO = (AppNameVO) o;

        if (appId != null ? !appId.equals(appNameVO.appId) : appNameVO.appId != null) return false;
        return !(appName != null ? !appName.equals(appNameVO.appName) : appNameVO.appName != null);

    }

    @Override
    public int hashCode() {
        int result = appId != null ? appId.hashCode() : 0;
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppNameVO{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
