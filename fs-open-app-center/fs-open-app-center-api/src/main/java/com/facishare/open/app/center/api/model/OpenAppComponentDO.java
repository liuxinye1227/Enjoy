package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 应用相件数据.
 *
 * @author zenglb
 * @date 2015年10月13日
 */
public class OpenAppComponentDO extends BaseDO {
    private static final long serialVersionUID = 7958202067873623123L;

    /**
     * 组件Id
     */
    private String componentId;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * appId
     */
    private String appId;

    /**
     * 应用简介
     */
    private String componentDesc;

    /**
     * 应用标签:主要用于标记手机端的应用中心显示的label
     */
    private Integer componentLabel;

    /**
     * @see com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum
     */
    private Integer componentType;

    public OpenAppComponentDO() {
    }

    public OpenAppComponentDO(String componentId, String componentName, String appId, String componentDesc,
                              Integer componentLabel, Integer status, Date gmtCreate, Date gmtModified, Integer componentType) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.appId = appId;
        this.componentDesc = componentDesc;
        this.componentLabel = componentLabel;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.componentType = componentType;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getComponentDesc() {
        return componentDesc;
    }

    public void setComponentDesc(String componentDesc) {
        this.componentDesc = componentDesc;
    }

    public Integer getComponentLabel() {
        return componentLabel;
    }

    public void setComponentLabel(Integer componentLabel) {
        this.componentLabel = componentLabel;
    }

    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((componentDesc == null) ? 0 : componentDesc.hashCode());
        result = prime * result + ((componentId == null) ? 0 : componentId.hashCode());
        result = prime * result + ((componentLabel == null) ? 0 : componentLabel.hashCode());
        result = prime * result + ((componentName == null) ? 0 : componentName.hashCode());
        result = prime * result + ((componentType == null) ? 0 : componentType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OpenAppComponentDO other = (OpenAppComponentDO) obj;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (componentDesc == null) {
            if (other.componentDesc != null)
                return false;
        } else if (!componentDesc.equals(other.componentDesc))
            return false;
        if (componentId == null) {
            if (other.componentId != null)
                return false;
        } else if (!componentId.equals(other.componentId))
            return false;
        if (componentLabel == null) {
            if (other.componentLabel != null)
                return false;
        } else if (!componentLabel.equals(other.componentLabel))
            return false;
        if (componentName == null) {
            if (other.componentName != null)
                return false;
        } else if (!componentName.equals(other.componentName))
            return false;
        if (componentType == null) {
            if (other.componentType != null)
                return false;
        } else if (!componentType.equals(other.componentType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OpenAppComponentDO{" +
                "componentId='" + componentId + '\'' +
                ", componentName='" + componentName + '\'' +
                ", appId='" + appId + '\'' +
                ", componentDesc='" + componentDesc + '\'' +
                ", componentLabel=" + componentLabel +
                ", componentType=" + componentType +
                '}';
    }
}
