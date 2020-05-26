package com.web.rudp;

import io.jpower.kcp.netty.ChannelOptionHelper;
import io.jpower.kcp.netty.UkcpChannel;
import io.jpower.kcp.netty.UkcpChannelOption;
import io.jpower.kcp.netty.UkcpServerChannel;
import io.netty.bootstrap.UkcpServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class KCPServer {

	static final int CONV = Integer.parseInt(System.getProperty("conv", "10"));
	static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

	public static void main(String[] args) throws Exception {
		// Configure the server.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			UkcpServerBootstrap b = new UkcpServerBootstrap();
			b.group(group).channel(UkcpServerChannel.class).childHandler(new ChannelInitializer<UkcpChannel>() {
				@Override
				public void initChannel(UkcpChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new KCPServerHandler());
				}
			});

			ChannelOptionHelper.nodelay(b, true, 20, 2, true);
			b.childOption(UkcpChannelOption.UKCP_MTU, 512);
			b.childOption(UkcpChannelOption.UKCP_AUTO_SET_CONV, true);

			// Start the server.
			ChannelFuture f = b.bind(PORT).sync();

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down all event loops to terminate all threads.
			group.shutdownGracefully();
		}
	}

}
