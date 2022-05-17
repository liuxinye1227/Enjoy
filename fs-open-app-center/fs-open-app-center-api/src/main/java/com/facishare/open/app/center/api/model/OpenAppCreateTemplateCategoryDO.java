package com.facishare.open.app.center.api.model;

/**
 * Created by liqiulin on 2016/7/14.
 */
public class OpenAppCreateTemplateCategoryDO extends BaseDO {
    /**
     * primary key
     */
    private String categoryId;
    /**
     * 分类标题
     */
    private String categoryTitle;
    /**
     * 分类描述
     */
    private String categoryDesc;
    /**
     * 分类顺序
     */
    private String categoryOrder;

    /**
     * 模板类型
     * 引用枚举类OpenAppCreateTemplateTypeEnum
     * @see com.facishare.open.app.center.api.model.enums.OpenAppCreateTemplateTypeEnum
     */
    private Integer templateType;



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(String categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public Integer getTemplateType() { return templateType; }

    public void setTemplateType(Integer templateType) { this.templateType = templateType; }
}
