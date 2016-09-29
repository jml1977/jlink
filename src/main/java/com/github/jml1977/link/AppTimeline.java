package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkTimeline;

public class AppTimeline {
	private LinkTimeline originalTimeline;

	private boolean respectQuantum;

	private LinkTimeline timeline;

	public AppTimeline(final LinkTimeline timeline, final boolean respectQuantum) {
		this.originalTimeline = timeline;
		this.respectQuantum = respectQuantum;
		this.timeline = timeline;
	}

	public double beatAtTime(final long time, final double quantum) {
		return Phase.toPhaseEncodedBeats(timeline, time, new Beats(quantum)).floating();
	}

	public double phaseAtTime(long time, double quantum) {
		return Phase.phase(new Beats(beatAtTime(time, quantum)), new Beats(quantum)).floating();
	}

	public double tempo() {
		return timeline.tempo().asBpm();
	}
}
