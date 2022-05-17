package com.facishare.open.app.center.api.model.enums;

/**
 * Created by xialf on 11/17/15.
 *
 * @author xialf
 */
public enum AppModeEnum {
    COMMON(1, "普通模式"),
    DEV(2, "开发模式");
    private final int code;
    private final String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    AppModeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取应用模式.
     *
     * @param code 模式code
     * @return 应用模式
     */
    public static AppModeEnum getByCode(int code) {
        for (AppModeEnum e : AppModeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
