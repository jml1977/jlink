package com.github.jml1977.link;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static com.github.jml1977.link.Phase.phase;

import com.github.jml1977.link.messages.LinkTimeline;

public class PhaseTest {
	private LinkTimeline tl0;

	private Beats zero = new Beats(0.0);
	private Beats one = new Beats(1.0);

	@Before
	public void setUp() {
		tl0 = new LinkTimeline(new Tempo(120.), one, 0);
	}

	@Test
	public void phase0() {
		Assert.assertEquals(Phase.phase(zero, zero), zero);
		Assert.assertEquals(Phase.phase(new Beats(0.1), zero), zero);
		Assert.assertEquals(Phase.phase(one, zero), zero);
		Assert.assertEquals(Phase.phase(zero.subtract(one), zero), zero);
	}

	@Test
	public void mod() {
		assertEquals(phase(zero, zero), zero.mod(zero));
		assertEquals(phase(one, zero), one.mod(zero));
		assertEquals(phase(zero, one), zero.mod(one));
		assertEquals(phase(new Beats(0.1), one), new Beats(0.1).mod(one));
		assertEquals(phase(new Beats(2.3), one), new Beats(2.3).mod(one));
		assertEquals(phase(new Beats(9.5), new Beats(2.3)), new Beats(9.5).mod(new Beats(2.3)));
	}

	@Test
	public void zeroMirrored() {
		assertEquals(phase(zero.subtract(one), one), zero);
		assertEquals(phase(new Beats(-0.1), one), new Beats(0.9));
		assertEquals(phase(new Beats(-2.3), one), new Beats(0.7));
		assertEquals(phase(new Beats(-9.5), new Beats(2.3)), new Beats(2.));
	}

}
