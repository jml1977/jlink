package com.github.jml1977.link;

import static com.github.jml1977.link.Phase.phase;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jml1977.link.messages.LinkTimeline;

public class PhaseTest {
	private LinkTimeline tl0;
	private LinkTimeline tl1;

	final private Beats zero = new Beats(0.0);
	final private Beats one = new Beats(1.0);
	final private Beats two = new Beats(2.0);
	final private Beats three = new Beats(3.0);
	final private Beats four = new Beats(4.0);

	@Before
	public void setUp() {
		tl0 = new LinkTimeline(new Tempo(120.), one, 0);
		tl1 = new LinkTimeline(new Tempo(60.), new Beats(-9.5), 2000000);
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

	@Test
	public void nextPhaseMatchZero() {
		assertEquals(Phase.nextPhaseMatch(new Beats(0.1), one, zero), new Beats(0.1));
		assertEquals(Phase.nextPhaseMatch(new Beats(2.3), new Beats(9.5), zero), new Beats(2.3));
		assertEquals(Phase.nextPhaseMatch(new Beats(-0.1), new Beats(-2.3), zero), new Beats(-0.1));
	}

	@Test
	public void nextPhaseMatchRange() {
		assertEquals(Phase.nextPhaseMatch(zero, new Beats(0.1), one), new Beats(0.1));
		assertEquals(Phase.nextPhaseMatch(new Beats(0.1), one, two), one);
		assertEquals(Phase.nextPhaseMatch(one, two, new Beats(2.3)), two);
		assertEquals(Phase.nextPhaseMatch(two, new Beats(2.3), three), new Beats(2.3));
	}

	@Test
	public void nextPhaseMatch() {
		assertEquals(Phase.nextPhaseMatch(one, new Beats(2.3), two), new Beats(2.3));
		assertEquals(Phase.nextPhaseMatch(new Beats(2.3), new Beats(-0.1), two), new Beats(3.9));
		assertEquals(Phase.nextPhaseMatch(new Beats(-9.5), new Beats(0.1), two), new Beats(-7.9));
		assertEquals(Phase.nextPhaseMatch(new Beats(-2.3), new Beats(0.1), new Beats(9.5)), new Beats(0.1));
	}

	@Test
	public void toPhaseEncodedBeatsZero() {
		long t0 = 0;
		long t1 = 2000000;
		long t2 = -3200000;
		assertEquals(Phase.toPhaseEncodedBeats(tl1, t0, zero), tl1.toBeats(t0));
		assertEquals(Phase.toPhaseEncodedBeats(tl0, t1, zero), tl0.toBeats(t1));
		assertEquals(Phase.toPhaseEncodedBeats(tl0, t2, zero), tl0.toBeats(t2));
	}

	@Test
	public void toPhaseEncodedBeatsNear() {
		long sec = 1000000;
		assertEquals(Phase.toPhaseEncodedBeats(tl0, sec, new Beats(2.2)), two);
		assertEquals(Phase.toPhaseEncodedBeats(tl0, sec, new Beats(1.8)), new Beats(3.8));
		assertEquals(Phase.toPhaseEncodedBeats(tl0, sec, two), two);
	}

	@Test
	public void toPhaseEncodedBeatsSample() {
		assertEquals(Phase.toPhaseEncodedBeats(tl0, -2000000, four), new Beats(0.).subtract(new Beats(4.)));
		assertEquals(Phase.toPhaseEncodedBeats(tl0, -3000000, four), new Beats(-6.0));
		assertEquals(Phase.toPhaseEncodedBeats(tl0, 3200000, three), new Beats(6.4));
		assertEquals(Phase.toPhaseEncodedBeats(tl1, 0, three), new Beats(-11.0));
		assertEquals(Phase.toPhaseEncodedBeats(tl1, 1500000, new Beats(2.4)), new Beats(-10.1));

	}

}
