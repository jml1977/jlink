package com.github.jml1977.link.linkhut;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class JLMulticastTest {
	@SuppressWarnings("all") // shut-up Sonar about hard-coded IP
	private final static String LINK_MULTICAST_GROUP = "224.76.78.75";

	private static final Logger logger = LoggerFactory.getLogger(JLMulticastTest.class);

	public static void main(String[] args) throws Exception {
		EventLoopGroup grp = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(grp);
		b.channel(NioDatagramChannel.class);

		b.handler(new SimpleChannelInboundHandler<DatagramPacket>() {

			@Override
			protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
				ByteBuf bb = msg.content();
				LinkPacketReader lpr = new LinkPacketReader(bb);
				Object response = lpr.read();
				logger.debug(response.toString());
			}

		});
		b.option(ChannelOption.IP_MULTICAST_IF, NetUtil.LOOPBACK_IF);
		b.option(ChannelOption.SO_REUSEADDR, true);
		b.localAddress(20808);

		InetSocketAddress groupAddress = new InetSocketAddress(LINK_MULTICAST_GROUP, 20808);

		NioDatagramChannel c = (NioDatagramChannel) b.bind().sync().channel();
		logger.info(c.toString());
		c.joinGroup(groupAddress, NetUtil.LOOPBACK_IF).sync();
	}

	private JLMulticastTest() {
	}
}
