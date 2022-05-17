package com.facishare.wechat.access.utils;

import com.facishare.fcp.client.FcpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangtao on 2015/11/17.
 */

public class FcpClientHelper {


    //


    private PropertiesUtil propertiesUtil;

    private FcpClient client;

    public FcpClientHelper(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    public FcpClient getClient() {
        System.out.println("ip:" +propertiesUtil.getFcpServerIp() + "port:" + propertiesUtil.getFcpServerPort());
         client = new FcpClient(propertiesUtil.getFcpServerIp(), propertiesUtil.getFcpServerPort());
        return client;
    }

    public void returnClient(FcpClient client) {
        client.shutDown();
    }

}
