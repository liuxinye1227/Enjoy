package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;
import java.util.List;

/**
 * 应用创建模板地址.
 * Created by zenglb on 2016/3/22.
 */
public class AppCreateTemplateExecuteJsonVO implements Serializable {

    /**
     * 应用logo.
     */
    private String appLogo;

    /**
     * 应用名称.
     */
    private String appName;

    /**
     * 应用描述.
     */
    private String appDesc;

    /**
     * 开启自定义菜单.1.开启 2.不开启.
     */
    private Integer openCustomMenu;

    /**
     * 开启自动回复 1.开启 2.不开启.
     */
    private Integer openAutoReply;

    /**
     * app侧跳转地址.
     */
    private String appLoginUrl;

    /**
     * 应用组件,app侧图片地址.
     */
    private String appComponentLogo;

    /**
     * web侧跳转地址.
     */
    private String webLoginUrl;

    /**
     * 自定义菜单.
     */
    private List<CustomMenuTemplateJsonVO> customMenu;

    /**
     * 素材中心.
     */
    private List<ImageTextMaterialJsonVO> materials;

    /**
     * 默认回复.
     */
    private AutoReplyJsonVO defaultAutoReply;

    /**
     * 关键字回复.
     */
    private List<AutoReplyJsonVO> keywordAutoReply;

    /**
     * 发送消息.
     */
    private String sendMsg;

    /**
     * 开启工单.1.开启 0.不开启.
     */
    private Integer openWorkOrder;

    /**
     * 开启问卷 1.开启 0.不开启.
     */
    private Integer openQuestionnaire;

    /**
     * 开启移动客服 1.开启 0.不开启.
     */
    private Integer openServiceNumber;

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Integer getOpenCustomMenu() {
        return openCustomMenu;
    }

    public void setOpenCustomMenu(Integer openCustomMenu) {
        this.openCustomMenu = openCustomMenu;
    }

    public Integer getOpenAutoReply() {
        return openAutoReply;
    }

    public void setOpenAutoReply(Integer openAutoReply) {
        this.openAutoReply = openAutoReply;
    }

    public String getAppLoginUrl() {
        return appLoginUrl;
    }

    public void setAppLoginUrl(String appLoginUrl) {
        this.appLoginUrl = appLoginUrl;
    }

    public String getWebLoginUrl() {
        return webLoginUrl;
    }

    public void setWebLoginUrl(String webLoginUrl) {
        this.webLoginUrl = webLoginUrl;
    }

    public List<CustomMenuTemplateJsonVO> getCustomMenu() {
        return customMenu;
    }

    public void setCustomMenu(List<CustomMenuTemplateJsonVO> customMenu) {
        this.customMenu = customMenu;
    }

    public List<ImageTextMaterialJsonVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<ImageTextMaterialJsonVO> materials) {
        this.materials = materials;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public AutoReplyJsonVO getDefaultAutoReply() {
        return defaultAutoReply;
    }

    public void setDefaultAutoReply(AutoReplyJsonVO defaultAutoReply) {
        this.defaultAutoReply = defaultAutoReply;
    }

    public List<AutoReplyJsonVO> getKeywordAutoReply() {
        return keywordAutoReply;
    }

    public void setKeywordAutoReply(List<AutoReplyJsonVO> keywordAutoReply) {
        this.keywordAutoReply = keywordAutoReply;
    }

    public String getAppComponentLogo() {
        return appComponentLogo;
    }

    public void setAppComponentLogo(String appComponentLogo) {
        this.appComponentLogo = appComponentLogo;
    }

    public Integer getOpenWorkOrder() {
        return openWorkOrder;
    }

    public void setOpenWorkOrder(Integer openWorkOrder) {
        this.openWorkOrder = openWorkOrder;
    }

    public Integer getOpenQuestionnaire() {
        return openQuestionnaire;
    }

    public void setOpenQuestionnaire(Integer openQuestionnaire) {
        this.openQuestionnaire = openQuestionnaire;
    }

    public Integer getOpenServiceNumber() {
        return openServiceNumber;
    }

    public void setOpenServiceNumber(Integer openServiceNumber) {
        this.openServiceNumber = openServiceNumber;
    }

    @Override
    public String toString() {
        return "AppCreateTemplateExecuteJsonVO{" +
                "appLogo='" + appLogo + '\'' +
                ", appName='" + appName + '\'' +
                ", appDesc='" + appDesc + '\'' +
                ", openCustomMenu=" + openCustomMenu +
                ", openAutoReply=" + openAutoReply +
                ", appLoginUrl='" + appLoginUrl + '\'' +
                ", appComponentLogo='" + appComponentLogo + '\'' +
                ", webLoginUrl='" + webLoginUrl + '\'' +
                ", customMenu=" + customMenu +
                ", materials=" + materials +
                ", defaultAutoReply=" + defaultAutoReply +
                ", keywordAutoReply=" + keywordAutoReply +
                ", sendMsg='" + sendMsg + '\'' +
                ", openWorkOrder=" + openWorkOrder +
                ", openQuestionnaire=" + openQuestionnaire +
                ", openServiceNumber=" + openServiceNumber +
                '}';
    }
}
