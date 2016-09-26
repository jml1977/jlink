package com.github.jml1977.link;

import com.github.jml1977.link.messages.LinkNodeId;
import com.github.jml1977.link.messages.LinkTimeline;

public class NodeState {

	public NodeState(LinkNodeId nodeId, LinkNodeId sessionId, LinkTimeline timeline) {
		this.nodeId = nodeId;
		this.sessionId = sessionId;
		this.timeline = timeline;
	}

	private final LinkNodeId nodeId;
	private final LinkNodeId sessionId;
	private final LinkTimeline timeline;

}
