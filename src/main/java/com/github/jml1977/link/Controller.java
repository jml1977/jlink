package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkTimeline;
import com.github.jml1977.link.net.Discovery;

public class Controller {
	private Clock clock;

	private boolean enabled;

	private final PeerCountCallback peerCountCallback;

	private LinkNodeId nodeId;
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
		this.nodeId = LinkNodeId.random();
		this.sessionId = this.nodeId;
		this.sessionTimeline = clampTempo(new LinkTimeline(this.tempo, new Beats(0), 0));
		this.ghostXForm = initXForm(this.clock);
		this.clientTimeline = new LinkTimeline(sessionTimeline.getTempo(), new Beats(0), ghostXForm.ghostToHost(0));

		this.discovery = new Discovery(new NodeState(nodeId, sessionId, sessionTimeline), ghostXForm, null /* GatewayFactory */,
				null /* UdpExceptionHandler */);
	}

	private Discovery discovery;
	private LinkTimeline clientTimeline;

	private GhostXForm initXForm(Clock clock) {
		return new GhostXForm(-1.0, -clock.micros());
	}

	private LinkTimeline clampTempo(LinkTimeline timeline) {
		final double minBPM = 20.0;
		final double maxBPM = 999.0;
		final double currentBpm = timeline.getTempo().asBpm();
		final double bpm = Math.min(Math.max(currentBpm, minBPM), maxBPM);
		Tempo newTempo = new Tempo(bpm);
		return new LinkTimeline(newTempo, timeline.getBeatOrigin(), timeline.getTimeOrigin());
	}

	private LinkTimeline sessionTimeline;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean newEnabled) {
		if (this.enabled != newEnabled) {
			this.enabled = newEnabled;
			if (newEnabled) {
				resetState();
			}
			discovery.setEnabled(newEnabled);
		}
	}

	private void resetState() {
		nodeId = LinkNodeId.random();
		sessionId = nodeId;
	}
}
