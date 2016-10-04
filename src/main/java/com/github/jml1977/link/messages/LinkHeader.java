package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;

public class LinkHeader {

	public LinkHeader(ByteBuf bb) throws InvalidLinkMessageException {
		mt = LinkMessageType.fromValue(bb.readByte());
		ttl = bb.readByte();
		sessionGroupId = new LinkSessionGroupId(bb.readByte(), bb.readByte());

		byte[] nodeId = new byte[8];
		bb.readBytes(nodeId);
		ident = new LinkNodeId(nodeId);
	}

	private final LinkMessageType mt;
	private final int ttl;
	private final LinkSessionGroupId sessionGroupId;
	private final LinkNodeId ident;

	public LinkMessageType getMessageType() {
		return mt;
	}

	public LinkNodeId ident() {
		return this.ident;
	}
	
	public int ttl() {
		return this.ttl;
	}

	@Override
	public String toString() {
		return "LinkHeader: " + mt.toString() + ", ttl=" + Integer.toString(ttl) + ", " + sessionGroupId.toString() + ", "
				+ ident.toString();
	}

	public LinkHeader(LinkMessageType mt, int ttl, LinkSessionGroupId sessionGroupId, LinkNodeId ident) {
		this.mt = mt;
		this.ttl = ttl;
		this.sessionGroupId = sessionGroupId;
		this.ident = ident;
	}

	public byte[] toNetworkBytes() {
		ByteBuffer bb = ByteBuffer.allocate(20);
		assert (bb.hasArray());

		bb.put(new byte[] { '_', 'a', 's', 'd', 'p', '_', 'v' });
		bb.put((byte) 1);

		bb.put((byte) mt.getId());
		bb.put((byte) ttl);
		bb.put(sessionGroupId.toNetworkBytes());
		bb.put(ident.toNetworkBytes());

		return bb.array();
	}
}
