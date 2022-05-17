package com.facishare.wechat.access.netty.handler.impl;

import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.LoginKey;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录消息处理器
 * Created by lif on 2015/9/30.
 */
public class LogoutHandler implements MsgHandler {


    private final Logger Log = LoggerFactory.getLogger(getClass());



    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {
        TransData result = new TransData();
        Map<String, String> data = msg.getData();

        result.setHeader(msg.getHeader());


        try {
            String token = msg.getHeader().get(BusiKey.HEADER_KEY_TOKEN);

            String ea = data.get(LoginKey.BODY_KEY_EA); // 企业号
            String userName = data.get(LoginKey.BODY_KEY_USERNAME);// 手机号
            String password = data.get(LoginKey.BODY_KEY_PASSWORD);// 密码

            result.setHeader(msg.getHeader());
            RedisTemplate redisTemplate =  MsgServer.getIntance().getRedisTemplate();
            String userId = redisTemplate.get(token, "");
            if (StringUtils.isBlank(userId)) {
                RouterManager.routeLoginSuccess(ctx.channel(), result);
            }
            String[] users = userId.split("\\.");

            // 判断是否有旧链接
            Channel oldChannel = ConnectionManager.getConn(users[2] + "");
            if (oldChannel != null) {
                ConnectionManager.removeConn(users[2]  + "");
            }
            redisTemplate.remove(token);
            redisTemplate.remove(userId);

            Map<String, String> resultMap = new HashMap<String, String>();

            resultMap.put("errorCode", CommonConstant.LOGIN_PASSWORD_SUCCESS);
            resultMap.put("errorMessage", "登出成功");
            result.setData(resultMap);
            // 返回登录信息
            RouterManager.routeLoginSuccess(ctx.channel(), result);

        } catch (Exception e) {
            Log.error("login error:" + e.getMessage(), e);
            RouterManager.routeLoginSuccess(ctx.channel(), result);


        }
    }


}
