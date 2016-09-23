package com.github.jml1977.link;

public class Link {
	private Clock clock;

	private Controller controller;

	public Link(double bpm) {
		this.clock = new Clock();
		this.controller = new Controller(new Tempo(bpm), clock, (i) -> {
		}, (t) -> {
		});
	}

	public Clock clock() {
		return this.clock;
	}

	public boolean enable(boolean newEnable) {
		return newEnable;
	}
}
