package com.web.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UDPServer {

	public void run(int port) throws InterruptedException {
		EventLoopGroup work = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(work);
		bootstrap.channel(NioDatagramChannel.class);
		bootstrap.option(ChannelOption.SO_BROADCAST, true);
		bootstrap.handler(new UDPHandler());
		bootstrap.bind(port).sync().channel().closeFuture().await();
	}

	public static void main(String[] args) {
		try {
			new UDPServer().run(8080);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
