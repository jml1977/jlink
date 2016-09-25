package com.github.jml1977.link.messages;

import java.util.concurrent.ThreadLocalRandom;

public class LinkNodeId {
	public LinkNodeId(byte[] id) {
		this.id = id;
		stringFormat = new String(id); // spec requires it to be printable
	}

	public static LinkNodeId random() {
		byte[] id = new byte[8];
		for (int i = 0; i < 8; i++) {
			id[i] = (byte) ThreadLocalRandom.current().nextInt(33, 126);
		}
		return new LinkNodeId(id);
	}

	private final String stringFormat;
	private final byte id[];

	public byte[] getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LinkNodeId: " + stringFormat;
	}
}
