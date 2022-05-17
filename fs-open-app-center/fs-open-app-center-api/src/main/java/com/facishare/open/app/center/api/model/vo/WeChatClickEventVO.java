package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @describe: 微信自定义菜单click事件vo
 * @author: huyue
 * @date: 2016/11/22 18:44
 */

public class WeChatClickEventVO implements Serializable {

    private static final long serialVersionUID = -4200660410462682398L;

    /**
     * 企业号
     */
    private String fsEa;

    /**
     * 微信appId
     */
    private String wxAppId;
    /**
     * 点击事件key
     */
    private String key;

    /**
     * 点击事件发送的文本内容
     */
    private String textInfo;

    /**
     * 创建时间
     */
    private Date cmtTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 有效:1 无效:2
     */
    private Integer status = 1 ;

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public Date getCmtTime() {
        return cmtTime;
    }

    public void setCmtTime(Date cmtTime) {
        this.cmtTime = cmtTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WeChatClickEventVO{" +
                "fsEa='" + fsEa + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                ", key='" + key + '\'' +
                ", textInfo='" + textInfo + '\'' +
                ", cmtTime=" + cmtTime +
                ", modifiedTime=" + modifiedTime +
                ", status=" + status +
                '}';
    }
}
