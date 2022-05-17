package com.facishare.open.manage.model;

import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenzs on 2016/10/17.
 */
public class AppServiceRefVO implements Serializable {

    private static final long serialVersionUID = -5541963218925211541L;

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 服务号Id
     */
    private String serviceId;

    /**
     * 新的服务号Id
     */
    private String newServiceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNewServiceId() {
        return newServiceId;
    }

    public void setNewServiceId(String newServiceId) {
        this.newServiceId = newServiceId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
