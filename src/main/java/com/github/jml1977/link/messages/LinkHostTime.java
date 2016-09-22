package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

public class LinkHostTime {
	public final static String KEY = "__ht";
	public static final byte[] KEY_BYTES = KEY.getBytes();

	public LinkHostTime(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		value = buf.getLong();
	}

	private final long value;

}
