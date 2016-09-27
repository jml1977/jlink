package com.github.jml1977.link;

import org.junit.Assert;
import org.junit.Test;

public class BeatsTest {
	@Test
	public void ConstructFromFloating() {
		Beats b = new Beats(0.5);
		Assert.assertEquals(500000, b.microbeats());
		Assert.assertEquals(0.5, b.floating(), 0.01);
	}

	@Test
	public void ConstructFromMicros() {
		Beats b = new Beats(100000);
		Assert.assertEquals(100000, b.microbeats());
		Assert.assertEquals(0.1, b.floating(), 0.01);
	}

	@Test
	public void Addition() {
		Beats b1 = new Beats(0.5);
		Beats b2 = new Beats(200000);
		Beats b3 = new Beats(0.1);
		Assert.assertEquals(b1, b2.add(b2).add(b3));
	}

	@Test
	public void Subtraction() {
		Beats b1 = new Beats(0.5);
		Beats b2 = new Beats(200000);
		Beats b3 = new Beats(0.1);
		Assert.assertEquals(b3, b1.subtract(b2).subtract(b2));
	}
}
