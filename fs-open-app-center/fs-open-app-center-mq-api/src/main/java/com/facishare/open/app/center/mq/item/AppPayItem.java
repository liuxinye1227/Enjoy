package com.facishare.open.app.center.mq.item;

import com.facishare.open.app.center.mq.item.base.ProtoBase;
import io.protostuff.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:付费应用
 * User: zhouq
 * Date: 2016/6/23
 */
public class AppPayItem extends ProtoBase implements Serializable{

    private static final long serialVersionUID = 4931624838072167933L;

    public static final Integer FLAG = 100;

    public static final String TOPIC = "AppPayEvent";

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
     * 用户ID.
     */
    @Tag(3)
    private Integer userId;

    /**
     * 应用管理员账号.
     */
    @Tag(4)
    private List<Integer> appAdmins;

    /**
     * 是否全公司
     */
    @Tag(5)
    private Boolean isAllEmployees;

    /**
     * 使用到期时间
     */
    @Tag(6)
    private Date expireDate;

    /**
     * 操作时间
     */
    @Tag(7)
    private Date operationTime;

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

    public List<Integer> getAppAdmins() {
        return appAdmins;
    }

    public void setAppAdmins(List<Integer> appAdmins) {
        this.appAdmins = appAdmins;
    }

    public Boolean getAllEmployees() {
        return isAllEmployees;
    }

    public void setAllEmployees(Boolean allEmployees) {
        isAllEmployees = allEmployees;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "AppPayItem{" +
                "appId='" + appId + '\'' +
                ", enterpriseAccount='" + enterpriseAccount + '\'' +
                ", userId=" + userId +
                ", appAdmins=" + appAdmins +
                ", isAllEmployees=" + isAllEmployees +
                ", expireDate=" + expireDate +
                ", operationTime=" + operationTime +
                '}';
    }
}
