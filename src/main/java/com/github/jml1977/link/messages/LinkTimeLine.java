package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

import com.github.jml1977.link.Tempo;

public class LinkTimeLine {

	// tempo
	// beats
	// timeorigin

	public LinkTimeLine(Tempo t, long beats, long origin) {
		this.tempo = t;
		beatOrigin = beats;
		timeOrigin = origin;
	}

	public Tempo getTempo() {
		return this.tempo;
	}

	public long getBeatOrigin() {
		return this.beatOrigin;
	}

	public long getTimeOrigin() {
		return this.timeOrigin;
	}

	public LinkTimeLine(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		tempo = new Tempo(buf.getLong());
		beatOrigin = buf.getLong();
		timeOrigin = buf.getLong();
	}

	/*
	 * public LinkTimeLine(ByteBuf tmln) { tempo = tmln.readLong(); // 8 bytes
	 * beatOrigin = tmln.readLong(); // 8 bytes timeOrigin = tmln.readLong(); //
	 * 8 bytes }
	 */

	private final Tempo tempo;
	private final long beatOrigin;
	private final long timeOrigin;

	@Override
	public String toString() {
		return "LinkTimeLine: (" + tempo + "," + beatOrigin + "," + timeOrigin + ")";
	}

}
