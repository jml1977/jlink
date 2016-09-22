package com.github.jml1977.link.linkhut;

import com.github.jml1977.link.Link;

public class State {
	protected boolean running = true;
	protected Link link;
	
	public State() {
		this(120);
	}

	public State(double bpm) {
		this.link = new Link(bpm);
		this.link.enable(true);
	}
}
