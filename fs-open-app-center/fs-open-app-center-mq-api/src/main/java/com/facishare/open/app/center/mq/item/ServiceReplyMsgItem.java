package com.facishare.open.app.center.mq.item;


import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;


public class ServiceReplyMsgItem extends ProtoBase {
    
    private static final long serialVersionUID = 4931624838072167933L;

    /**
     * 服务号id.
     */
    @Tag(1)
    private String appId;

    /**
     * 企业账户
     */
    @Tag(2)
    private String enterpriseAccount;

    /**
     * 用户ID.
     */
    @Tag(3)
    private Integer userId;
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ServiceReplyMsgItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", userId=" + userId +
                '}';
    }
}
