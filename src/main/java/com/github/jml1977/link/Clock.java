package com.github.jml1977.link;

public class Clock {
	private final long startTime = now();

	public long micros() {
		return now() - startTime;
	}

	private static long now() {
		return System.nanoTime() / 1000;
	}
}
