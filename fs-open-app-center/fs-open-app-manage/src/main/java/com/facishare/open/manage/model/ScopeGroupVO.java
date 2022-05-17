package com.facishare.open.manage.model;

import java.io.Serializable;

/**
 * @author zenglb
 * @date 2015年9月18日
 */
public class ScopeGroupVO implements Serializable {
    private static final long serialVersionUID = 3227792013814104990L;

    /**
     * 权限组名
     */
    private String name;

    /**
     * 权限组
     */
    private String scopeGroup;

    /**
     * 是否选中。
     */
    private Boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScopeGroup() {
        return scopeGroup;
    }

    public void setScopeGroup(String scopeGroup) {
        this.scopeGroup = scopeGroup;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


}
