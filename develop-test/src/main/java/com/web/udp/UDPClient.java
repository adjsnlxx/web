package com.web.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class UDPClient {

	public void run(String ip, int port) {
		EventLoopGroup work = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(work);
		bootstrap.channel(NioDatagramChannel.class);
		bootstrap.option(ChannelOption.SO_BROADCAST, true);
		bootstrap.handler(new UDPClientHandler());

		Channel channel = null;
		try {
			// UDP本地监听端口（如果为0将表示由系统分配，否则使用指定端口）
			channel = bootstrap.bind(0).sync().channel();

			InetSocketAddress rAddress = new InetSocketAddress(ip, port);

			DatagramPacket packet = new DatagramPacket(Unpooled.copiedBuffer("hello udp", CharsetUtil.UTF_8), rAddress);

			channel.writeAndFlush(packet);
		} catch (InterruptedException e) {
			work.shutdownGracefully();
		}

	}

	public static void main(String[] args) {
		new UDPClient().run("127.0.0.1", 8080);
	}
}
