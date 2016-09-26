package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkTimeLine;

public class Controller {
	private Clock clock;

	private boolean enabled;

	private final PeerCountCallback peerCountCallback;

	private LinkNodeId myNodeId;
	private LinkNodeId sessionId;

	private GhostXForm ghostXForm;

	private Tempo tempo;

	private final TempoCallback tempoCallback;

	public Controller(Tempo tempo, Clock clock, PeerCountCallback peerCountCallback, TempoCallback tempoCallback) {
		this.tempo = tempo;
		this.clock = clock;
		this.peerCountCallback = peerCountCallback;
		this.tempoCallback = tempoCallback;
		this.enabled = false;
		this.myNodeId = LinkNodeId.random();
		this.sessionId = this.myNodeId;
		this.sessionTimeline = clampTempo(new LinkTimeLine(this.tempo, 0, 0));
		this.ghostXForm = initXForm(this.clock);
		this.clientTimeline = new LinkTimeLine(sessionTimeline.getTempo(), 0, ghostXForm.ghostToHost(0));
	}

	private LinkTimeLine clientTimeline;

	private GhostXForm initXForm(Clock clock) {
		return new GhostXForm(-1.0, -clock.micros());
	}

	private LinkTimeLine clampTempo(LinkTimeLine timeline) {
		final double minBPM = 20.0;
		final double maxBPM = 999.0;
		final double currentBpm = timeline.getTempo().asBpm();
		final double bpm = Math.min(Math.max(currentBpm, minBPM), maxBPM);
		Tempo newTempo = new Tempo(bpm);
		return new LinkTimeLine(newTempo, timeline.getBeatOrigin(), timeline.getTimeOrigin());
	}

	private LinkTimeLine sessionTimeline;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean newEnabled) {
		if (this.enabled != newEnabled) {
			this.enabled = newEnabled;
			if (newEnabled) {
				resetState();
			}
		}
	}

	private void resetState() {
		myNodeId = LinkNodeId.random();
		sessionId = myNodeId;
	}
}
