package com.facishare.pay.business.utils;

/**
 * Created by wangtao on 2015/10/30.
 */
public enum LevelType {

    WARN("WARN"),

    ERROR("ERROR"),

    ;

    private String type;

    LevelType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
