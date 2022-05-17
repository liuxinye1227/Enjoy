package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.enums.FeedbackStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xialf on 1/20/16.
 *
 * @author xialf
 */
public class FeedbackVO implements Serializable {
    private static final long serialVersionUID = 2814830521323001879L;

    private Integer id;

    /**
     * 用户id.
     */
    private String fsUserId;

    /**
     * 反馈内容.
     */
    private String content;

    /**
     * 联系方式.
     */
    private String contact;

    /**
     * 创建时间.
     */
    private Date gmtCreate;

    /**
     * 反馈状态.
     */
    private FeedbackStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFsUserId() {
        return fsUserId;
    }

    public void setFsUserId(String fsUserId) {
        this.fsUserId = fsUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }
}
