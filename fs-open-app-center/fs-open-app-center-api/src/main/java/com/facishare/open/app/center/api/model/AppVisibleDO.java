package com.facishare.open.app.center.api.model;


/**
 * 应用企业可见实体，用于处理应用的灰度发布问题
 *
 * @author zenglb
 * @date 2015年8月31日
 */
public class AppVisibleDO extends BaseDO {

    private static final long serialVersionUID = -3163589458310220816L;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 可见类型，1.包含，2.排除。3.全部
     */
    private Integer type;

    public AppVisibleDO() {
    }

    public AppVisibleDO(String appId, Integer type) {
        this.appId = appId;
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AppVisibleDO [appId=" + appId + ", type=" + type + "]";
    }
}
