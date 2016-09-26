package com.github.jml1977.link.messages;

import java.nio.ByteBuffer;

import com.github.jml1977.link.Beats;
import com.github.jml1977.link.Tempo;

public class LinkTimeline {

	// tempo
	// beats
	// timeorigin

	private final Beats beatOrigin;

	private final Tempo tempo;

	private final long timeOrigin;

	public LinkTimeline(byte[] b) {
		ByteBuffer buf = ByteBuffer.wrap(b);
		tempo = new Tempo(buf.getLong());
		beatOrigin = new Beats(buf.getLong());
		timeOrigin = buf.getLong();
	}

	public LinkTimeline(Tempo t, Beats beats, long origin) {
		this.tempo = t;
		beatOrigin = beats;
		timeOrigin = origin;
	}

	public Beats toBeats(long time) {
		return beatOrigin.add(tempo.microsToBeats(time - timeOrigin));
	}

	public long fromBeats(Beats beats) {
		return timeOrigin + tempo.beatsToMicros(beats.subtract(beatOrigin));
	}

	public Beats getBeatOrigin() {
		return this.beatOrigin;
	}

	public Tempo getTempo() {
		return this.tempo;
	}

	public long getTimeOrigin() {
		return this.timeOrigin;
	}

	@Override
	public String toString() {
		return "LinkTimeLine: (" + tempo + "," + beatOrigin + "," + timeOrigin + ")";
	}

}
