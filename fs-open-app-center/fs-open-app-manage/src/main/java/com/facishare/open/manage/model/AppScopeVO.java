package com.facishare.open.manage.model;

/**
 * 应用权限信息
 * Created by zhouq
 * on 2016/4/20.
 */
public class AppScopeVO {

    /**
     * 权限值,示例: get_user_info
     */
    private String scope;

    /**
     * 权限中文名称.示例:获取用户信息
     */
    private String scopeName;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppScopeVO that = (AppScopeVO) o;

        if (!getScope().equals(that.getScope())) return false;
        return getScopeName().equals(that.getScopeName());

    }

    @Override
    public int hashCode() {
        int result = getScope().hashCode();
        result = 31 * result + getScopeName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AppScopeVO{" +
                "scope='" + scope + '\'' +
                ", scopeName='" + scopeName + '\'' +
                '}';
    }
}
