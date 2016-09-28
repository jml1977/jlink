package com.github.jml1977.link.linkhut;

import com.github.jml1977.link.AppTimeline;
import com.github.jml1977.link.Beats;
import com.github.jml1977.link.Phase;

public class JLinkHut {
	public static void main(String[] args) throws Exception {
		State state = new State();
		StateRunner runner = new StateRunner(state);
		runner.start();
		while (runner.isAlive()) {
			long time = state.link.clock().micros();
			AppTimeline timeline = state.link.captureAppTimeline();
			printState(time, timeline, state.link.numPeers(), null);
			Thread.sleep(10);
		}
		runner.join();
	}

	private static void printState(long time, AppTimeline timeline, int numPeers, Beats quantum) {
		double beats = timeline.beatAtTime(time, quantum);
		Phase phase = timeline.phaseAtTime(time, quantum);
		System.out.println("peers: " + numPeers + " | quantum: " + quantum + " | " + timeline.tempo() + " | " + beats + " | ");
	}
}