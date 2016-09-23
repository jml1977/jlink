package com.github.jml1977.link.linkhut;

import java.net.InetSocketAddress;

import com.github.jml1977.link.messages.LinkPacketReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.NetUtil;

public class StateRunner extends Thread {

	public StateRunner(com.github.jml1977.link.linkhut.State state) {
	}

	@Override
	public void run() {
		try {
			EventLoopGroup grp = new NioEventLoopGroup();
			Bootstrap b = new Bootstrap();
			b.group(grp);
			b.channel(NioDatagramChannel.class);

			b.handler(new SimpleChannelInboundHandler<DatagramPacket>() {

				@Override
				protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
					ByteBuf bb = msg.content();
					LinkPacketReader lpr = new LinkPacketReader(bb);
				}

			});
			b.option(ChannelOption.IP_MULTICAST_IF, NetUtil.LOOPBACK_IF);
			b.option(ChannelOption.SO_REUSEADDR, true);
			b.localAddress(20808);

			String group = "224.76.78.75";
			InetSocketAddress groupAddress = new InetSocketAddress(group, 20808);

			NioDatagramChannel c = (NioDatagramChannel) b.bind().sync().channel();
			System.out.println(c);
			c.joinGroup(groupAddress, NetUtil.LOOPBACK_IF).sync();
			for (;;) {
				Thread.sleep(100000);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
