package com.web.rudp;

import io.jpower.kcp.netty.UkcpChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo client.
 *
 * @author <a href="mailto:szhnet@gmail.com">szh</a>
 */
public class KCPClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		String msg = "sam";

		ByteBuf buf = Unpooled.buffer(3);
		buf.writeBytes(msg.getBytes());

		ctx.writeAndFlush(buf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ctx.write(msg);
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