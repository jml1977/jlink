package com.github.jml1977.link.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;

public class LinkPacketReader {
	private static final Logger logger = LoggerFactory.getLogger(LinkPacketReader.class);

	public Object read() throws InvalidLinkMessageException {
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
			return handleAlive(lh, bb);
		case RESPONSE:
			return handleResponse(lh, bb);
		case BYEBYE:
			return handleByeBye(lh, bb);
		case INVALID:
			logger.warn("Invalid reponse message type: {}", lh.getMessageType());
			return null;
		default:
			logger.warn("Unknown message type: {}", lh.getMessageType());
			return null;
		}
	}

	private ByteBuf bb;

	public LinkPacketReader(ByteBuf bb) {
		this.bb = bb;
		this.bb.retain();
	}

	@Override
	public void finalize() {
		if (bb != null) {
			bb.release();
		}
	}

	private LinkPeerState handleAlive(LinkHeader lh, ByteBuf bb) {
		logger.info("handleAlive");
		LinkPeerState lps = new LinkPeerState(lh, bb);
		logger.info(lps.toString());
		return lps;
	}

	private LinkByeBye handleByeBye(LinkHeader lh, ByteBuf bb) {
		logger.info("handleByeBye");
		LinkByeBye lbb = new LinkByeBye(lh);
		logger.info(lbb.toString());
		return lbb;
	}

	private LinkResponse handleResponse(LinkHeader lh, ByteBuf bb) {
		logger.info("handleResponse");
		LinkResponse lr = new LinkResponse(lh);
		logger.info(lr.toString());
		return lr;
	}
}
