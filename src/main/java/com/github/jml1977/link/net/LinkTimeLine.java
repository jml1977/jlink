package com.github.jml1977.link.net;

import java.nio.ByteBuffer;

public class LinkTimeLine {

	// tempo
	// beats
	// timeorigin

	public LinkTimeLine(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		tempo = buf.getLong();
		beatOrigin = buf.getLong();
		timeOrigin = buf.getLong();
	}

	/*
	 * public LinkTimeLine(ByteBuf tmln) { tempo = tmln.readLong(); // 8 bytes
	 * beatOrigin = tmln.readLong(); // 8 bytes timeOrigin = tmln.readLong(); //
	 * 8 bytes }
	 */

	private final long tempo;
	private final long beatOrigin;
	private final long timeOrigin;

	@Override
	public String toString() {
		return "LinkTimeLine: (" + tempo + "," + beatOrigin + "," + timeOrigin + ")";
	}

}
