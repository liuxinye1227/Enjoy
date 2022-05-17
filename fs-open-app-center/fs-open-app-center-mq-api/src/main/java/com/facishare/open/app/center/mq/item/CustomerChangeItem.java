package com.facishare.open.app.center.mq.item;

import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;

import java.util.Date;
import java.util.List;

/**
 * 客服人员变更通知
 * @author huanghp
 * @date 2016-08-09
 */
public class CustomerChangeItem extends ProtoBase {

    private static final long serialVersionUID = 8806200399932207237L;

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
     * 客服变更情况下，新增客服人员列表
     */
    @Tag(3)
    private List<Integer> addCustomerIds;
    
    /**
     * 客服变更情况下，删除客服人员列表
     */
    @Tag(4)
    private List<Integer> removeCustomerIds;

    /**
     * 操作时间
     */
    @Tag(5)
    private Date operationTime;

    /**
     * 用户ID.
     */
    @Tag(6)
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

    public List<Integer> getAddCustomerIds() {
        return addCustomerIds;
    }

    public void setAddCustomerIds(List<Integer> addCustomerIds) {
        this.addCustomerIds = addCustomerIds;
    }

    public List<Integer> getRemoveCustomerIds() {
        return removeCustomerIds;
    }

    public void setRemoveCustomerIds(List<Integer> removeCustomerIds) {
        this.removeCustomerIds = removeCustomerIds;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CustomerChangeItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", addCustomerIds=" + addCustomerIds +
                ", removeCustomerIds=" + removeCustomerIds +
                ", operationTime=" + operationTime +
                ", userId=" + userId +
                '}';
    }
}
