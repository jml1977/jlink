package com.github.jml1977.link.net;

import com.github.jml1977.link.GhostXForm;
import com.github.jml1977.link.NodeState;

public class Discovery {

	public Discovery(NodeState state, GhostXForm ghostXForm, Object factory, Object io) {
		gateways = new PeerGateways(3, state, factory, io);
	}

	public void setEnabled(boolean enabled) {
		gateways.setEnabled(enabled);
	}

	private final PeerGateways gateways;

}
