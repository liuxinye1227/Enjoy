package com.facishare.open.app.center.api.model.property;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * OpenDev properties
 *
 * @author chenzengyong
 * @date on 2016/1/11.
 */
public class OpenDevProperties implements Serializable{

    private static final long serialVersionUID = -6100003335568301819L;

    /**
     * 开发者详情
     */
    private String intro;

    /**
     * 手机号码
     */
    private String mobileNum;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public  String getJsonString() {
        return new Gson().toJson(this);
    }

    public static OpenDevProperties fromJson(String json) {
        return new Gson().fromJson(json, OpenDevProperties.class);
    }

}
