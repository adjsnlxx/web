package com.web.rudp;

import io.jpower.kcp.netty.UkcpChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class KCPServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("remote address active : " + ctx.channel().remoteAddress().toString());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("remote address inactive : " + ctx.channel().remoteAddress().toString());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		UkcpChannel kcpCh = (UkcpChannel) ctx.channel();
		System.out.println("conv = " + kcpCh.conv());

		if (msg instanceof ByteBuf) {
			ByteBuf buf = (ByteBuf) msg;
			byte[] data = new byte[buf.readableBytes()];
			buf.readBytes(data);

			System.out.println("req : " + new String(data).toString());

			ByteBuf message = Unpooled.buffer(5);
			message.writeBytes("ok".getBytes());
			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
