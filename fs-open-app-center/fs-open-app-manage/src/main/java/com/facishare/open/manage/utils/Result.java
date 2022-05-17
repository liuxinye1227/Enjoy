package com.facishare.open.manage.utils;

/**
 * 返回结果
 *
 * @author songk
 */
public class Result {

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回编号
     * 默认为成功编号
     */
    private String code = "0001";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
