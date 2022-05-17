package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;

import java.util.Date;

/**
 * Created by xialf on 10/16/15.
 *
 * @author xialf
 */
public class OpenAppComponentVO {
    final private OpenAppComponentDO openAppComponentDO;
    private Date appAuthGmtCreate;
    private String appCreater;

    /**
     * 付费类型
     */
    private PayTypeEnum payTypeEnum;
    /**
     * 应用类型.1 自定义应用 2 开发者应用 3 基础应用
     */
    private Integer appType;

    public OpenAppComponentVO(OpenAppComponentDO componentDO) {
        this.openAppComponentDO = componentDO;
    }

    public OpenAppComponentDO getOpenAppComponentDO() {
        return openAppComponentDO;
    }

    public String getComponentId() {
        return openAppComponentDO.getComponentId();
    }

    public String getComponentName() {
        return openAppComponentDO.getComponentName();
    }

    public String getAppId() {
        return openAppComponentDO.getAppId();
    }

    public String getComponentDesc() {
        return openAppComponentDO.getComponentDesc();
    }

    public Integer getComponentLabel() {
        return openAppComponentDO.getComponentLabel();
    }

    public Integer getComponentType() {
        return openAppComponentDO.getComponentType();
    }

    public Date getAppAuthGmtCreate() {
        return appAuthGmtCreate;
    }

    public void setAppAuthGmtCreate(Date appAuthGmtCreate) {
        this.appAuthGmtCreate = appAuthGmtCreate;
    }

    public String getAppCreater() {
        return appCreater;
    }

    public void setAppCreater(String appCreater) {
        this.appCreater = appCreater;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }
}
