package com.github.jml1977.link;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jml1977.link.messages.LinkTimeline;

public class TempoTest {
	@Before
	public void setUp() {

	}

	@Test
	public void test1() {
		LinkTimeline ltl = new LinkTimeline(new Tempo(60.), new Beats(-1.0), 1000000);
		Assert.assertEquals(new Beats(2.5), ltl.toBeats(4500000));
	}
}