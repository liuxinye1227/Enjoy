package com.facishare.open.app.center.api.model.enums;

/**
 *点击按钮事件
 *
 * @author chenzengyong
 * @date on 2016/2/24.
 */
public enum TargetTypeEnum {
    /**
     * 跳转地址.
     */
    URL(1),
    /**
     * 不可跳转.
     */
    GRAY(2),
    /**
     * 个人试用机会用完.
     */
    EMPLOYEE_UNABLE_TRY(3),
    /**
     * 企业试用机会用完.
     */
    ADMIN_UNABLE_TRY(4),

    /**
     *  隐藏按钮.
     */
    HIDE(5),
    ;

    private int code;

    TargetTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
