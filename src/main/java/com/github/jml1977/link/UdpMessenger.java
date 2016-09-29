package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkMessageType;
import com.github.jml1977.link.messages.LinkNodeId;

public class UdpMessenger {
	private static class HostPortPair {
		public HostPortPair(String host, int port) {
			this.host = host;
			this.port = port;
		}

		private final String host;
		private final int port;
	}

	private static class Impl {
		public Impl(Object iface, NodeState state, Object io, byte ttl, byte ttlRatio) {
			this.iface = iface;
			this.state = state;
			this.io = io;
			this.ttl = ttl;
			this.ttlRatio = ttlRatio;
		}

		private final Object iface;
		private final NodeState state;
		private final Object io;
		private final byte ttl;
		private final byte ttlRatio;

		public void sendByeBye() {
			sendUdpMessage(iface, state.ident(), (byte) 0, (byte) LinkMessageType.BYEBYE.getId(), makePayload(), multicastEndpoint());
		}

		private Object makePayload() {
			return new Object();
		}

		private void sendUdpMessage(Object iface, LinkNodeId nodeId, byte ttl, byte messageType, Object payload, HostPortPair to) {

		}

		public HostPortPair multicastEndpoint() {
			return new HostPortPair("224.76.78.75", 20808);
		}
	}

	private final Impl impl;

	public UdpMessenger(Object iface, NodeState state, Object io, byte ttl, byte ttlRatio) {
		impl = new Impl(iface, state, io, ttl, ttlRatio);

	}

	public void destroy() {
		sendByeBye();
	}

	private void sendByeBye() {
		impl.sendByeBye();
	}

}
