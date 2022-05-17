package com.facishare.open.manage.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.List;

/**
 * 消息子图文表
 */
@Entity(value = "MessageImageTextParam", noClassnameStored = true)
public class MessageImageTextParamDO implements Serializable{
    private static final long serialVersionUID = -8479147295606237506L;
    @Id
    private ObjectId id;
    /**
     * 消息Id
     */
    private String messageId;
    /**
     * 消息子图文id
     */
    private String imageTextParamId;

    /**
     * 对应的素材子图文id
     */
    private String materialImageTextParamId;
    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章内容.
     */
    private String content;
    /**
     * 插入的培训助手视频ID
     */
    private List<String> videoIds;
    /**
     * 类型
     * @see com.facishare.open.material.api.enums.ImageTextTypeEnum
     */
    private Integer type;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 封面地址
     */
    private String coverImageUrl;
    /**
     * 封面图片是否在正文中
     */
    private Boolean isCoverImageInText;
    /**
     * 子图文状态，mysql中是没有入库
     * @see com.facishare.open.material.api.enums.MaterialStatusEnum
     */
    private Integer status;
    /**
     * 排序标志
     */
    private Integer sortFlag;
    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifiedTime;

    @Override
    public String toString() {
        return "MessageImageTextParamDO{" +
                "id=" + id +
                ", messageId='" + messageId + '\'' +
                ", imageTextParamId='" + imageTextParamId + '\'' +
                ", materialImageTextParamId='" + materialImageTextParamId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", videoIds=" + videoIds +
                ", type=" + type +
                ", summary='" + summary + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", isCoverImageInText=" + isCoverImageInText +
                ", status=" + status +
                ", sortFlag=" + sortFlag +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getImageTextParamId() {
        return imageTextParamId;
    }

    public void setImageTextParamId(String imageTextParamId) {
        this.imageTextParamId = imageTextParamId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Integer sortFlag) {
        this.sortFlag = sortFlag;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    public List<String> getVideoIds() {
        return videoIds;
    }

    public String getMaterialImageTextParamId() {
        return materialImageTextParamId;
    }

    public void setMaterialImageTextParamId(String materialImageTextParamId) {
        this.materialImageTextParamId = materialImageTextParamId;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }
}
