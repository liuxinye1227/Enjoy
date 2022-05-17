package com.facishare.open.app.center.api.model.enums;

/**
 * Description:操作来源
 * User: zhouq
 * Date: 2016/8/18
 */
public enum OperationSource {

    /**
     * web浏览器操作.
     */
    WEB(1),

    /**
     * 系统内部操作.
     */
    SYSTEM(2),;

    private int value;

    /**
     * 类型.
     *
     * @param value code
     */
    private OperationSource(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
