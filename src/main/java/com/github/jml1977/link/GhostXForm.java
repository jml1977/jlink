package com.github.jml1977.link;

public class GhostXForm {
	public GhostXForm(double slope, long intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	private final double slope;
	private final long intercept;

	public long ghostToHost(long ghostTime) {
		return (long) ((ghostTime - intercept) / slope);
	}
}
