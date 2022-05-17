package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.enums.VisibleEnum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 应用是否对企业可见。
 *
 * @author zenglb
 * @date 2015年8月31日
 */
public class AppEaVisibleVO implements Serializable {
    private static final long serialVersionUID = -4719117638429176508L;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 企业可见类型.
     */
    private VisibleEnum visibleEnum;

    /**
     * 企业账号.
     */
    private List<FsEaVO> fsEas;

    public AppEaVisibleVO() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public VisibleEnum getVisibleEnum() {
        return visibleEnum;
    }

    public void setVisibleEnum(VisibleEnum visibleEnum) {
        this.visibleEnum = visibleEnum;
    }

    public List<FsEaVO> getFsEas() {
        return fsEas;
    }

    public void setFsEas(List<FsEaVO> fsEas) {
        this.fsEas = fsEas;
    }

    @Override
    public String toString() {
        return "AppEaVisibleVO{" +
                "appId='" + appId + '\'' +
                ", visibleEnum=" + visibleEnum +
                ", fsEas=" + fsEas +
                '}';
    }
}
