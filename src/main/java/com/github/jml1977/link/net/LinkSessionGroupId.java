package com.github.jml1977.link.net;

public class LinkSessionGroupId {

	public LinkSessionGroupId(byte byte1, byte byte2) {
		groupId = byte1 * (2 ^ 8) + byte2;
	}

	private final int groupId;

	public int getGroupId() {
		return this.groupId;
	}

	@Override
	public String toString() {
		return "LinkSessionGroupId: " + Integer.toString(groupId);
	}
}