package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 服务号工作台统计信息DO
 * Created by liqiulin on 2016/8/9.
 */
public class ServiceDashboardStatisticsDO extends BaseDO {
    /**
     * 服务号ID
     */
    private String appId;

    /**
     * 群发消息数
     */
    private Long sendMsgCount;

    /**
     * 员工反馈消息数
     */
    private Long receiveMsgCount;

    /**
     * 消息查看人数（就是进入服务号的人数）
     */
    private Long viewCount;

    /**
     * 图文消息评论数
     */
    private Long commentCount;

    /**
     * 图文消息点赞数
     */
    private Long likeCount;

    /**
     * 图文消息阅读数
     */
    private Long readCount;

    /**
     * 统计数据截止时间
     */
    private Date dataEndTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getSendMsgCount() {
        return sendMsgCount;
    }

    public void setSendMsgCount(Long sendMsgCount) {
        this.sendMsgCount = sendMsgCount;
    }

    public Long getReceiveMsgCount() {
        return receiveMsgCount;
    }

    public void setReceiveMsgCount(Long receiveMsgCount) {
        this.receiveMsgCount = receiveMsgCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Date getDataEndTime() {
        return dataEndTime;
    }

    public void setDataEndTime(Date dataEndTime) {
        this.dataEndTime = dataEndTime;
    }
}
