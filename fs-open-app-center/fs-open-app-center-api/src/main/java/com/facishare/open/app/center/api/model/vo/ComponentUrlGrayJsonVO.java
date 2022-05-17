package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zenglb on 2016/4/13.
 */
public class ComponentUrlGrayJsonVO implements Serializable {

    /**
     * 组件url
     */
    private String loginUrl;

    /**
     * 企业信息
     */
    private List<FsEaVO> fsEaList;

    public List<FsEaVO> getFsEaList() {
        return fsEaList;
    }

    public void setFsEaList(List<FsEaVO> fsEaList) {
        this.fsEaList = fsEaList;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public String toString() {
        return "ComponentUrlGrayJsonVO{" +
                "loginUrl='" + loginUrl + '\'' +
                ", fsEaList=" + fsEaList +
                '}';
    }
}
