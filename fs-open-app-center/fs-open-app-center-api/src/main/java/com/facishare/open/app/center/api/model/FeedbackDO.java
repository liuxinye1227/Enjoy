package com.facishare.open.app.center.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xialf on 1/20/16.
 *
 * @author xialf
 */
public class FeedbackDO implements Serializable {
    private static final long serialVersionUID = 2461247110767894821L;

    private Integer id;
    private String fsUserId;
    private String content;
    private String contact;
    private Date gmtCreate;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
