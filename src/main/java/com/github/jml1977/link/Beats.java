package com.github.jml1977.link;

public class Beats {
	public Beats(long microbeats) {
		this.microbeats = microbeats;
	}

	public Beats(double beats) {
		microbeats = Math.round(beats * 1e6);
	}

	public double floating() {
		return (double) microbeats() / 1e6;
	}

	public long microbeats() {
		return microbeats;
	}

	public Beats add(Beats rhs) {
		return new Beats(microbeats() + rhs.microbeats());
	}

	public Beats subtract(Beats rhs) {
		return new Beats(microbeats() - rhs.microbeats());
	}

	public Beats mod(Beats rhs) {
		if (rhs.equals(new Beats(0))) {
			return new Beats(0);
		}
		return new Beats(microbeats % rhs.microbeats());
	}

	private final long microbeats;

	@Override
	public String toString() {
		return "Beats: " + microbeats();
	}

	@Override
	public int hashCode() {
		return new Long(microbeats()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Beats)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Beats rhs = (Beats) obj;
		return (microbeats() == rhs.microbeats());
	}
}
