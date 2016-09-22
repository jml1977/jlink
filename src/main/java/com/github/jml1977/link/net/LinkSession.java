package com.github.jml1977.link.net;

public class LinkSession {

	public LinkSession(byte[] sess) {
		sessionId = new LinkNodeId(sess);
	}

	private final LinkNodeId sessionId;
	
	@Override
	public String toString() {
		return "LinkSession: " + sessionId.toString(); 
	}

}
