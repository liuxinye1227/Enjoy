package com.facishare.wechat.access.netty.codec;

import com.facishare.wechat.access.model.TransData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 解码器
 * Created by lif on 2015/9/30.
 */
public class JsonDecoder extends ByteToMessageDecoder {
	/**
	 * 日志
	 */
	private final Logger Log = LoggerFactory.getLogger(getClass());

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// 标记一下当前的readIndex的位置
		in.markReaderIndex();
		// 判断包头长度
		if (in.readableBytes() < 4) {// 不够包头
			return;
		}

		// 读取传送过来的消息的长度。
		int length = in.readInt();

		// 长度如果小于0
		if (length < 0) {// 非法数据，关闭连接
			ctx.close();
		}

		if (length > in.readableBytes()) {// 读到的消息体长度如果小于传送过来的消息长度
			// 重置读取位置
			in.resetReaderIndex();
			return;
		}

		ByteBuf frame = Unpooled.buffer(length);
		in.readBytes(frame);

		try {
			byte[] inByte = frame.array();


			// 字节转成对象
			TransData msg = TransData.fromJson(new String(inByte, "UTF-8"));
			Log.info("[SERVER][RECV][remoteAddress:"
					+ ctx.channel().remoteAddress() + "][total length:"
					+ length + "][bare length:" + inByte.toString().length()
					+ "]:\r\n" + msg.toString());

			if (msg != null) {
				// 获取业务消息头
				out.add(msg);
			}
		} catch (Exception e) {
			Log.info(ctx.channel().remoteAddress() + ",decode failed.", e);
		}

	}
}
