package com.github.jml1977.link.linkhut;

public class StateRunner extends Thread {

	public StateRunner(com.github.jml1977.link.linkhut.State state) {
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(100);
			}
		} catch (Exception ex) {

		}
	}
}
