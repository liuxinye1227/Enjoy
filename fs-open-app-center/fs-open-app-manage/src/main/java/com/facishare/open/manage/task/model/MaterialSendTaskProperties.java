package com.facishare.open.manage.task.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenTask properties.
 *
 * @author lambo.
 * @date on 2016/1/11.
 */
public class MaterialSendTaskProperties implements Serializable{
    private static final long serialVersionUID = -6100003335568301819L;

    /**
     * 应用id.
     */
    private String sendAppId;

    /**
     * 应用名称.
     */
    private String sendAppName;

    /**
     * 应用素材id.
     */
    private String materialId;

    /**
     * 素材所有的企业.
     */
    private String materialFsEa;

    /**
     * 素材所有应用id.
     */
    private String materialAppId;

    /**
     * 企业列表.
     */
    private List<String> fsEas = new ArrayList<>();

    /**
     * 发送量
     * @return
     */
    private Integer sendCapacity;

    /**
     * 发送给系统管理员 1是 2不是
     * @return
     */
    private Integer toSysAdmin;

    /**
     * 应用管理员类型
     */
    private List<String> appAdminTypes;

    /**
     * 文本消息.
     */
    private String textMsg;

    /**
     * 应用管理员中的应用列表.
     */
    private List<String> adminAppIds = new ArrayList<>();

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public List<String> getFsEas() {
        return fsEas;
    }

    public void setFsEas(List<String> fsEas) {
        this.fsEas = fsEas;
    }

    public String getMaterialAppId() {
        return materialAppId;
    }

    public void setMaterialAppId(String materialAppId) {
        this.materialAppId = materialAppId;
    }

    public String getMaterialFsEa() {
        return materialFsEa;
    }

    public void setMaterialFsEa(String materialFsEa) {
        this.materialFsEa = materialFsEa;
    }

    public String getSendAppName() {
        return sendAppName;
    }

    public void setSendAppName(String sendAppName) {
        this.sendAppName = sendAppName;
    }

    public String getSendAppId() {
        return sendAppId;
    }

    public void setSendAppId(String sendAppId) {
        this.sendAppId = sendAppId;
    }

    public Integer getSendCapacity() {
        return sendCapacity;
    }

    public void setSendCapacity(Integer sendCapacity) {
        this.sendCapacity = sendCapacity;
    }

    public Integer getToSysAdmin() {
        return toSysAdmin;
    }

    public void setToSysAdmin(Integer toSysAdmin) {
        this.toSysAdmin = toSysAdmin;
    }

    public List<String> getAppAdminTypes() {
        return appAdminTypes;
    }

    public void setAppAdminTypes(List<String> appAdminTypes) {
        this.appAdminTypes = appAdminTypes;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public List<String> getAdminAppIds() {
        return adminAppIds;
    }

    public void setAdminAppIds(List<String> adminAppIds) {
        this.adminAppIds = adminAppIds;
    }

    @Override
    public String toString() {
        return "MaterialSendTaskProperties{" +
                "sendAppId='" + sendAppId + '\'' +
                ", sendAppName='" + sendAppName + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialFsEa='" + materialFsEa + '\'' +
                ", materialAppId='" + materialAppId + '\'' +
                ", fsEas=" + fsEas +
                ", sendCapacity=" + sendCapacity +
                ", toSysAdmin=" + toSysAdmin +
                ", appAdminTypes=" + appAdminTypes +
                ", textMsg='" + textMsg + '\'' +
                ", adminAppIds=" + adminAppIds +
                '}';
    }
}
