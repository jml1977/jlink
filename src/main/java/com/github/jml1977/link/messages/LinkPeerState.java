package com.github.jml1977.link.messages;

import io.netty.buffer.ByteBuf;

public class LinkPeerState {
	public LinkPeerState(final LinkHeader lh, ByteBuf bb) {
		this.lh = lh;

		byte[] tmlnKey = new byte[4];
		bb.readBytes(tmlnKey); // tmln
		int tmlnSize = bb.readInt(); // 4 bytes
		byte[] tmlnValue = new byte[tmlnSize];
		bb.readBytes(tmlnValue);

		byte[] sessKey = new byte[4];
		bb.readBytes(sessKey);
		int sessSize = bb.readInt(); // 4 bytes
		byte[] sessValue = new byte[sessSize];
		bb.readBytes(sessValue);

		byte[] mep4Key = new byte[4];
		bb.readBytes(mep4Key);
		int mep4Size = bb.readInt(); // 4 bytes
		byte[] mep4Value = new byte[mep4Size];
		bb.readBytes(mep4Value);

		ltl = new LinkTimeline(tmlnValue);
		ls = new LinkSession(sessValue);
		lmev4 = new LinkMeasurementEndpointV4(mep4Value);
	}
	
	public int ttl() {
		return lh.ttl();
	}

	public LinkNodeId ident() {
		return lh.ident();
	}

	private final LinkHeader lh;
	private final LinkTimeline ltl;
	private final LinkSession ls;
	private final LinkMeasurementEndpointV4 lmev4;

	@Override
	public String toString() {
		return lh.toString() + "\n LinkPeerState:\n  " + ltl.toString() + "\n  " + ls.toString() + "\n  " + lmev4.toString();
	}
}
