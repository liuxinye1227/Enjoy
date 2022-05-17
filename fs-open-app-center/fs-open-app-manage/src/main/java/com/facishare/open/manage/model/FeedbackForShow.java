package com.facishare.open.manage.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 用来展示的用户反馈信息,增加了公司名称和用户名字.
 * Created by xialf on 1/25/16.
 *
 * @author xialf
 */
public class FeedbackForShow {
    private Integer id;

    /**
     * 用户id.
     */
    private String fsUserId;

    /**
     * 用户名称.
     */
    private String userName;

    /**
     * 反馈内容.
     */
    private String content;

    /**
     * 公司名称.
     */
    private String eaName;

    /**
     * 联系方式.
     */
    private String contact;

    /**
     * 创建时间.
     */
    private Date gmtCreate;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEaName() {
        return eaName;
    }

    public void setEaName(String eaName) {
        this.eaName = eaName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("fsUserId", fsUserId)
                .append("userName", userName)
                .append("content", content)
                .append("eaName", eaName)
                .append("contact", contact)
                .append("gmtCreate", gmtCreate)
                .toString();
    }
}
