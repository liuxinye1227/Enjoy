package com.facishare.open.app.center.api.model;

import java.io.Serializable;

/**
 *  应用组件名称，用于汇聚统计后查询应用组件名称接口.
 * Created by zenglb on 2016/1/19.
 */
public class ComponentNameVO implements Serializable{

    /**
     * 组件id.
     */
    private String componentId;

    /**
     * 组件名称
     */
    private String componentName;

    public ComponentNameVO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentNameVO that = (ComponentNameVO) o;

        if (componentId != null ? !componentId.equals(that.componentId) : that.componentId != null) return false;
        return !(componentName != null ? !componentName.equals(that.componentName) : that.componentName != null);

    }

    @Override
    public int hashCode() {
        int result = componentId != null ? componentId.hashCode() : 0;
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComponentNameVO{" +
                "componentId='" + componentId + '\'' +
                ", componentName='" + componentName + '\'' +
                '}';
    }
}
