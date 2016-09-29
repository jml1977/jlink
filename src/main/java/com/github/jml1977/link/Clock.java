package com.github.jml1977.link;

public class Clock {
	private final long startTime = System.nanoTime() / 1000;

	public long micros() {
		return (System.nanoTime() / 1000) - startTime;
	}
}
