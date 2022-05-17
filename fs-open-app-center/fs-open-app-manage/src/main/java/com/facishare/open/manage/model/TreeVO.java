package com.facishare.open.manage.model;

/**
 * @author songk
 * @date 2015年9月11日
 */
public class TreeVO {

    /**
     * 菜单ID
     */
    private String id;

    /**
     * 菜单父ID
     */
    private String pId = "0";

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单展开
     */
    private String open = "true";

    /**
     * 消息类型    1表示文本消息   2表示事件消息
     */
    private int type = 0;

    /**
     * 参数值
     */
    private String actionParam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActionParam() {
        return actionParam;
    }

    public void setActionParam(String actionParam) {
        this.actionParam = actionParam;
    }
}
