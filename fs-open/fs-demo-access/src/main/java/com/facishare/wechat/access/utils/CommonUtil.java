package com.facishare.wechat.access.utils;

import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.api.access.model.WxMsg;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * Created by lif on 2015/9/30.
 */
public class CommonUtil {
    /**
     * 从channel获取登录数据
     *
     * @param channel
     * @return
     */
    public static String getLoginId(Channel channel) {
        return (String) channel.attr(AttributeKey.valueOf(CommonConstant.ID_KEY)).get();
    }
    public static String getLoginEa(Channel channel) {
        return (String) channel.attr(AttributeKey.valueOf(CommonConstant.EA_KEY)).get();
    }

    /**
     * 设置登录数据 to channel
     *
     * @param channel
     * @return
     */
    public static void setLoginInfo(Channel channel, String useId, String ea, String userName, String token) {
        channel.attr(AttributeKey.valueOf(CommonConstant.ID_KEY)).set(useId);
        channel.attr(AttributeKey.valueOf(CommonConstant.EA_KEY)).set(ea);
        channel.attr(AttributeKey.valueOf(CommonConstant.TOKEN_KEY)).set(token);
        channel.attr(AttributeKey.valueOf(CommonConstant.NAME_KEY)).set(userName);
    }

    /**
     * create userId
     *
     * @param ea
     * @param userId
     * @return
     */
    public static String generateUserId(String ea, int userId) {
        return "E." + ea.trim() + "." + userId;
    }

    /**
     * create WxMsg.Builder
     *
     * @return
     */
    public static WxMsg.Message.Builder createWxMsgBuilder() {
        WxMsg.Message.Builder msgBuilder =  WxMsg.Message.newBuilder();
        msgBuilder.setPid(IDUtil.nextId());
        return msgBuilder;
    }


}
