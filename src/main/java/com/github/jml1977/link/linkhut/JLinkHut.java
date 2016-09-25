package com.github.jml1977.link.linkhut;

public class JLinkHut {
	public static void main(String[] args) throws Exception {
		State state = new State();
		StateRunner runner = new StateRunner(state);
		runner.start();
		while (runner.isAlive()) {
			System.out.println(state.link.clock().micros());
			// Object timeline = state.link.captureAppTimeline();
			Thread.sleep(10);
		}
		runner.join();
	}
}