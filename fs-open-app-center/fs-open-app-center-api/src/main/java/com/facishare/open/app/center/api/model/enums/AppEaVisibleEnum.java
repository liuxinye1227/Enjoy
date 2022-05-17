package com.facishare.open.app.center.api.model.enums;

/**
 * /**
 * 企业对应用的可用性类型.
 *
 * Created by zenglb on 2015/12/1.
 */
public enum AppEaVisibleEnum {
    /**
     * 包含.
     */
    INCLUDE(1),
    /**
     * 排除.
     */
    EXCLUDE(2),;

    private int value;

    /**
     * 值.
     * @param value
     */
    private AppEaVisibleEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
