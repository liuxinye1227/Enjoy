package com.facishare.open.app.center.api.model.enums;



/**
 * Created by xialf on 2/25/16.
 *
 * @author xialf
 */
public enum PayStatus {
    /**
     * 从未试用或购买过.
     */
    NEVER_USED(0),
    /**
     * 已购买.
     */
    PURCHASE(1),
    /**
     * 试用中.
     */
    TRIAL(2),
    /**
     * 试用到期.
     */
    TRIAL_EXPIRED(3),
    /**
     * 购买到期.
     */
    PURCHASE_EXPIRED(4);

    private int code;

    PayStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PayStatus getByCode(int code) {
        for (PayStatus e : PayStatus.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
