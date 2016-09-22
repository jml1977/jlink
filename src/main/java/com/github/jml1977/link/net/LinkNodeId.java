package com.github.jml1977.link.net;

public class LinkNodeId {
	public LinkNodeId(byte[] id) {
		this.id = id;
		stringFormat = new String(id); // spec requires it to be printable
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
