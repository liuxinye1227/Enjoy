package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;

/**
 * Created by zenglb on 2016/3/23.
 */
public class AppCreateTemplateDtlImgVO implements Serializable{

    /**
     * 标题.
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 描述.
     */
    private String dtlDesc;
    /**
     * 图片地址.
     */
    private String imageUrl;

    /**
     * 类型.
     */
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDtlDesc() {
        return dtlDesc;
    }

    public void setDtlDesc(String dtlDesc) {
        this.dtlDesc = dtlDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return "AppCreateTemplateDtlImgVO{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", dtlDesc='" + dtlDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
