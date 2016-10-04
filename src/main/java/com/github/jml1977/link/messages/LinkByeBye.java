package com.github.jml1977.link.messages;

public class LinkByeBye {

	private final LinkHeader lh;

	public LinkByeBye(final LinkHeader lh) {
		this.lh = lh;
	}

	@Override
	public String toString() {
		return lh.toString();
	}
	
	public LinkNodeId ident() {
		return lh.ident();
	}

	public byte[] toNetworkBytes() {
		return lh.toNetworkBytes(); // nothign else to see here
	}
}
