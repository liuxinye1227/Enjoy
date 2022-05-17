package com.facishare.open.app.center.api.model;

/**
 * 企业中的应用管理员
 *
 * @author zenglb
 * @date 2015年10月13日
 */
public class OpenAppAdminDO extends BaseDO {
    private static final long serialVersionUID = -5040418920712101130L;

    /**
     * 纷享企业
     */
    private String fsEa;

    /**
     * 应用账号
     */
    private String appId;

    /**
     * 应用类型，详情见：{@link com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType AppCenterEnum.AppType}
     */
    private Integer appType;

    /**
     * 纷享用户
     */
    private String fsUserId;

    public OpenAppAdminDO() {
    }

    public OpenAppAdminDO(String fsEa, String appId, Integer appType, String fsUserId) {
        this.fsEa = fsEa;
        this.appId = appId;
        this.appType = appType;
        this.fsUserId = fsUserId;
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

    public String getFsUserId() {
        return fsUserId;
    }

    public void setFsUserId(String fsUserId) {
        this.fsUserId = fsUserId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return "OpenAppAdminDO{" +
                "fsEa='" + fsEa + '\'' +
                ", appId='" + appId + '\'' +
                ", appType=" + appType +
                ", fsUserId='" + fsUserId + '\'' +
                '}';
    }
}
