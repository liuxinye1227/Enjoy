package com.facishare.open.app.center.mq.item;

import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:应用管理员变更
 * User: zhouq
 * Date: 2016/6/23
 */
public class AppAdminItem  extends ProtoBase implements Serializable{

    private static final long serialVersionUID = 4931624838072167933L;

    public static final Integer FLAG = 1;

    public static final String TOPIC = "AppAdminEvent";

    /**
     * 应用id.
     */
    @Tag(1)
    private String appId;

    /**
     * 企业账户
     */
    @Tag(2)
    private String enterpriseAccount;

    /**
     * 应用管理员账号.
     */
    @Tag(3)
    private List<Integer> appAdmins;

    /**
     * 新增的应用管理员账号.
     */
    @Tag(4)
    private List<Integer> adminsAdded;

    /**
     * 删除的应用管理员账号.
     */
    @Tag(5)
    private List<Integer> adminsRemoved;

    /**
     * 操作时间
     */
    @Tag(6)
    private Date operationTime;

    /**
     * 用户ID.
     */
    @Tag(7)
    private Integer userId;

    /**
     * 操作来源，参考com.facishare.open.app.center.api.model.enums.OperationSource.
     */
    @Tag(8)
    private Integer operationSource;

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

    public List<Integer> getAppAdmins() {
        return appAdmins;
    }

    public void setAppAdmins(List<Integer> appAdmins) {
        this.appAdmins = appAdmins;
    }

    public List<Integer> getAdminsAdded() {
        return adminsAdded;
    }

    public void setAdminsAdded(List<Integer> adminsAdded) {
        this.adminsAdded = adminsAdded;
    }

    public List<Integer> getAdminsRemoved() {
        return adminsRemoved;
    }

    public void setAdminsRemoved(List<Integer> adminsRemoved) {
        this.adminsRemoved = adminsRemoved;
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

    public Integer getOperationSource() {
        return operationSource;
    }

    public void setOperationSource(Integer operationSource) {
        this.operationSource = operationSource;
    }

    @Override
    public String toString() {
        return "AppAdminItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", appAdmins=" + appAdmins +
                ", adminsAdded=" + adminsAdded +
                ", adminsRemoved=" + adminsRemoved +
                ", operationTime=" + operationTime +
                ", userId=" + userId +
                ", operationSource=" + operationSource +
                '}';
    }
}
