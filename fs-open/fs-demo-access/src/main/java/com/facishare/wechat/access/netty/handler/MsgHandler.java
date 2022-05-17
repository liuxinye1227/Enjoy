package com.facishare.wechat.access.netty.handler;

import com.facishare.wechat.access.model.TransData;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理器
 * Created by lif on 2015/9/30.
 * 
 */
public interface MsgHandler {

	/**
	 * 处理方法
	 * 
	 * @param ctx
	 * @param msg
	 */
	public void process(ChannelHandlerContext ctx, TransData msg);

}