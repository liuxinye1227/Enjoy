package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 群发消息记录
 * User: huyue
 * Date: 2016/11/2
 */
public class WechatGroupMsgVO implements Serializable {
    private static final long serialVersionUID = 0;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 企业号
     */
    private String fsEa;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 微信应用id
     */
    private String wechatAppId;

    /**
     * 群发接收人
     */
    private List<String> toUser;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 给所有人
     */
    private Boolean isToAll;

    /**
     * 标签
     */
    private Integer tagId;

    /**
     * 内容
     */
    private String content;

    /**
     * 素材id
     */
    private String mediaId;

    /**
     * 消息发送时间
     */
    private Long sendTime;

    /**
     * 群发消息总数
     */
    private Integer totalCount;

    /**
     * 发送成功总数
     */
    private Integer sentCount;

    /**
     * 发送失败总数
     */
    private Integer errorCount;

    /**
     * 微信消息id
     */
    private String msgId;

    /**
     * 状态 1：成功 2 ：失败
     */
    private Integer status;

    /**
     * 消息发送失败原因
     */
    private String reason;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId;
    }

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Boolean getToAll() {
        return isToAll;
    }

    public void setToAll(Boolean toAll) {
        isToAll = toAll;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "WechatGroupMsgVO{" +
                "appId='" + appId +
                ",fsEa='" + fsEa +
                ",sender='" + sender +
                ",wechatAppId='" + wechatAppId +
                ",toUser='" + toUser +
                ",msgType='" + msgType +
                ",isToAll='" + isToAll +
                ",tagId='" + tagId +
                ",content='" + content +
                ",mediaId='" + mediaId +
                ",sendTime='" + sendTime +
                ",totalCount='" + totalCount +
                ", sentCount=" + sentCount +
                ", errorCount=" + errorCount +
                ", reason=" + reason +
                ", msgId=" + msgId +
                ", status=" + status +
                '}';
    }
}
