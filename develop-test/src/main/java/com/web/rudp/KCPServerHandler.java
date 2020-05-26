package com.web.rudp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class KCPServerHandler extends SimpleChannelInboundHandler {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof ByteBuf) {
			ByteBuf buf = (ByteBuf) msg;
			byte[] data = new byte[buf.readableBytes()];
			buf.readBytes(data);

			System.out.println(new String(data).toString());
		}
	}
}
