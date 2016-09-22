package com.github.jml1977.link.messages;

import io.netty.buffer.ByteBuf;

public class LinkByeBye {

	public LinkByeBye(final LinkHeader lh, ByteBuf bb) {
		this.lh = lh;
	}

	private final LinkHeader lh;

	@Override
	public String toString() {
		return lh.toString();
	}
}
