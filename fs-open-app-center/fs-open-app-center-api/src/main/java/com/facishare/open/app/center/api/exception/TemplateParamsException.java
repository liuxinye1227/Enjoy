package com.facishare.open.app.center.api.exception;


public class TemplateParamsException extends RuntimeException {

    private static final long serialVersionUID = -4794179229646248058L;

    public TemplateParamsException(String message) {
        super(message);
    }

    public TemplateParamsException(String message, Throwable cause) {
        super(message, cause);
    }

}
