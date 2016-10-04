package com.github.jml1977.link.utils;

public class Tuple<First, Second> {
	private final First f;
	private final Second s;

	public Tuple(First k, Second v) {
		this.f = k;
		this.s = v;
	}

	public final First first() {
		return f;
	}

	public final Second second() {
		return s;
	}
}
