package com.facishare.open.app.center.api.model.enums;

/**
 * 应用付费类型 Created by chenzengyong on 15/10/13.
 */
public enum PayTypeEnum {

    CHARGE(1, "CHARGE", "付费"), FREE(2, "FREE", "免费");

    /**
     * 描述
     */
    private String typeDesc;

    /**
     * 描述
     */
    private String typeName;

    /**
     * 收费类型
     */
    private int payType;

    private PayTypeEnum(int payType, String typeName, String typeDesc) {
        this.typeDesc = typeDesc;
        this.typeName = typeName;
        this.payType = payType;
    }

    public int getPayType() {
        return payType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public static PayTypeEnum getByPayType(int payType) {
        for (PayTypeEnum e : PayTypeEnum.values()) {
            if (e.getPayType() == payType) {
                return e;
            }
        }

        return null;
    }
}
