package com.facishare.wechat.access.api;

/**
 * Created by dingc on 2015/11/18.
 */
public interface SendMessageService {

    boolean sendTextMessage(Long userId, String content);

    boolean sendMessage(String enterpriseAccount, long fromUserId, long toUserId, String messageContent,
        String messageType, String messageTime);


}
