package com.web.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
			throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println("req:" + req);

		ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(req, CharsetUtil.UTF_8), packet.sender()));
	}
}
