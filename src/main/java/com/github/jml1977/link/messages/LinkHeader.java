package com.github.jml1977.link.messages;

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

	@Override
	public String toString() {
		return "LinkHeader: " + mt.toString() + ", ttl=" + Integer.toString(ttl) + ", " + sessionGroupId.toString()
				+ ", " + ident.toString();
	}
}
