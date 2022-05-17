package com.facishare.open.manage.model;

import java.io.Serializable;

/**
 * Created by albeter on 17/3/9.
 */
public class AppIconDO implements Serializable{

    private String appId;
    private String masterIconId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMasterIconId() {
        return masterIconId;
    }

    public void setMasterIconId(String masterIconId) {
        this.masterIconId = masterIconId;
    }
}
