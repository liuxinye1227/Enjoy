package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 外联服务号
 * @author zhouq
 * @date 2016年11月1日
 */

public class OuterServiceWechatVO implements Serializable {

    private static final long serialVersionUID = 7943692494960507477L;
    /**
     * 企业应用Id
     */
    private String appId;

    /**
     * 微信公众号名称
     */
    private String wxAppId;

    /**
     * 企业账号
     */
    private String fsEa;

    /**
     * 状态消息： 1：正常，2：失效
     */
    private Integer status;

    /**
     * 创始时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;

    /**
     * 外联服务号名称
     */
    private String appName;

    /**
     * 取消授权来源：1:外联服务号自动取消,2:微信取消授权,3:升级为专业客服而取消
     */
    private Integer unbindSource;

    /**
     * 群发消息使用的模板Id
     */
    private String msgTemplateId;

    public OuterServiceWechatVO(){
    }

    public OuterServiceWechatVO(String wxAppId, int status){
        this.wxAppId = wxAppId;
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getUnbindSource() {
        return unbindSource;
    }

    public void setUnbindSource(Integer unbindSource) {
        this.unbindSource = unbindSource;
    }

    public String getMsgTemplateId() {
        return msgTemplateId;
    }

    public void setMsgTemplateId(String msgTemplateId) {
        this.msgTemplateId = msgTemplateId;
    }

    @Override
    public String toString() {
        return "OuterServiceWechatVO{" +
                "appId='" + appId + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                ", fsEa='" + fsEa + '\'' +
                ", status=" + status +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", appName='" + appName + '\'' +
                ", unbindSource=" + unbindSource +
                ", msgTemplateId='" + msgTemplateId + '\'' +
                '}';
    }
}
