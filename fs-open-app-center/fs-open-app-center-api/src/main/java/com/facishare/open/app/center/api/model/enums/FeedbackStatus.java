package com.facishare.open.app.center.api.model.enums;

/**
 * Created by xialf on 1/20/16.
 *
 * @author xialf
 */
public enum FeedbackStatus {
    /**
     * 已删除.
     */
    DELETED(0),

    /**
     * 新建.
     */
    NEW(1),

    /**
     * 已处理.
     */
    PROCESSED(2);
    private final int code;

    FeedbackStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 根据code反馈状态.
     *
     * @param code 状态code
     * @return 反馈状态
     */
    public static FeedbackStatus getByCode(int code) {
        for (FeedbackStatus e : FeedbackStatus.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
