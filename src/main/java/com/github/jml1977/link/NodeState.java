package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkTimeline;

public class NodeState {

	private final LinkNodeId nodeId;

	private final LinkNodeId sessionId;

	private final LinkTimeline timeline;

	public NodeState(LinkNodeId nodeId, LinkNodeId sessionId, LinkTimeline timeline) {
		this.nodeId = nodeId;
		this.sessionId = sessionId;
		this.timeline = timeline;
	}

	public LinkNodeId ident() {
		return this.nodeId;
	}

}
