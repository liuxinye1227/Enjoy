package com.facishare.open.app.center.api.model.enums;

/**
 * 客服角色.
 * @author chenzs
 * @date 2016年11月01日
 */

public enum OpenCustomerRoleEnum {
    /**
     * 1.普通客服.
     */
    COMMON(1, "common", "普通客服"),
    /**
     * 2.专属客服.
     */
    EXCLUSIVE(2, "exclusive", "专属客服"),
    /**
     * 3.客服主管
     */
    MANAGER(3, "manager", "客服主管");

    private final int code;
    private final String name;
    private final String desc;

    OpenCustomerRoleEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static OpenCustomerRoleEnum getByCode(int code) {
        for (OpenCustomerRoleEnum role : values()) {
            if (code == role.code) {
                return role;
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

    @Override
    public String toString() {
        return "OpenCustomerRoleEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
