package com.github.jml1977.link.net;

import io.netty.buffer.ByteBuf;

public class LinkPacketReader {

	public LinkPacketReader(ByteBuf bb) {
		bb.readerIndex(0);
		final byte[] protocol = new byte[7];
		bb.readBytes(protocol);

		String s = new String(protocol);
		if (!(s.equals("_asdp_v")))
			throw new RuntimeException("Invalid Ableton Link protocol declaration");
		byte b7 = bb.readByte(); // offset 7
		if (b7 != 1)
			throw new RuntimeException("Invalid Ableton Link protocol version " + Byte.toString(b7));

		LinkHeader lh = new LinkHeader(bb);
		switch (lh.getMessageType()) {
		case ALIVE:
			handleAlive(lh, bb);
			break;
		case RESPONSE:
			break;
		case BYEBYE:
			handleByeBye(lh, bb);
			break;
		case INVALID:
			break;
		}
	}

	private void handleAlive(LinkHeader lh, ByteBuf bb) {
		System.out.println("handleAlive");
		LinkPeerState lps = new LinkPeerState(lh, bb);
		System.out.println(lps);
	}

	private void handleByeBye(LinkHeader lh, ByteBuf bb) {
		System.out.println("handleByeBye");
		LinkByeBye lbb = new LinkByeBye(lh, bb);
		System.out.println(lbb);
	}
}
