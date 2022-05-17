package com.facishare.open.manage.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouq
 * on 2016/4/20.
 */
public class OpenAppScopeVO implements Serializable {

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 权限信息
     */
    private List<AppScopeVO> appScopeVOList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<AppScopeVO> getAppScopeVOList() {
        return appScopeVOList;
    }

    public void setAppScopeVOList(List<AppScopeVO> appScopeVOList) {
        this.appScopeVOList = appScopeVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenAppScopeVO that = (OpenAppScopeVO) o;

        if (!getAppId().equals(that.getAppId())) return false;
        return getAppScopeVOList().equals(that.getAppScopeVOList());

    }

    @Override
    public int hashCode() {
        int result = getAppId().hashCode();
        result = 31 * result + getAppScopeVOList().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OpenAppScopeVO{" +
                "appId='" + appId + '\'' +
                ", appScopeVOList=" + appScopeVOList +
                '}';
    }
}
