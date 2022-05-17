package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.BaseDO;

import java.util.List;

/**
 * 绑定信息
 *
 * @author zenglb
 * @date 2015年8月17日
 */
public class OpenFsUserBindAppVO extends BaseDO {

    private static final long serialVersionUID = -6418820413119814968L;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 企业账号
     */
    private String fsEnterpriseAccount;

    /**
     * 权限字段
     */
    private List<String> scope;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFsEnterpriseAccount() {
        return fsEnterpriseAccount;
    }

    public void setFsEnterpriseAccount(String fsEnterpriseAccount) {
        this.fsEnterpriseAccount = fsEnterpriseAccount;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((fsEnterpriseAccount == null) ? 0 : fsEnterpriseAccount.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OpenFsUserBindAppVO other = (OpenFsUserBindAppVO) obj;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (fsEnterpriseAccount == null) {
            if (other.fsEnterpriseAccount != null)
                return false;
        } else if (!fsEnterpriseAccount.equals(other.fsEnterpriseAccount))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        return true;
    }

}
