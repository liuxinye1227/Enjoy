package com.facishare.wechat.access.client.handler.impl;

import com.facishare.fcp.FcpCodec;
import com.facishare.fcp.client.FcpClient;
import com.facishare.fcp.protocol.*;
import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.client.handler.MsgHandler;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangtao on 2015/11/18.
 */
public class BusiHandler implements MsgHandler {
    private static Logger logger = LoggerFactory.getLogger(BusiHandler.class);


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {



        try {
            logger.debug("SERVER REV: {} ", msg);
        } catch (Exception e) {
            logger.error("error:{}", e);
        }
    }
}
