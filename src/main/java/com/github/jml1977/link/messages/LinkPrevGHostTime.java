package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

public class LinkPrevGHostTime {
	public final static String KEY = "_pgt";
	protected static final byte[] KEY_BYTES = KEY.getBytes();

	public LinkPrevGHostTime(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		value = buf.getLong();
	}

	private final long value;
}
