package com.facishare.wechat.access.service.impl;

import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.MessageKey;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.api.SendMessageService;
import io.netty.channel.Channel;
import org.apache.commons.lang.time.DateUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dingc on 2015/11/18.
 */
public class SendMessageServiceImpl implements SendMessageService {

    public boolean sendTextMessage(Long userId, String content) {
        try {
            Channel channel = ConnectionManager.getConn(userId + "");
            if (channel != null) {
                TransData data = new TransData();
                Map<String, String> header = new HashMap<>();
                Map<String, String> body = new HashMap<>();
                body.put(MessageKey.BODY_KEY_CONTENT, content);
                header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_SEND_MESSAGE);
                data.setData(body);
                data.setHeader(header);
                RouterManager.routeLoginSuccess(channel, data);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendMessage(String enterpriseAccount, long fromUserId, long toUserId, String messageContent,
        String messageType, String messageTime) {

        // messageID;
        // sessionID;
        //senderID;
        //messageContent;
       //messageTime;
       //thumbImagePath;
       //messageType;
       //originalImagePath;
       //fileObjectID;
        Channel channel = ConnectionManager.getConn(toUserId + "");
        if (channel != null) {
            TransData data = new TransData();
            Map<String, String> header = new HashMap<>();
            Map<String, String> body = new HashMap<>();
            body.put("messageID", UUID.randomUUID().toString());
            body.put("sessionID", toUserId + "");
            body.put("senderID", fromUserId + "");
            body.put("messageContent", messageContent);
            body.put("messageType", messageType);
            body.put("messageTime", messageTime);
            header.put(CommonConstant.HEADER_KEY_COMMAND, CommandType.COMMAND_SEND_MESSAGE);
            data.setData(body);
            data.setHeader(header);
            RouterManager.routeLoginSuccess(channel, data);
            return true;
        }

        return false;
    }
}
