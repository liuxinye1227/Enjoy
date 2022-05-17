package com.facishare.wechat.access.utils;

import com.dyuproject.protostuff.Tag;
import com.google.common.base.MoreObjects;

public class EnterpriseSummary {
    
    @Tag(1)
    private int enterpriseId;
    
    @Tag(2)
    private String enterpriseAccount;

    @Tag(3)
    private String enterpriseName;
    
    @Tag(4)
    private int employeeId;
    
    @Tag(5)
    private String employeeName;
    


    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enterpriseId", enterpriseId)
                .add("enterpriseName", enterpriseName)
                .add("enterpriseAccount", enterpriseAccount)
                .add("employeeId", employeeId)
                .add("employeeName", employeeName)
                .toString();
    }
    
}
