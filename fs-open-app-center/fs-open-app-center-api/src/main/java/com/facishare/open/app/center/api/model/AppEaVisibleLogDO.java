package com.facishare.open.app.center.api.model;

/**
 *  开发者后台管理对应用的灰度发布记录实体.
 *
 * Created by zenglb on 2015/12/3.
 */
public class AppEaVisibleLogDO extends BaseDO{

    private static final long serialVersionUID = -4091450256351299543L;

    /**
     * 操作人.
     */
    private String opUser;

    /**
     * 应用id.
     */
    private String appId;

    /**
     * 消息.
     */
    private String message;

    /**
     * 参数.
     */
    private String param;

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppEaVisibleLogDO that = (AppEaVisibleLogDO) o;

        if (opUser != null ? !opUser.equals(that.opUser) : that.opUser != null) return false;
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return !(param != null ? !param.equals(that.param) : that.param != null);

    }

    @Override
    public int hashCode() {
        int result = opUser != null ? opUser.hashCode() : 0;
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (param != null ? param.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppEaVisibleLogDO{" +
                "opUser='" + opUser + '\'' +
                ", appId='" + appId + '\'' +
                ", message='" + message + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
