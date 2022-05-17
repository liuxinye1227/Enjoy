package com.facishare.open.app.center.api.model.enums;

/**
 * 试用类型
 *
 * @author chenzengyong
 * @date on 2016/2/25.
 */
public enum TryTypeEnum {
    NOT_TRY(1),//不可试用
    ENTERPRISE_EMPLOYEE_TRY(2),//可企业，个人试用
    ENTERPRISE_TRY(3),//可企业试用

    ;


    private int code;

    TryTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
