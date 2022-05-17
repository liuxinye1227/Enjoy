package com.facishare.open.app.center.api.model;

/**
 * 组件跳转地址
 *
 * @author zhouq
 * @date 2016年4月12日
 */
public class OpenComponentUrlGrayDO extends BaseDO {
    private static final long serialVersionUID = 7778202067873623123L;

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
     *[{
     loginUrl : "http://wwww.baidu.com",
     *fsEas:[
     *{fsEa:"fsfte2a",eaName:"a"},
     *{fsEa:"fsfte2b",eaName:"b"},
     *{fsEa:"fsfte2c",eaName:"c"}
     *   ]
     * },{}
     *]
     */
    private String grayJson;

    public OpenComponentUrlGrayDO() {
    }


    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getGrayId() {
        return grayId;
    }

    public void setGrayId(String grayId) {
        this.grayId = grayId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGrayJson() {
        return grayJson;
    }

    public void setGrayJson(String grayJson) {
        this.grayJson = grayJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenComponentUrlGrayDO that = (OpenComponentUrlGrayDO) o;

        if (!grayId.equals(that.grayId)) return false;
        if (!componentId.equals(that.componentId)) return false;
        if (!appId.equals(that.appId)) return false;
        return grayJson.equals(that.grayJson);

    }

    @Override
    public int hashCode() {
        int result = grayId.hashCode();
        result = 31 * result + componentId.hashCode();
        result = 31 * result + appId.hashCode();
        result = 31 * result + grayJson.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OpenComponentUrlGrayDO{" +
                "grayId='" + grayId + '\'' +
                ", componentId='" + componentId + '\'' +
                ", appId='" + appId + '\'' +
                ", grayJson='" + grayJson + '\'' +
                '}';
    }
}
