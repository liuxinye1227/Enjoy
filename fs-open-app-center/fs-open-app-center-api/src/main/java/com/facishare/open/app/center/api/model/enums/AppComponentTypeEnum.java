package com.facishare.open.app.center.api.model.enums;

public enum AppComponentTypeEnum {
    WEB(2, "web端"), APP(1, "app端"), SERVICE(3, "服务号"), LINK_SERVICE(4, "互联服务号");

    private final int type;
    private final String desc;

    AppComponentTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static AppComponentTypeEnum getByCode(int code) {
        for (AppComponentTypeEnum accessTypeEnum : values()) {
            if (accessTypeEnum.type == code) {
                return accessTypeEnum;
            }
        }
        throw new EnumConstantNotPresentException(AppComponentTypeEnum.class, Integer.toString(code));
    }
}
