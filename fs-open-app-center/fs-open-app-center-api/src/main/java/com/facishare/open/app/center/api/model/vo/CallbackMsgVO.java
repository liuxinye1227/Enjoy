package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * Description: 群发消息到微信回调对象
 * User: zhouq
 * Date: 2016/11/2
 */
public class CallbackMsgVO implements Serializable{

    private static final long serialVersionUID = -5228688094608595738L;
    /**
     * 微信消息id
     */
    private String msgId;

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
     * 状态 1：成功 2 ：失败
     */
    private Integer status;

    /**
     * 消息发送失败原因
     */
    private String reason;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
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
        return "CallbackMsgVO{" +
                "msgId='" + msgId + '\'' +
                ", totalCount=" + totalCount +
                ", sentCount=" + sentCount +
                ", errorCount=" + errorCount +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
