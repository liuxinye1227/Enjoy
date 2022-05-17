package com.facishare.open.app.center.api.model.template;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板实体结构
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateDO implements Serializable {

    private static final long serialVersionUID = -2938188567571596175L;

    /**
     * 模板记录在数据库表中ID
     */
    private long id;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 模板对象，不做存储
     */
    private Template template;

    /**
     * 模板创建时间
     */
    private Date createTime;

    /**
     * 模板更新时间
     */
    private Date updateTime;

    /**
     * 模板信息状态
     */
    private int status;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名
     */
    private String appName = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
        Gson gson = new Gson();
        this.template = gson.fromJson(this.templateContent, Template.class);
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}