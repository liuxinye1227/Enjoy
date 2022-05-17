package com.facishare.open.app.center.api.model.enums;

/**
 * 应用状态
 *
 * @author zenglb
 * @date 2015年9月6日
 */
public enum AppStatus {
    /**
     * 已上线
     */
    ON_LINE(1, "已上线"),
    /**
     * 已删除
     */
    DELETED(2, "已删除"),
    /**
     * 建设中
     */
    BUILDING(3, "建设中"),
    /**
     * 计划中
     */
    PLANNING(4, "计划中"),
    /**
     * 待审核
     */
    WAIT_AUDIT(5, "待审核"),
    /**
     * 审核中
     */
    AUDITING(6, "审核中"),
    /**
     * 审核通过
     */
    AUDIT_PASS(7, "审核通过"),
    /**
     * 审核不通过
     */
    AUDIT_NO_PASS(8, "审核不通过"),
    /**
     * 已下线
     */
    OFF_LINE(9, "已下线"),;

    private final int status;
    private final String desc;

    private AppStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public String desc() {
        return desc;
    }

    public int status() {
        return status;
    }
}
