package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 组件跳转地址
 *
 * @author zhouq
 * @date 2016年4月12日
 */
public class OpenComponentUrlGrayVO implements Serializable {
    private static final long serialVersionUID = 7999902067873623123L;

    /**
     * 主键Id
     */
    private String grayId;

    /**
     * 组件Id
     */
    private String componentId;

    /**
     * appId
     */
    private String appId;

    /**
     * 组件地址和对应灰度的企业id,名称集合
     *
     */
    private List<ComponentUrlGrayJsonVO> componentUrlGrayJsonVOList;

    public OpenComponentUrlGrayVO() {
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGrayId() {
        return grayId;
    }

    public void setGrayId(String grayId) {
        this.grayId = grayId;
    }

    public List<ComponentUrlGrayJsonVO> getComponentUrlGrayJsonVOList() {
        return componentUrlGrayJsonVOList;
    }

    public void setComponentUrlGrayJsonVOList(List<ComponentUrlGrayJsonVO> componentUrlGrayJsonVOList) {
        this.componentUrlGrayJsonVOList = componentUrlGrayJsonVOList;
    }

    @Override
    public String toString() {
        return "OpenComponentUrlGrayVO{" +
                "grayId='" + grayId + '\'' +
                ", componentId='" + componentId + '\'' +
                ", appId='" + appId + '\'' +
                ", componentUrlGrayJsonVOList=" + componentUrlGrayJsonVOList +
                '}';
    }
}
