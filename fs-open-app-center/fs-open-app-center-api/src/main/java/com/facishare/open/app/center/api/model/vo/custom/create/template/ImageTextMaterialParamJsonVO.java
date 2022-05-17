package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;

/**
 * Created by zenglb on 2016/3/29.
 */
public class ImageTextMaterialParamJsonVO implements Serializable {
    private String id;
    private String title;
    private String author;
    private String content;
    private Integer type;
    private String summary;
    private String coverImageUrl;
    private Boolean isCoverImageInText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Boolean getCoverImageInText() {
        return isCoverImageInText;
    }

    public void setCoverImageInText(Boolean coverImageInText) {
        isCoverImageInText = coverImageInText;
    }
}
