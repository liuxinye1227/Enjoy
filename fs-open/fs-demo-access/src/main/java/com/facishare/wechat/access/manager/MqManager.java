package com.facishare.wechat.access.manager;

import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.api.access.model.WxMsg;


/**
 * Created by lif on 2015/10/10.
 */
public class MqManager {

    /**
     * send msg
     */
    public static void sendMsg(WxMsg.ChatMessage chatMsg){
        MsgServer.getIntance().getRabbitTemplate().convertAndSend("OPEN.PROVIDER.WXMSG", chatMsg);
    }
}
