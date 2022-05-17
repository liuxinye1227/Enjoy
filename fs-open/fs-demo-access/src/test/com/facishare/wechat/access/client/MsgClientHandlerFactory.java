package com.facishare.wechat.access.client;

import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.client.handler.impl.BusiHandler;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.client.handler.impl.HeartBeatHandler;
import com.facishare.wechat.access.client.handler.MsgHandler;
import com.facishare.wechat.access.client.handler.impl.LoginHandler;

/**
 * Created by lif on 2015/10/22.
 */
public class MsgClientHandlerFactory {
    static LoginHandler loginHandler = new LoginHandler();

    static HeartBeatHandler heartBeatHandler = new HeartBeatHandler();
    static BusiHandler busiHandler = new BusiHandler();

    public static MsgHandler getClientHandler(TransData msg) {
        if (msg == null || msg.getHeader() == null) {
            return null;
        }

        String command = msg.getHeader().get(CommonConstant.HEADER_KEY_COMMAND);
        if (CommandType.COMMAND_LOGIN.equals(command)) {
            // 登录
            return loginHandler;
        } else if (CommandType.COMMAND_HEARTBEAT.equals(command)) {
            // 心跳
            return heartBeatHandler;
        }  else if (CommandType.COMMAND_BUSI.equals(command)) {
            // 业务操作
            return busiHandler;
        } else {
            return null;
        }
    }

}
