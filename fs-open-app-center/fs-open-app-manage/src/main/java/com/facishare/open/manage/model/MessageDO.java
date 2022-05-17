package com.facishare.open.manage.model;

import com.facishare.open.material.api.enums.MessageStatusEnum;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.io.Serializable;

/**
 * @describe: 消息表DO
 * @author: xiaoweiwei
 * @date: 2016/9/21 18:06
 */
@Entity(value = "Message", noClassnameStored = true)
public class MessageDO implements Serializable {
    private static final long serialVersionUID = -3477565845907787908L;
    @Id
    private ObjectId id;
    /**
     * 消息id
     */
    @Indexed(name = "messageId")
    private String messageId;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 所属者（企业创建为企业号、第三方开发者创建为第三方账号）
     */
    private String ownerId;
    /**
     * 素材id
     */
    @Indexed(name = "materialId")
    private String materialId;

    /**
     * 状态
     * @see MessageStatusEnum
     */
    private Integer status;

    /**
     * 类型
     * @see com.facishare.open.material.api.enums.MaterialTypeEnum
     */
    private Integer type;

    /**
     * 发送类型.
     * @see com.facishare.open.material.api.enums.MessageSendTypeEnum
     */
    private Integer sendType;
    /**
     * 文本内容
     */
    private String textContent;

    /**
     * 图片，文件，音频，视频，则对应fastdfs返回的文件id
     */
    @Indexed(name = "fileId")
    private String fileId;
    /**
     * 发送者
     */
    private String sender;

    /**
     * 接受范围
     */
    private String accepter;

    /**
     * 接收人数
     */
    private Integer accepterNum;


    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间.
     */
    private Long modifiedTime;

    /**
     * 外部发送总数.
     */
    private Integer outSendTotal;

    /**
     * 发送失败.
     */
    private Integer outSendFailedNum;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public Integer getAccepterNum() {
        return accepterNum;
    }

    public void setAccepterNum(Integer accepterNum) {
        this.accepterNum = accepterNum;
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

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Integer getOutSendTotal() {
        return outSendTotal;
    }

    public void setOutSendTotal(Integer outSendTotal) {
        this.outSendTotal = outSendTotal;
    }

    public Integer getOutSendFailedNum() {
        return outSendFailedNum;
    }

    public void setOutSendFailedNum(Integer outSendFailedNum) {
        this.outSendFailedNum = outSendFailedNum;
    }

    @Override
    public String toString() {
        return "MessageDO{" +
                "id=" + id +
                ", messageId='" + messageId + '\'' +
                ", appId='" + appId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", sendType=" + sendType +
                ", textContent='" + textContent + '\'' +
                ", fileId='" + fileId + '\'' +
                ", sender='" + sender + '\'' +
                ", accepter='" + accepter + '\'' +
                ", accepterNum=" + accepterNum +
                ", outSendTotal=" + outSendTotal +
                ", outSendFailedNum=" + outSendFailedNum +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
