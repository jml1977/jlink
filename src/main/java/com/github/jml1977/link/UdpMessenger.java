package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkByeBye;
import com.github.jml1977.link.messages.LinkHeader;
import com.github.jml1977.link.messages.LinkMessageType;
import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkSessionGroupId;

public class UdpMessenger {
	private static class HostPortPair {
		private final String host;

		private final int port;

		public HostPortPair(String host, int port) {
			this.host = host;
			this.port = port;
		}
	}

	private static class Impl {
		private final Object iface;
		private final Object io;
		private NodeState state;
		private final byte ttl;
		private final byte ttlRatio;

		public Impl(Object iface, NodeState state, Object io, byte ttl, byte ttlRatio) {
			this.iface = iface;
			this.state = state;
			this.io = io;
			this.ttl = ttl;
			this.ttlRatio = ttlRatio;
		}

		private Object makePayload(Object object) {
			return new PayloadBuilder(object).build();
		}

		public HostPortPair multicastEndpoint() {
			return new HostPortPair("224.76.78.75", 20808);
		}

		public void sendByeBye() {
			LinkHeader lh = new LinkHeader(LinkMessageType.BYEBYE, ttl, new LinkSessionGroupId(0), state.ident());
			LinkByeBye lbb = new LinkByeBye(lh);
			sendUdpMessage(iface, state.ident(), makePayload(lbb), multicastEndpoint());
		}

		private void sendUdpMessage(Object iface, LinkNodeId nodeId, Object payload, HostPortPair to) {
		}

		public void updateState(NodeState state) {
			this.state = state;
		}
	}

	private final Impl impl;

	public UdpMessenger(Object iface, NodeState state, Object io, byte ttl, byte ttlRatio) {
		impl = new Impl(iface, state, io, ttl, ttlRatio);
	}

	public void updateState(NodeState state) {
		impl.updateState(state);
	}

	public void destroy() {
		sendByeBye();
	}

	private void sendByeBye() {
		impl.sendByeBye();
	}

	private static class PayloadBuilder {
		public PayloadBuilder(Object object) {
			this.object = object;
		}

		private final Object object;

		public Object build() {
			if (object instanceof LinkByeBye) {
				LinkByeBye lbb = (LinkByeBye) object;
				byte[] resp = lbb.toNetworkBytes();
			}
			return null;
		}
	}
}
