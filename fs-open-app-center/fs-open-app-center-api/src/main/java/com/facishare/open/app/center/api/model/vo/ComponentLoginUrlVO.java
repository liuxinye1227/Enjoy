package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 组件跳转地址
 *
 * @author zhouq
 * @date 2016年4月12日
 */
public class ComponentLoginUrlVO implements Serializable {
    private static final long serialVersionUID = 7888202067873623123L;

    /**
     * 组件id.
     */
    private String componentId;
    /**
     * 组件url
     */
    private String loginUrl;

    public ComponentLoginUrlVO() {
    }

    public ComponentLoginUrlVO(String componentId, String loginUrl) {
        this.componentId = componentId;
        this.loginUrl = loginUrl;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public String toString() {
        return "ComponentLoginUrlVO{" +
                "componentId='" + componentId + '\'' +
                ", loginUrl='" + loginUrl + '\'' +
                '}';
    }
}
