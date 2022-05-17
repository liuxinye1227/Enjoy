package com.facishare.wechat.access.utils;

/**
 * Created by wangtao on 2015/11/13.
 */
public class PropertiesUtil {


    private String fcpServerIp;

    private int fcpServerPort;

    public int getFcpServerPort() {
        return fcpServerPort;
    }

    public void setFcpServerPort(int fcpServerPort) {
        this.fcpServerPort = fcpServerPort;
    }

    public String getFcpServerIp() {
        return fcpServerIp;
    }

    public void setFcpServerIp(String fcpServerIp) {
        this.fcpServerIp = fcpServerIp;
    }
}
