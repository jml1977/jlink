package com.github.jml1977.link.messages;

import io.netty.buffer.ByteBuf;

public class LinkByeBye {

	private final LinkHeader lh;

	public LinkByeBye(final LinkHeader lh, ByteBuf bb) {
		this.lh = lh;
	}

	@Override
	public String toString() {
		return lh.toString();
	}
}
