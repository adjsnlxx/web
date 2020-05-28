package com.web.rudp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class KCPDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] data = new byte[in.readableBytes()];
		in.readBytes(data);

		KCPMessage message = new KCPMessage();
		message.setMsg(new String(data));
		out.add(message);
	}
}
