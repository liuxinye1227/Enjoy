package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 数据迁移历史表
 */
public class AppDataMigrationHistoryDO extends BaseDO{

    /**
     * 原有应用的应用id.
     */
    private String appId;

    /**
     * 应用创建者
     */
    private String appCreator;

    /**
     * 执行
     */
    private Date gmtExecute;

    /**
     * 新服务号id
     */
    private String newServiceId;

    /**
     * 新服务号的组件id.
     */
    private String newServiceComponentId;

    /**
     * app侧组件id.
     */
    private String appComponentId;

    /**
     * web侧组件id.
     */
    private String webComponentId;

    /**
     * 新创建的应用id.
     */
    private String newAppId;

    /**
     * 是否有修改自定义菜单状态 是否有修改自定义菜单状态，1.修改2，未修改
     */
    private Integer modifyCustomMenuStatus;

    /**
     * 是否修改自动回复状态 是否修改自动回复状态，1.修改，2 未修改.
     */
    private Integer modifyAutoReplyStatus;

    /**
     * 是否修改移动客服状态 是否修改移动客服状态，1.修改，2 未修改.
     */
    private Integer modifyCustomServiceStatus;

    /**
     * 开发模式变更状态 开发模式变更状态.1,有变更。2未变更.
     */
    private Integer modifyAppModeStatus;

    public AppDataMigrationHistoryDO() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }

    public String getNewServiceId() {
        return newServiceId;
    }

    public void setNewServiceId(String newServiceId) {
        this.newServiceId = newServiceId;
    }

    public String getNewServiceComponentId() {
        return newServiceComponentId;
    }

    public void setNewServiceComponentId(String newServiceComponentId) {
        this.newServiceComponentId = newServiceComponentId;
    }

    public String getNewAppId() {
        return newAppId;
    }

    public void setNewAppId(String newAppId) {
        this.newAppId = newAppId;
    }

    public Integer getModifyCustomMenuStatus() {
        return modifyCustomMenuStatus;
    }

    public void setModifyCustomMenuStatus(Integer modifyCustomMenuStatus) {
        this.modifyCustomMenuStatus = modifyCustomMenuStatus;
    }

    public Integer getModifyAutoReplyStatus() {
        return modifyAutoReplyStatus;
    }

    public void setModifyAutoReplyStatus(Integer modifyAutoReplyStatus) {
        this.modifyAutoReplyStatus = modifyAutoReplyStatus;
    }

    public Integer getModifyCustomServiceStatus() {
        return modifyCustomServiceStatus;
    }

    public void setModifyCustomServiceStatus(Integer modifyCustomServiceStatus) {
        this.modifyCustomServiceStatus = modifyCustomServiceStatus;
    }

    public Integer getModifyAppModeStatus() {
        return modifyAppModeStatus;
    }

    public void setModifyAppModeStatus(Integer modifyAppModeStatus) {
        this.modifyAppModeStatus = modifyAppModeStatus;
    }

    public String getAppCreator() {
        return appCreator;
    }

    public void setAppCreator(String appCreator) {
        this.appCreator = appCreator;
    }

    public String getAppComponentId() {
        return appComponentId;
    }

    public void setAppComponentId(String appComponentId) {
        this.appComponentId = appComponentId;
    }

    public String getWebComponentId() {
        return webComponentId;
    }

    public void setWebComponentId(String webComponentId) {
        this.webComponentId = webComponentId;
    }

    @Override
    public String toString() {
        return "AppDataMigrationHistoryDO{" +
                "appId='" + appId + '\'' +
                ", appCreator='" + appCreator + '\'' +
                ", gmtExecute=" + gmtExecute +
                ", newServiceId='" + newServiceId + '\'' +
                ", newServiceComponentId='" + newServiceComponentId + '\'' +
                ", appComponentId='" + appComponentId + '\'' +
                ", webComponentId='" + webComponentId + '\'' +
                ", newAppId='" + newAppId + '\'' +
                ", modifyCustomMenuStatus=" + modifyCustomMenuStatus +
                ", modifyAutoReplyStatus=" + modifyAutoReplyStatus +
                ", modifyCustomServiceStatus=" + modifyCustomServiceStatus +
                ", modifyAppModeStatus=" + modifyAppModeStatus +
                ", status=" + getStatus() +
                '}';
    }
}