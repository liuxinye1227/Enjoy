package com.facishare.open.app.center.api.model.enums;

/**
 * 跳转URL枚举
 *
 * @author chenzengyong
 * @date on 2016/2/25.
 */
public enum TryUrlEnum {

    TRY_URL(1, "tryUrl"),//试用url
    AUTH_URL(2, "authUrl"),//授权url
    NOTIFY_ADMIN_URL(3, "notifyAdminUrl"),//通知管理员开通
    VOID(4, "void"),//无




    ;


    private int code;

    private String desc;

    TryUrlEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
