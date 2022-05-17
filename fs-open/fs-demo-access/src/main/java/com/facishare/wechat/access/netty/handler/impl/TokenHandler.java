package com.facishare.wechat.access.netty.handler.impl;

import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 2015/11/20.
 */
public class TokenHandler implements MsgHandler {


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

        String token = msg.getHeader().get(BusiKey.HEADER_KEY_TOKEN);
        String userId = MsgServer.getIntance().getRedisTemplate().get(token, "");
        TransData transData = new TransData();
        Map<String, String> dataMap = new HashMap<>();

        if (StringUtils.isBlank(userId)) {

            transData.setHeader(msg.getHeader());
            dataMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.TOKEN_TIMEOUT);

        } else {
            transData.setHeader(msg.getHeader());
            dataMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.SUCCESS);

        }
        transData.setData(dataMap);

        RouterManager.routeSuccess(ctx.channel(), transData);


    }
}
