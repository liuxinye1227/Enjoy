package com.facishare.wechat.access.client;


import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.LoginKey;
import com.facishare.wechat.access.client.handler.MsgHandler;
import com.facishare.wechat.access.model.TransData;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lif on 2015/9/28.
 */
public class MsgClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MsgClientHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try {
//            System.out.println("连接到服务端:ip=" + ctx.channel().remoteAddress());
//
            //登录
//            TransData busiData = new TransData();
//            Map<String, String> header = new HashMap<>();
//            Map<String, String> body = new HashMap<>();
//
//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_LOGIN);
//            body.put(LoginKey.BODY_KEY_EA, "ftetest");
//            body.put(LoginKey.BODY_KEY_USERNAME, "18811110009");
////            body.put(LoginKey.BODY_KEY_USERNAME, "18811110000");
//            body.put(LoginKey.BODY_KEY_PASSWORD, "123456");
//            busiData.setData(body);
//            busiData.setHeader(header);
//            ctx.channel().writeAndFlush(busiData);


//            Map<String, String> header = new HashMap<>();
//            Map<String, String> body = new HashMap<>();
//
//            // 发送业务请求  公共部分
//            TransData busiData = new TransData();
//
//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_BUSI);
//////            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_ADDRESS_BOOK);
//////
//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_LOGOUT);
//
//            header.put(BusiKey.HEADER_KEY_TOKEN, "7f14d690-91ed-4017-8694-6de6a530a78d");

//            Thread.sleep(2000);
            // 发送业务请求  公共部分
//            TransData busiData = new TransData();
//            header = new HashMap<>();
//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_ADDRESS_BOOK);
//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_BUSI);
//            header.put(BusiKey.HEADER_KEY_TOKEN, "a27dbf0b-e899-4577-99dd-f1fd08bc2d03");
//            Map<String, String> body = new HashMap<>();
//
//            // 添加用户
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.addUserInfo");
//            body.put("userName","15814477582");
//            body.put("type","1");
//            body.put("password", "123456");
//
//            // 修改密码
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.addUserInfo");
//
//            body.put("password", "123456");

//            // 添加用户
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.addUserInfo");
//            body.put("userName","wangtao");
//            body.put("type","1");
//            body.put("password", "12345678");
//
//            // 修改密码
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.addUserInfo");
//
//            body.put("password", "123456");

            // 绑卡
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.addUserCard");
//            body.put("cardNo", "625325685555555555566");
//            body.put("createTime", "2015-11-24 12:00:00");
//            body.put("remark", "xxxx");
//            body.put("bankName", ""); // 数据库不能为null
//            body.put("cardName", "王涛");
//            body.put("phone", "15814477582");

            //充值
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.Charge");
//            body.put("amount","1000.55");
//            body.put("createTime","2015-11-19 12:00:00");
//            body.put("cardInfoId","167");
//            body.put("password", "123456");
              //"amount":"1","password":"670b14728ad9902aecba32e22fa4f6bd","startTime":"2015-11-20 21:18:34","cardInfoId":166}
            // 查询用户钱包流水
              //header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.queryUserWalletLog");
//            body.put(BusiKey.DATA_KEY_BODY, "{\"pageSize\":10,\"currentPage\":1}");
//            busiData.setData(body);

            // 查询用户钱包
          // header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.userGetMoneyApply");

             // 提现
//            header.put(BusiKey.HEADER_KEY_QUERY, "TEST.Messenger.userGetMoneyApply");
//
//            body.put("amount","0.5");
//            body.put("startTime","2015-11-22 12:00:00");
//            body.put("cardInfoId","171");
//            body.put("chargeNo","1");
//            body.put("chargeWay","1");
//            body.put("channelId","1");
//            body.put("password","123456");


//            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_ADDRESS_BOOK);
//            header.put(BusiKey.HEADER_KEY_TOKEN, "0d3c6378-4f38-45cf-8842-c40b41acfd52");
//
//            busiData.setHeader(header);
//            busiData.setData(body);
//            ctx.channel().writeAndFlush(busiData);

            login(ctx.channel());
            //chat(ctx.channel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login(Channel channel) {
        //登录
                    TransData busiData = new TransData();
                    Map<String, String> header = new HashMap<>();
                    Map<String, String> body = new HashMap<>();

                    header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_LOGIN);
                    body.put(LoginKey.BODY_KEY_EA, "ftetest");
                    body.put(LoginKey.BODY_KEY_USERNAME, "18811110008");
                    body.put(LoginKey.BODY_KEY_PASSWORD, "123456");
                    busiData.setData(body);
                    busiData.setHeader(header);
                    channel.writeAndFlush(busiData);
    }
    private void chat(Channel channel) {

        TransData busiData = new TransData();
        Map<String, String> header = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_CHAT);
        body.put("messageID", UUID.randomUUID().toString());
        body.put("sessionID", 12345+"");
        body.put("senderID", 1234 + "");
        body.put("messageContent", "test");

        body.put("messageType", "text");
        body.put("messageTime", "2015-12-01 12:00:00");
        busiData.setData(body);
        channel.writeAndFlush(busiData);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        TransData req = (TransData) msg;
        MsgHandler msgHandler = MsgClientHandlerFactory.getClientHandler(req);

        if (msgHandler != null) {
            msgHandler.process(ctx, req);
        } else {
            logger.error("没找到handler, header:" + req.getHeader());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public static void main(String args[]) {
        TransData loginReq = new TransData();
        Map<String, String> header = new HashMap<>();
        header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_LOGIN);
        Map<String, String> body = new HashMap<>();
        body.put(LoginKey.BODY_KEY_EA, "fs");
        body.put(LoginKey.BODY_KEY_USERNAME, "13266637621");
        body.put(LoginKey.BODY_KEY_PASSWORD, "123456");
        loginReq.setData(body);
        loginReq.setHeader(header);
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginReq));
    }

}
