package com.github.jml1977.link.linkhut;

import java.net.InetSocketAddress;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.jml1977.link.Clock;
import com.github.jml1977.link.messages.LinkByeBye;
import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkPacketReader;
import com.github.jml1977.link.messages.LinkPeerState;
import com.github.jml1977.link.utils.Tuple;

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
		peerTimeouts = new PeerTimeouts();
		timer = new ScheduledThreadPoolExecutor(1);
	}

	private ScheduledThreadPoolExecutor timer;

	private void handle(Object obj) {
		if (obj instanceof LinkPeerState) {
			onPeerState((LinkPeerState) obj);
		} else if (obj instanceof LinkByeBye) {
			onByeBye(((LinkByeBye) obj).ident());
		}
	}

	private void onPeerState(LinkPeerState peerState) {
		LinkNodeId peerId = peerState.ident();
		PeerTimeout peer = findPeer(peerId);
		if (peer != null) { // existing
			peerTimeouts.removeIf(p -> p.second().equals(peerId));
		}
		PeerTimeout pt = new PeerTimeout(Clock.now() + (peerState.ttl() * 1000000), peerId);
		peerTimeouts.add(pt);
		sawPeer(peerState);
		scheduleNextPruning();
	}

	private void scheduleNextPruning() {
		if (!peerTimeouts.isEmpty()) {
			long schedulingTime = peerTimeouts.peek().first() + 1000000 - Clock.now();
			timer.schedule(() -> pruneExpiredPeers(), schedulingTime, TimeUnit.MILLISECONDS);
		}
	}

	private void pruneExpiredPeers() {
		System.out.println("pruneExpiredPeers");
	}

	private void sawPeer(LinkPeerState state) {
	}

	private static class PeerTimeout extends Tuple<Long, LinkNodeId> {
		public PeerTimeout(Long k, LinkNodeId v) {
			super(k, v);
		}
	}

	private static class PeerTimeouts extends PriorityQueue<PeerTimeout> {
		public PeerTimeouts() {
			super(new TimeoutComparator());
		}

		private static class TimeoutComparator implements Comparator<PeerTimeout> {

			@Override
			public int compare(PeerTimeout o1, PeerTimeout o2) {
				return o1.first().compareTo(o2.first());
			}

		}
	}

	private PeerTimeouts peerTimeouts;

	public PeerTimeout findPeer(final LinkNodeId nodeId) {
		for (PeerTimeout p : peerTimeouts) {
			if (p.second().equals(nodeId)) {
				return p;
			}
		}
		return null;
	}

	private void onByeBye(LinkNodeId ident) {
		PeerTimeout t = findPeer(ident);
		if (t != null) {
			peerLeft(t.second());
			peerTimeouts.removeIf(p -> p.second().equals(ident));
		}
	}

	private void peerLeft(LinkNodeId ident) {
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
					handle(lpr.read());
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
