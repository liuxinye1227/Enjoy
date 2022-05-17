package com.facishare.wechat.access.netty;


import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import com.facishare.wechat.access.netty.handler.impl.MsgHandlerFactory;
import com.facishare.wechat.access.utils.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lif on 2015/9/28.
 */
public class MsgServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        String userId = CommonUtil.getLoginId(ctx.channel());
        ConnectionManager.removeConn(userId);

        // 关闭连接
        if (ctx.channel().isOpen()) {
            ctx.channel().close();
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        TransData req = (TransData) msg;
        MsgHandler msgHandler = MsgHandlerFactory.getAppMsgHandler(req);

        if (msgHandler != null) {
            msgHandler.process(ctx, req);
        } else {
            Log.error("没找到handler, header:" + req.getHeader());
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
