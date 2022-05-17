package com.facishare.wechat.access.netty.handler.impl;


import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.handler.HeartBeatHandler;
import com.facishare.wechat.access.netty.handler.MsgHandler;


public class MsgHandlerFactory {

    static AddressBookHandler addressBookHandler = new AddressBookHandler();

    static LoginHandler loginHandler = new LoginHandler();

    static HeartBeatHandler heartBeatHandler = new HeartBeatHandler();

    static BusiHandler busiHandler = new BusiHandler();

    static TokenHandler tokenHandler = new TokenHandler();

    static LogoutHandler logoutHandler = new LogoutHandler();

    static ChatHandler chatHandler = new ChatHandler();

    public static MsgHandler getAppMsgHandler(TransData msg) {

        String command = msg.getHeader().get(CommonConstant.HEADER_KEY_COMMAND);
        if (CommandType.COMMAND_LOGIN.equals(command)) {
            // 登录
            return loginHandler;
        } else if (CommandType.COMMAND_HEARTBEAT.equals(command)) {
            // 心跳
            return heartBeatHandler;
        } else if (CommandType.COMMAND_ADDRESS_BOOK.equals(command)) {
            // 通讯录
            return addressBookHandler;
        } else if (CommandType.COMMAND_BUSI.equals(command)) {
            // 业务操作
            return busiHandler;
        }  else if (CommandType.COMMAND_VERRIFY_TOKEN.equals(command)) {
            // 验证token 是否正确
            return tokenHandler;
        } else if (CommandType.COMMAND_LOGOUT.equals(command)) {
            // 登出
            return logoutHandler;
        } else if (CommandType.COMMAND_CHAT.equals(command)) {
            // 登出
            return chatHandler;
        }  else {
            return null;
        }
    }


}
