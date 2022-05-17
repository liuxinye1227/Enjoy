package com.facishare.open.manage.model;

import com.facishare.open.app.center.api.model.vo.FsEaVO;

import java.io.Serializable;
import java.util.List;

/**
 * 应用参数接收
 *
 * @author zenglb
 * @date 2015年9月22日
 */
public class OpenAppWithAppIdDevIdVO implements Serializable {
    private static final long serialVersionUID = -6015130005331542149L;

    /**
     * 开发者Id
     */
    private String devId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用可见状态 1.全部企业可见 2.部分企业可见.
     */
    private Integer visibleStatus;

    /**
     * 可见企业列表
     */
    private List<FsEaVO> fsEas;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<FsEaVO> getFsEas() {
        return fsEas;
    }

    public void setFsEas(List<FsEaVO> fsEas) {
        this.fsEas = fsEas;
    }

    public Integer getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(Integer visibleStatus) {
        this.visibleStatus = visibleStatus;
    }
}
