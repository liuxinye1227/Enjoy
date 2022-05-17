package com.facishare.open.app.center.api.model;

/**
 * 应用申请过的权限 .scope取值为 发消息 ,获取图片 等功能点
 *
 * @author zenglb
 * @date 2015年8月20日
 */
public class OpenAppScopeOrderDO extends BaseDO {

    private static final long serialVersionUID = -1281758829314277412L;

    /**
     * 应用appId
     */
    private String appId;

    /**
     * 权限值,示例: get_user_info
     */
    private String scope;

    /**
     * 权限中文名称.示例:获取用户信息
     */
    private String scopeName;

    public OpenAppScopeOrderDO() {
    }

    public OpenAppScopeOrderDO(String appId, String scope, String scopeName) {
        this.appId = appId;
        this.scope = scope;
        this.scopeName = scopeName;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((scopeName == null) ? 0 : scopeName.hashCode());
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
        OpenAppScopeOrderDO other = (OpenAppScopeOrderDO) obj;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (scopeName == null) {
            if (other.scopeName != null)
                return false;
        } else if (!scopeName.equals(other.scopeName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OpenAppScopeOrderDO [appId=" + appId + ", scope=" + scope + ", scopeName=" + scopeName + "]";
    }
}
