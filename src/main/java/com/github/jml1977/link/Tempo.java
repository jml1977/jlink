package com.github.jml1977.link;

public class Tempo {
	private double bpm;

	public Tempo(long microsPerBeat) {
		this(60.0d * 1e6 / microsPerBeat);
	}

	public Tempo(double bpm) {
		this.bpm = bpm;
	}

	public double asBpm() {
		return this.bpm;
	}

	public long asLong() {
		return (long)(60.0 * 1e6 / bpm);
	}

	@Override
	public String toString() {
		return "Tempo: " + Double.toString(asBpm());
	}
}
