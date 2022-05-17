package com.facishare.wechat.access.client.handler.impl;

import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.client.handler.MsgHandler;
import com.facishare.wechat.access.client.handler.impl.HeartBeatHandler;
import com.facishare.wechat.access.model.TransData;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录消息处理器
 * Created by lif on 2015/9/30.
 */
public class LoginHandler implements MsgHandler {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {
        try {
            String errorCode = msg.getData().get("errorCode");
            if (errorCode.equals(CommonConstant.LOGIN_PASSWORD_SUCCESS)) {
                logger.info("恭喜【{0}】登录成功, token：{1}", msg.getData().get("userId"), msg.getData().get("token"));
                System.out.println("恭喜【{0}】登录成功, token：{1}"+ msg.getData().get("userId")+ msg.getData().get("token"));
            }

            //发送心跳包
            HeartBeatHandler.startSendHeartBeatTask(ctx);
        } catch (Exception e) {
            logger.error("login error:" + e.getMessage(), e);
        }

    }

}
