package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

public class LinkGHostTime {
	public final static String KEY = "__gt";
	public static final byte[] KEY_BYTES = KEY.getBytes();

	public LinkGHostTime(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		value = buf.getLong();
	}

	private final long value;
}
