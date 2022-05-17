package com.facishare.open.app.center.api.model;

/**
 * 应用企业可见实体，用于处理应用的灰度发布问题.
 *
 * @author zenglb
 * @date 2015年8月31日
 */
public class EaVisibleDO extends BaseDO {

    private static final long serialVersionUID = -3163589458310220816L;

    /**
     * 企业账号 E.xxx.001中的xxx.
     */
    private String fsEa;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 可见类型，1.包含，2.排除
     */
    private Integer type;

    /**
     * 企业名称.
     */
    private String eaName;

    public EaVisibleDO() {
    }

    public EaVisibleDO(String fsEa,String eaName, String appId, Integer type) {
        this.fsEa = fsEa;
        this.eaName = eaName;
        this.appId = appId;
        this.type = type;
    }

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
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

    public String getEaName() {
        return eaName;
    }

    public void setEaName(String eaName) {
        this.eaName = eaName;
    }

    @Override
    public String toString() {
        return "EaVisibleDO{" +
                "fsEa='" + fsEa + '\'' +
                ", appId='" + appId + '\'' +
                ", type=" + type +
                ", eaName='" + eaName + '\'' +
                '}';
    }
}
