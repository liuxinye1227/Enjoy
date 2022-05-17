package com.facishare.wechat.access.manager;

import com.facishare.wechat.access.model.TransData;
import io.netty.channel.Channel;

/**
 * Created by lif on 2015/9/30.
 */
public class RouterManager {

    public static void routeLoginSuccess(Channel channel, TransData message) {
        channel.writeAndFlush(message);
    }

    public static void routeLoginFail(Channel channel, TransData message) {
        channel.writeAndFlush(message);
    }

    public static void routeSuccess(Channel channel, TransData message) {
        channel.writeAndFlush(message);
    }
}
