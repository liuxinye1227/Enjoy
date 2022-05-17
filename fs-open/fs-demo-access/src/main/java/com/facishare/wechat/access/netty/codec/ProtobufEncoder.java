package com.facishare.wechat.access.netty.codec;


import com.facishare.wechat.api.access.model.WxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码器
 * Created by lif on 2015/9/30.
 */
public class ProtobufEncoder extends MessageToByteEncoder<WxMsg.Message> {
	/**
	 * 日志对象
	 */
	private final Logger Log = LoggerFactory.getLogger(getClass());

	@Override
	protected void encode(ChannelHandlerContext ctx, WxMsg.Message msg, ByteBuf out)
			throws Exception {

		byte[] bytes = msg.toByteArray();// 将对象转换为byte

		int length = bytes.length;// 读取消息的长度

		ByteBuf buf = Unpooled.buffer(4 + length);
		buf.writeInt(length);// 先将消息长度写入，也就是消息头
		buf.writeBytes(bytes);// 消息体中包含我们要发送的数据
		out.writeBytes(buf);

		Log.info("[SERVER][SEND][remoteAddress:"
				+ ctx.channel().remoteAddress() + "][total length:" + length
				+ "][bare length:" + msg.getSerializedSize() + "]:\r\n"
				+ msg.toString());

	}

}
