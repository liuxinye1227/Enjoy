package com.facishare.open.app.center.api.model.enums;

import org.springframework.util.StringUtils;

/**
 * Author: Ansel Qiao
 * Create Time: 15/8/21
 */

public enum IconType {

    IOS(1, "ios"), ANDROID(2, "android"), WEB(3, "web"), SERVICE(4, "service"), LOGO(5, "logo");

    private int value;

    private String message;

    IconType(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static IconType getByMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            throw new IllegalArgumentException("type is illegal");
        }
        for (IconType type : values()) {
            if (message.toLowerCase().equals(type.getMessage())) {
                return type;
            }
        }
        throw new IllegalArgumentException("type " + message + " is not found");
    }
}
