package com.facishare.open.manage.ajax.result;


import com.facishare.open.manage.ajax.code.AjaxCode;

/**
 * 用于ajax返回.
 *
 * @author zenglb
 * @date 2015年8月3日
 */
public class AjaxResult {

    /**
     * 错误码.
     */
    private Integer errCode = AjaxCode.OK;

    /**
     * 错误信息.
     */
    private String errMsg = "OK";

    /**
     * 消息数据.
     */
    private Object data;

    public AjaxResult(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public AjaxResult(Object data) {
        this(AjaxCode.OK, "OK");
        this.data = data;
    }

    public AjaxResult(Integer errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "AjaxResult [errCode=" + errCode + ", errMsg=" + errMsg + ", data=" + data + "]";
    }
}
