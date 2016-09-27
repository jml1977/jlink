package com.github.jml1977.link;

import org.junit.Assert;
import org.junit.Test;

import com.github.jml1977.link.messages.LinkTimeline;

public class TempoTest {
	@Test
	public void testToBeats() {
		LinkTimeline ltl = new LinkTimeline(new Tempo(60.), new Beats(-1.0), 1000000);
		Assert.assertEquals(new Beats(2.5), ltl.toBeats(4500000));
	}

	@Test
	public void testFromBeats() {
		LinkTimeline ltl = new LinkTimeline(new Tempo(60.), new Beats(-1.0), 1000000);
		Assert.assertEquals(5200000, ltl.fromBeats(new Beats(3.2)));
	}
}
