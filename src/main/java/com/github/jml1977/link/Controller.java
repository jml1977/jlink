package com.github.jml1977.link;

public class Controller {
	private Clock clock;

	private Tempo tempo;
	public Controller(Tempo tempo, Clock clock, PeerCountCallback peerCountCallback, TempoCallback tempoCallback) {
		this.tempo = tempo;
		this.clock = clock;
	}
}
