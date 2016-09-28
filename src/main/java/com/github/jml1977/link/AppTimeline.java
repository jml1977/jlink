package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkTimeline;

public class AppTimeline {
	public AppTimeline(final LinkTimeline timeline, final boolean respectQuantum) {
		this.originalTimeline = timeline;
		this.respectQuantum = respectQuantum;
		this.timeline = timeline;
	}

	public double tempo() {
		return timeline.tempo().asBpm();
	}

	private LinkTimeline originalTimeline;
	private boolean respectQuantum;
	private LinkTimeline timeline;

	public double beatAtTime(final long time, final Beats quantum) {
		return Phase.toPhaseEncodedBeats(timeline, time, quantum).floating();
	}

	public static Phase phaseAtTime(long time, Beats quantum) {
		return null;
	}
}
