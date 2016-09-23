package com.github.jml1977.link.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;

public class LinkPacketReader {
	private static final Logger logger = LoggerFactory.getLogger(LinkPacketReader.class);

	public LinkPacketReader(ByteBuf bb) throws InvalidLinkMessageException {
		bb.readerIndex(0);
		final byte[] protocol = new byte[7];
		bb.readBytes(protocol);

		String s = new String(protocol);
		if (!("_asdp_v".equals(s)))
			throw new InvalidLinkMessageException("Invalid Ableton Link protocol declaration");
		byte b7 = bb.readByte(); // offset 7
		if (b7 != 1)
			throw new InvalidLinkMessageException("Invalid Ableton Link protocol version " + Byte.toString(b7));

		LinkHeader lh = new LinkHeader(bb);
		switch (lh.getMessageType()) {
		case ALIVE:
			handleAlive(lh, bb);
			break;
		case RESPONSE:
			handleResponse(lh, bb);
			break;
		case BYEBYE:
			handleByeBye(lh, bb);
			break;
		case INVALID:
			break;
		default:
			logger.warn("Unknown message type: {}", lh.getMessageType());
		}
	}

	private void handleAlive(LinkHeader lh, ByteBuf bb) {
		logger.info("handleAlive");
		LinkPeerState lps = new LinkPeerState(lh, bb);
		logger.info(lps.toString());
	}

	private void handleByeBye(LinkHeader lh, ByteBuf bb) {
		logger.info("handleByeBye");
		LinkByeBye lbb = new LinkByeBye(lh, bb);
		logger.info(lbb.toString());
	}

	private void handleResponse(LinkHeader lh, ByteBuf bb) {
		// TODO
	}
}
