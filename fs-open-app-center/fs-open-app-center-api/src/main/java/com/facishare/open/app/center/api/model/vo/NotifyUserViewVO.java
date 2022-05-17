package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.EmployeeRange;

import java.io.Serializable;

/**
 * Description: 采用企信通知用户更新
 * User: zhouq
 * Date: 2016/11/15
 */
public class NotifyUserViewVO implements Serializable {

    private static final long serialVersionUID = 183549980053740362L;
    /**
     * 企业ea
     */
    private String fsEa;
    /**
     * 企业应用Id
     */
    private String appId;

    /**
     * ad工程的checkAppUpdated 对应的ModuleKey
     */
    private String checkAppUpdatedKey;

    /**
     * 企信分发的Key
     */
    private Integer msgSessionKey;

    /**
     * 清空缓存的级别:是否全网
     */
    private boolean isAll;

    /**
     * 清空缓存对象
     */
    private EmployeeRange view;

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getMsgSessionKey() {
        return msgSessionKey;
    }

    public void setMsgSessionKey(Integer msgSessionKey) {
        this.msgSessionKey = msgSessionKey;
    }

    public String getCheckAppUpdatedKey() {
        return checkAppUpdatedKey;
    }

    public void setCheckAppUpdatedKey(String checkAppUpdatedKey) {
        this.checkAppUpdatedKey = checkAppUpdatedKey;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }

    public EmployeeRange getView() {
        return view;
    }

    public void setView(EmployeeRange view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "NotifyUserViewVO{" +
                "fsEa='" + fsEa + '\'' +
                ", appId='" + appId + '\'' +
                ", checkAppUpdatedKey='" + checkAppUpdatedKey + '\'' +
                ", msgSessionKey=" + msgSessionKey +
                ", isAll=" + isAll +
                ", view=" + view +
                '}';
    }
}
