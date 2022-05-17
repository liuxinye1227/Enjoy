package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 应用数据迁移数据表
 */
public class AppDataMigrationConfigDO extends BaseDO {

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用创建者
     */
    private String appCreator;

    /**
     * 应用创建时间
     */
    private Date appGmtCreate;

    /**
     * 自定义菜单的状态 自定义菜单的状态 1.仅开启，2.开启有内容.3.未开启.
     */
    private Integer customMenuStatus;

    /**
     * 用户数据
     */
    private Integer userMsgNum;

    /**
     * 群发消息数
     */
    private Integer groupMsgNum;

    /**
     * 调用开平的api次数
     */
    private Integer openApiNum;

    /**
     * app侧组件id.
     */
    private String appComponentId;

    /**
     * web侧组件id.
     */
    private String webComponentId;

    /**
     * 开发模式 开发模式，1.普通模式，2.开发模式
     */
    private Integer appMode;

    /**
     * 自动回复状态 自动回复状态 1.开启。2.不开启
     */
    private Integer autoReplyStatus;

    /**
     * 移动客服功能状态 移动客服功能状态。1.开启 2.不开启
     */
    private Integer customServiceStatus;

    public AppDataMigrationConfigDO() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppCreator() {
        return appCreator;
    }

    public void setAppCreator(String appCreator) {
        this.appCreator = appCreator;
    }

    public Date getAppGmtCreate() {
        return appGmtCreate;
    }

    public void setAppGmtCreate(Date appGmtCreate) {
        this.appGmtCreate = appGmtCreate;
    }

    public Integer getCustomMenuStatus() {
        return customMenuStatus;
    }

    public void setCustomMenuStatus(Integer customMenuStatus) {
        this.customMenuStatus = customMenuStatus;
    }

    public Integer getUserMsgNum() {
        return userMsgNum;
    }

    public void setUserMsgNum(Integer userMsgNum) {
        this.userMsgNum = userMsgNum;
    }

    public Integer getGroupMsgNum() {
        return groupMsgNum;
    }

    public void setGroupMsgNum(Integer groupMsgNum) {
        this.groupMsgNum = groupMsgNum;
    }

    public Integer getOpenApiNum() {
        return openApiNum;
    }

    public void setOpenApiNum(Integer openApiNum) {
        this.openApiNum = openApiNum;
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

    public Integer getAppMode() {
        return appMode;
    }

    public void setAppMode(Integer appMode) {
        this.appMode = appMode;
    }

    public Integer getAutoReplyStatus() {
        return autoReplyStatus;
    }

    public void setAutoReplyStatus(Integer autoReplyStatus) {
        this.autoReplyStatus = autoReplyStatus;
    }

    public Integer getCustomServiceStatus() {
        return customServiceStatus;
    }

    public void setCustomServiceStatus(Integer customServiceStatus) {
        this.customServiceStatus = customServiceStatus;
    }

    @Override
    public String toString() {
        return "AppDataMigrationConfigDO{" +
                "appId='" + appId + '\'' +
                ", appCreator='" + appCreator + '\'' +
                ", appGmtCreate=" + appGmtCreate +
                ", customMenuStatus=" + customMenuStatus +
                ", userMsgNum=" + userMsgNum +
                ", groupMsgNum=" + groupMsgNum +
                ", openApiNum=" + openApiNum +
                ", appComponentId='" + appComponentId + '\'' +
                ", webComponentId='" + webComponentId + '\'' +
                ", appMode=" + appMode +
                ", autoReplyStatus=" + autoReplyStatus +
                ", customServiceStatus=" + customServiceStatus +
                '}';
    }
}
