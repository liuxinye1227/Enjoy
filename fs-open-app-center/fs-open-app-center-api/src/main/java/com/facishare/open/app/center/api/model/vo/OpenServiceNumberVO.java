package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 类描述： 服务号
 *  创建人：zhouq 
 *  创建时间：2016年3月30日 下午2:47:44
 * @version
 */

public class OpenServiceNumberVO implements Serializable {

    private static final long serialVersionUID = -2129086077341990412L;

    /**
     * 企业应用Id
     */
    private String appId;

    /**
     * 企业应用名称
     */
    private String appName;

    /**
     * 图片url
     */
    private String imageUrl;

    /**
     * 应用描述
     */
    private String desc;

    public  OpenServiceNumberVO(){
        
    }
    
    public  OpenServiceNumberVO(String appId, String appName, String imageUrl, String desc){
        this.appId = appId;
        this.appName = appName;
        this.imageUrl = imageUrl;
        this.desc = desc;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OpenAppVO [appId=" + appId + ", appName=" + appName + ", imageUrl=" + imageUrl + ", desc=" + desc + "]";
    }

}
