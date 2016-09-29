package com.github.jml1977.link;

public class GhostXForm {
	private final long intercept;

	private final double slope;

	public GhostXForm(double slope, long intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	public long ghostToHost(long ghostTime) {
		return (long) ((ghostTime - intercept) / slope);
	}
}
