package com.facishare.wechat.access.client.handler.impl;

import com.facishare.wechat.access.Constants.CommandType;
import com.facishare.wechat.access.client.handler.MsgHandler;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.utils.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * heart beat handler
 * Created by lif on 2015/10/10.
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter implements MsgHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                // Read timeout
                System.out.println("READER_IDLE: read timeout from " + ctx.channel().remoteAddress());

                String userId = CommonUtil.getLoginId(ctx.channel());
                ConnectionManager.removeConn(userId);

                // close connection
                if (ctx.channel().isOpen()) {
                    ctx.channel().close();
                }

            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public static void startSendHeartBeatTask(ChannelHandlerContext ctx) {
        TransData data = new TransData();
        Map<String, String> head = new HashMap<String, String>();
        head.put("command", CommandType.COMMAND_HEARTBEAT);
        data.setHeader(head);
        ctx.executor().scheduleAtFixedRate(() -> {
            ctx.channel().writeAndFlush(data);
        }, 0, 20000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

    }
}
