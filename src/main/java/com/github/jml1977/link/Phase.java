package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkTimeline;

public class Phase {

	public static Beats toPhaseEncodedBeats(LinkTimeline timeline, long time, Beats quantum) {
		Beats beat = timeline.toBeats(time);
		return closestPhaseMatch(beat, beat.subtract(timeline.beatOrigin()), quantum);
	}

	private static Beats closestPhaseMatch(Beats x, final Beats target, final Beats quantum) {
		return nextPhaseMatch(x.subtract(new Beats(0.5 * quantum.floating())), target, quantum);
	}

	public static Beats nextPhaseMatch(final Beats x, final Beats target, final Beats quantum) {
		Beats desiredPhase = phase(target, quantum);
		Beats xPhase = phase(x, quantum);
		Beats phaseDiff = desiredPhase.subtract(xPhase).add(quantum).mod(quantum);
		return x.add(phaseDiff);
	}

	public static Beats phase(Beats beats, final Beats quantum) {
		if (quantum.equals(new Beats(0))) {
			return new Beats(0);
		}
		long quantumMicros = quantum.microbeats();
		long quantumBins = (Math.abs(beats.microbeats()) + quantumMicros) / quantumMicros;
		long quantumBeats = quantumBins * quantumMicros;
		return beats.add(new Beats(quantumBeats)).mod(quantum);
	}

}
