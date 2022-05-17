package com.facishare.wechat.access.netty.handler;

import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.utils.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * heart beat command
 * Created by lif on 2015/10/10.
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter implements MsgHandler  {

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
    public void process(ChannelHandlerContext ctx, TransData msg) {
        ctx.channel().writeAndFlush(msg);
    }
}
