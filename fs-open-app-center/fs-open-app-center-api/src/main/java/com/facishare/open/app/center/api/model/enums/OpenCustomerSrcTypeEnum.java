package com.facishare.open.app.center.api.model.enums;

/**
 * 客服来源类型.
 * @author chenzs
 * @date 2016年11月01日
 */

public enum OpenCustomerSrcTypeEnum {
    /**
     * 1.手动添加
     */
    BY_HAND(1, "byhand", "手动"),
    /**
     * 2.crm同步
     */
    CRM(2, "crm", "CRM");

    private final int code;
    private final String name;
    private final String desc;

    OpenCustomerSrcTypeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static OpenCustomerSrcTypeEnum getByCode(int code) {
        for (OpenCustomerSrcTypeEnum srcType : values()) {
            if (code == srcType.code) {
                return srcType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
