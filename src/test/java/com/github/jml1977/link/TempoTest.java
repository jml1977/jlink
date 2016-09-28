package com.github.jml1977.link;

import org.junit.Assert;
import org.junit.Test;

public class TempoTest {
	@Test
	public void ConstructFromBpm() {
		Tempo t = new Tempo(120.0);
		Assert.assertEquals(120., t.asBpm(), 0.01);
		Assert.assertEquals(500000, t.microsPerBeat());
	}
	
	@Test
	public void ConstructFromMicros() {
		Tempo t = new Tempo(500000);
		Assert.assertEquals(120., t.asBpm(), 0.01);
		Assert.assertEquals(500000, t.microsPerBeat());
	}
	
	@Test
	public void MicrosToBeats() {
		Tempo t = new Tempo(120.0);
		Assert.assertEquals(new Beats(2.0), t.microsToBeats(1000000));
	}
	
	@Test
	public void BeatsToMicros() {
		Tempo t = new Tempo(120.0);
		Assert.assertEquals(1000000, t.beatsToMicros(new Beats(2.)));
	}
}
