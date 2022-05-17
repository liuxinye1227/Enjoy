package com.facishare.open.app.center.api.model.enums;

/**
 * 客户端类型.
 */
public enum AppAccessTypeEnum {
    ANDROID(1, "ANDROID端"), IOS(2, "IOS端"),WEB(3, "WEB端"),SERVICE(4, "服务号WEB端"),LINK_SERVICE(5, "互联服务号WEB端");

    /**
     * 类型.
     */
    private final int type;
    /**
     * 描述.
     */
    private final String desc;

    AppAccessTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static AppAccessTypeEnum getByCode(int code) {
        for (AppAccessTypeEnum accessTypeEnum : values()) {
            if (accessTypeEnum.type == code) {
                return accessTypeEnum;
            }
        }
        return null;
    }
}
