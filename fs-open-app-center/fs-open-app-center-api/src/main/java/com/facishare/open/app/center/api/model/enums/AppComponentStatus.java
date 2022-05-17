package com.facishare.open.app.center.api.model.enums;

public enum AppComponentStatus {

    VALID(1, "有效"), INVALID(2, "无效");

    private final int status;
    private final String desc;

    private AppComponentStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
