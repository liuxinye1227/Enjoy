package com.facishare.pay.business.exception;

/**
 * Created by wangtao on 2015/10/22.
 */
public class PayBusinessExcrption extends RuntimeException {


    private static final long serialVersionUID = 606581022369842928L;

    private int errorCode;

    private String errMessage;

    public PayBusinessExcrption() {
        super();
        this.setErrorCode(-2);

    }

    public PayBusinessExcrption(String message) {
        super(message);
        this.setErrorCode(-2);
    }

    public PayBusinessExcrption(int errCode, String message) {
        super(message);
        this.setErrorCode(errCode);
    }

    public PayBusinessExcrption(String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(-2);
    }

    public PayBusinessExcrption(Throwable cause) {
        super(cause);
        this.setErrorCode(-2);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
