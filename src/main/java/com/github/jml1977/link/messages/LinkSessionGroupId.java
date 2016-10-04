package com.github.jml1977.link.messages;

public class LinkSessionGroupId {

	public LinkSessionGroupId(byte byte1, byte byte2) {
		groupId = byte1 * (2 ^ 8) + byte2;
	}

	private final int groupId;

	public LinkSessionGroupId(int groupId) {
		this.groupId = groupId;
	}

	// public int getGroupId() {
	// return this.groupId;
	// }

	@Override
	public String toString() {
		return "LinkSessionGroupId: " + Integer.toString(groupId);
	}

	public byte[] toNetworkBytes() {
		byte[] b = new byte[2];
		b[0] = (byte) (groupId >> 8);
		b[1] = (byte) (groupId & 0xFF);
		return b;
	}
}