/**
 *
 */
package com.facishare.open.manage.model;

import java.util.List;

/**
 * @author songk
 * @date 2015年9月11日
 */
public class MenuPageVO {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单列表
     */
    private List<TreeVO> treeVOList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeVO> getTreeVOList() {
        return treeVOList;
    }

    public void setTreeVOList(List<TreeVO> treeVOList) {
        this.treeVOList = treeVOList;
    }
}
