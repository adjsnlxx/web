package com.web.rudp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class KCPEncoder extends MessageToMessageEncoder<KCPMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, KCPMessage msg, List<Object> out) throws Exception {
		String temp = msg.getMsg();

		ByteBuf byteBuf = ctx.alloc().buffer(temp.length());
		byteBuf.writeBytes(temp.getBytes());
		out.add(byteBuf);
	}
}
