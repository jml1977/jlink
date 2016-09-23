package com.github.jml1977.link.messages;

public enum LinkMessageType {
	INVALID(0), ALIVE(1), RESPONSE(2), BYEBYE(3);

	private final int id;

	LinkMessageType(int id) {
		this.id = id;
	}

	public static LinkMessageType fromValue(int id) throws InvalidLinkMessageException {
		for (LinkMessageType mt : values()) {
			if (mt.getId() == id) {
				return mt;
			}
		}
		throw new InvalidLinkMessageException("Value not found in MessageType " + Integer.toString(id));
	}

	public int getId() {
		return this.id;
	}

};