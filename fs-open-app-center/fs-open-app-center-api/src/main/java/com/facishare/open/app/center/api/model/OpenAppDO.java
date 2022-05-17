package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 应用相关数据.
 *
 * @author zenglb
 * @date 2015年8月24日 todo 不要放其他信息，放到vo中去
 */
public class OpenAppDO extends BaseDO {

    private static final long serialVersionUID = 7958202067873623123L;

    /**
     * appId
     */
    private String appId;

    /**
     * app名称
     */
    private String appName;

    /**
     * 应用简介
     */
    private String appDesc;

    /**
     * 应用类型.1 自定义应用 2 开发者应用 3 基础应用
     *
     * @see com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType
     */
    private Integer appType;

    /**
     * 应用模式 1.普通模式,2.开发者模式
     */
    private Integer appMode;

    /**
     * 创建者信息,createrType=1此处为fsuserId为 E.xxx.001中的xxx值
     * createType=2时为open_dev.idr
     */
    private String appCreater;

    /**
     * 创建者类型.1 纷享账号创建 2 开发者账号创建
     */
    private Integer createrType;

    /**
     * 应用分类.如,移动应用这类的数据 1.企业协同 2.移动办公
     */
    private Integer appClass;

    /**
     * 企业服务号名称
     */
    private String serviceName;

    /**
     * 绑定状态,主要相对于企业的应用.1.为绑定.2为停用
     */
    private Integer bindStatus;

    /**
     * 授权时间
     */
    private Date bindTime;

    /**
     * 应用开发者信息
     */
    private OpenDevDO openDevDO;

    /**
     * 1 付费 2免费
     * @see com.facishare.open.app.center.api.model.enums.PayTypeEnum
     *
     */
    private Integer payType;

    /**
     * 试用类型
     * @see com.facishare.open.app.center.api.model.enums.TryTypeEnum
     */
    private Integer tryType;

    public OpenAppDO(){

    }

    public OpenAppDO(String appId, String serviceName){
        this.appId = appId;
        this.serviceName = serviceName;
    }

    public Integer getTryType() {
        return tryType;
    }

    public void setTryType(Integer tryType) {
        this.tryType = tryType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppMode() {
        return appMode;
    }

    public void setAppMode(Integer appMode) {
        this.appMode = appMode;
    }

    public String getAppCreater() {
        return appCreater;
    }

    public void setAppCreater(String appCreater) {
        this.appCreater = appCreater;
    }

    public Integer getCreaterType() {
        return createrType;
    }

    public void setCreaterType(Integer createrType) {
        this.createrType = createrType;
    }

    public Integer getAppClass() {
        return appClass;
    }

    public void setAppClass(Integer appClass) {
        this.appClass = appClass;
    }

    public OpenDevDO getOpenDevDO() {
        return openDevDO;
    }

    public void setOpenDevDO(OpenDevDO openDevDO) {
        this.openDevDO = openDevDO;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OpenAppDO openAppDO = (OpenAppDO) o;

        if (appId != null ? !appId.equals(openAppDO.appId) : openAppDO.appId != null) {
            return false;
        }
        if (appName != null ? !appName.equals(openAppDO.appName) : openAppDO.appName != null) {
            return false;
        }
        if (appDesc != null ? !appDesc.equals(openAppDO.appDesc) : openAppDO.appDesc != null) {
            return false;
        }
        if (appType != null ? !appType.equals(openAppDO.appType) : openAppDO.appType != null) {
            return false;
        }
        if (appMode != null ? !appMode.equals(openAppDO.appMode) : openAppDO.appMode != null) {
            return false;
        }
        if (appCreater != null ? !appCreater.equals(openAppDO.appCreater) : openAppDO.appCreater != null) {
            return false;
        }
        if (createrType != null ? !createrType.equals(openAppDO.createrType) : openAppDO.createrType != null) {
            return false;
        }
        if (appClass != null ? !appClass.equals(openAppDO.appClass) : openAppDO.appClass != null) {
            return false;
        }
        if (serviceName != null ? !serviceName.equals(openAppDO.serviceName) : openAppDO.serviceName != null) {
            return false;
        }
        if (bindStatus != null ? !bindStatus.equals(openAppDO.bindStatus) : openAppDO.bindStatus != null) {
            return false;
        }
        if (bindTime != null ? !bindTime.equals(openAppDO.bindTime) : openAppDO.bindTime != null) {
            return false;
        }
        if (openDevDO != null ? !openDevDO.equals(openAppDO.openDevDO) : openAppDO.openDevDO != null) {
            return false;
        }
        return !(payType != null ? !payType.equals(openAppDO.payType) : openAppDO.payType != null);

    }

    @Override
    public int hashCode() {
        int result = appId != null ? appId.hashCode() : 0;
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (appDesc != null ? appDesc.hashCode() : 0);
        result = 31 * result + (appType != null ? appType.hashCode() : 0);
        result = 31 * result + (appMode != null ? appMode.hashCode() : 0);
        result = 31 * result + (appCreater != null ? appCreater.hashCode() : 0);
        result = 31 * result + (createrType != null ? createrType.hashCode() : 0);
        result = 31 * result + (appClass != null ? appClass.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (bindStatus != null ? bindStatus.hashCode() : 0);
        result = 31 * result + (bindTime != null ? bindTime.hashCode() : 0);
        result = 31 * result + (openDevDO != null ? openDevDO.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OpenAppDO{" +
                "appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appDesc='" + appDesc + '\'' +
                ", appType=" + appType +
                ", appMode=" + appMode +
                ", appCreater='" + appCreater + '\'' +
                ", createrType=" + createrType +
                ", appClass=" + appClass +
                ", serviceName='" + serviceName + '\'' +
                ", bindStatus=" + bindStatus +
                ", bindTime=" + bindTime +
                ", openDevDO=" + openDevDO +
                ", payType=" + payType +
                '}';
    }
}
