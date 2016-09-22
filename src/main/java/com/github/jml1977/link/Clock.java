package com.github.jml1977.link;

public class Clock {
	public long micros() {
		return System.nanoTime() / 1000;
	}
}
