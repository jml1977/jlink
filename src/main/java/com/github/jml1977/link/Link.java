package com.github.jml1977.link;

public class Link {
	private Clock clock;

	private Controller controller;

	private Object mutex = new Object();

	private PeerCountCallback peerCountCallback = i -> {
		// do nothing
	};

	private TempoCallback tempoCallback = t -> {
		// do nothing
	};

	public Link(double bpm) {
		this.clock = new Clock();
		this.controller = new Controller(new Tempo(bpm), clock, i -> {
			synchronized (mutex) {
				peerCountCallback.callback(i);
			}

		}, t -> {
			synchronized (mutex) {
				tempoCallback.callback(t);
			}
		});
	}

	public boolean isEnabled() {
		return controller.isEnabled();
	}

	public void setEnabled(boolean enabled) {
		controller.setEnabled(enabled);
	}

	public Clock clock() {
		return this.clock;
	}

	public void setPeerCountCallback(PeerCountCallback pcc) {
		synchronized (mutex) {
			this.peerCountCallback = pcc;
		}
	}

	public void setTempoCallback(TempoCallback tc) {
		synchronized (mutex) {
			this.tempoCallback = tc;
		}
	}

	public int numPeers() {
		return 0;
	}

	public AppTimeline captureAppTimeline() {
		return new AppTimeline(controller.timeline(), numPeers() > 0);
	}
}
