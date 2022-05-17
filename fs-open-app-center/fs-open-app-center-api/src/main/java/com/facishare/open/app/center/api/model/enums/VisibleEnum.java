package com.facishare.open.app.center.api.model.enums;

/**
 * 用于前端显示，应用列表列
 * Created by zenglb on 2015/12/1.
 */
public enum VisibleEnum {
    ALL(1, "全部企业可见"),  PART(2, "仅部分企业可见"),NONE(3, "未发布"),;

    private int code;
    private String name;

    VisibleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static VisibleEnum valueByCode(Integer visibleStatus) {
        for (VisibleEnum v : values()) {
            if (v.getCode() == visibleStatus) {
                return v;
            }
        }
        return null;
    }
}
