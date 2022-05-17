package com.facishare.open.app.center.api.model;

import com.facishare.open.app.center.api.model.enums.AppViewTypeEnum;

import java.io.Serializable;

/**
 * Description: 组件跳转对象
 * User: zhouq
 * Date: 2016/7/21
 */
public class CallBackBindDO implements Serializable{
    private static final long serialVersionUID = -6171587840737803679L;
    /**
     * 纷享应用ID或应用组件ID
     */
    private String appOrComponentId;

    /**
     * 纷享用户账号Id:E.fs.123
     */
    private String fsUserId;

    /**
     * 应用类别, APP端可见，WEB端可见
     */
    private AppViewTypeEnum appType = AppViewTypeEnum.APP;

    /**
     * 消息跳转Url
     */
    private String messageJumpUrl;

    public String getAppOrComponentId() {
        return appOrComponentId;
    }

    public void setAppOrComponentId(String appOrComponentId) {
        this.appOrComponentId = appOrComponentId;
    }

    public String getFsUserId() {
        return fsUserId;
    }

    public void setFsUserId(String fsUserId) {
        this.fsUserId = fsUserId;
    }

    public AppViewTypeEnum getAppType() {
        return appType;
    }

    public void setAppType(AppViewTypeEnum appType) {
        this.appType = appType;
    }

    public String getMessageJumpUrl() {
        return messageJumpUrl;
    }

    public void setMessageJumpUrl(String messageJumpUrl) {
        this.messageJumpUrl = messageJumpUrl;
    }

    @Override
    public String toString() {
        return "CallBackBindDO{" +
                "appOrComponentId='" + appOrComponentId + '\'' +
                ", fsUserId='" + fsUserId + '\'' +
                ", appType=" + appType +
                ", messageJumpUrl='" + messageJumpUrl + '\'' +
                '}';
    }
}
