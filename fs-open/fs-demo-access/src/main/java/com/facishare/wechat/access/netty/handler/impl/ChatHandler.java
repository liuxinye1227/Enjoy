package com.facishare.wechat.access.netty.handler.impl;

import com.facishare.fcp.FcpCodec;
import com.facishare.fcp.client.FcpClient;
import com.facishare.fcp.protocol.*;
import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.MessageTypeConstants;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangtao on 2015/11/18.
 */
public class ChatHandler implements MsgHandler {
    private static Logger logger = LoggerFactory.getLogger(ChatHandler.class);


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

        try {
            // 获取查询 msg
            String token = msg.getHeader().get(BusiKey.HEADER_KEY_TOKEN);

            //String queryName = msg.getHeader().get(BusiKey.HEADER_KEY_QUERY);

            //String body = new Gson().toJson(msg.getData());

            // 获取接收消息的 channel ，发送消息
            Map<String, String> msgMap = msg.getData();
            String toUserId = msgMap.get("toUserId");
            long fromUserId = Long.valueOf(msgMap.get("fromUserId"));


            Channel channel = ConnectionManager.getConn(toUserId);

            TransData transData = new TransData();
            Map<String, String> header = new HashMap<>();
            String msgType = msg.getData().get("messageType");
            Map<String, String> body = new HashMap<>();


            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("errorCode", CommonConstant.SUCCESS);
            transData.setData(resultMap);
            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_CHAT);
            header.put(CommonConstant.CHAT_TYPE, CommonConstant.CHAT_TYPE_SEND);
            transData.setHeader(header);

            // 返回发送正确的结果
            RouterManager.routeSuccess(ctx.channel(), transData);

            if (channel != null) {
                logger.debug("接收消息的channel:[{}]", channel);
                body.put("messageID", UUID.randomUUID().toString());
                body.put("toUserId", toUserId + "");
                body.put("fromUserId", fromUserId + "");
                body.put("messageContent", msg.getData().get("messageContent"));

                body.put("messageType", msgType);
                body.put("messageTime", msg.getData().get("messageTime"));
                body.put("thumbImagePath", "");
                body.put("originalImagePath", "");
                header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_CHAT);
                header.put(CommonConstant.CHAT_TYPE,CommonConstant.CHAT_TYPE_REV);
                transData.setData(body);
                RouterManager.routeSuccess(channel, transData);
            } else {
                logger.debug("user:[{}] is offline", toUserId);
            }



        } catch (Exception e) {
            TransData transData = new TransData();
            Map<String, String> header = new HashMap<>();

            header.put(CommonConstant.CHAT_TYPE, CommonConstant.CHAT_TYPE_SEND);
            transData.setHeader(header);
            logger.error("error:{}", e);
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.CHAT_ERROR);
            transData.setData(resultMap);

            RouterManager.routeSuccess(ctx.channel(), transData);

        }
    }

    public static void main(String[] args) {
        TransData transData = new TransData();
        Map<String, String> body = new HashMap<>();
        body.put("messageID", UUID.randomUUID().toString());
        body.put("toUserId", 12345 + "");
        body.put("fromUserId", 125 + "");
        body.put("messageContent", "11111111111");

        body.put("messageType", "TEXT");
        body.put("messageTime", "2015-11-26 11:00:00");
        transData.setData(body);

        System.out.println(new Gson().toJson(transData));
    }
}
