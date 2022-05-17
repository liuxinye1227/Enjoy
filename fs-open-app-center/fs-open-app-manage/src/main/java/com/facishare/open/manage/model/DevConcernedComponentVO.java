package com.facishare.open.manage.model;

import java.io.Serializable;

/**
 * Created by huanghl on 2016/8/9.
 *
 * @author huanghl
 */
public class DevConcernedComponentVO implements Serializable{

    private String componentId;

    private String componentName;

    private Integer componentType;

    private String imageUrl;

    public DevConcernedComponentVO(String componentId, String componentName, Integer componentType, String imageUrl) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentType = componentType;
        this.imageUrl = imageUrl;
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

    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DevConcernedComponentVO{" +
                "componentId='" + componentId + '\'' +
                ", componentName='" + componentName + '\'' +
                ", componentType=" + componentType +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
