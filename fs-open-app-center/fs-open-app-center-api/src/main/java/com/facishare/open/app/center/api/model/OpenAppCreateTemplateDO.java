package com.facishare.open.app.center.api.model;

/**
 * Created by zenglb on 2016/3/17.
 */
public class OpenAppCreateTemplateDO extends BaseDO {

    /**
     * 创建模板id
     */
    private String templateId;
    /**
     * 创建模板类型.
     */
    private Integer templateType;
    /**
     * 应用icon.
     */
    private String templateIcon;
    /**
     * 应用标题.
     */
    private String title;
    /**
     * 模板分类ID
     */
    private String categoryId;
    /**
     * 模板顺序
     */
    private Integer templateOrder;
    /**
     * 需要初始化的json.
     */
    private String executeJson;
    /**
     * 此模板创建的服务号个数
     */
    private Integer serviceNum;

    public OpenAppCreateTemplateDO() {
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getTemplateIcon() {
        return templateIcon;
    }

    public void setTemplateIcon(String templateIcon) {
        this.templateIcon = templateIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecuteJson() {
        return executeJson;
    }

    public void setExecuteJson(String executeJson) {
        this.executeJson = executeJson;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTemplateOrder() {
        return templateOrder;
    }

    public void setTemplateOrder(Integer templateOrder) {
        this.templateOrder = templateOrder;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    @Override
    public String toString() {
        return "OpenAppCreateTemplateDO{" +
                "templateId='" + templateId + '\'' +
                ", templateType=" + templateType +
                ", templateIcon='" + templateIcon + '\'' +
                ", title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", templateOrder=" + templateOrder +
                ", executeJson='" + executeJson + '\'' +
                ", serviceNum=" + serviceNum +
                '}';
    }
}
