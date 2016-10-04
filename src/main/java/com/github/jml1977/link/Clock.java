package com.github.jml1977.link;

public class Clock {
	private final long startTime = now();

	public long micros() {
		return now() - startTime;
	}

	public static long now() {
		return System.nanoTime() / 1000;
	}
}
