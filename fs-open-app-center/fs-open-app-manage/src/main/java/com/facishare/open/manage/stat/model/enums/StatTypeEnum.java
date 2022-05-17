package com.facishare.open.manage.stat.model.enums;

public enum StatTypeEnum {
    EA_OPEN(1, "企业开启数"),
    EA_USE(5, "企业使用数"),
    APP_PVUV(9, "应用pvuv"),;

    private final int type;
    private final String name;

    private StatTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
