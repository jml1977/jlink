package com.github.jml1977.link.messages;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class LinkMeasurementEndpointV4 {

	public LinkMeasurementEndpointV4(byte[] mep4) {
		final ByteBuffer b = ByteBuffer.wrap(mep4);

		inet = new byte[4];
		b.get(inet);

		port = b.getShort(); // short
		if (port < 0)
			port += 65536;
	}

	private final byte[] inet;
	private int port;

	public String toString() {
		try {
			return "LMEPv4: " + InetAddress.getByAddress(inet) + ", " + port;
		} catch (UnknownHostException ex) {
			return "LMEPv4: " + Arrays.toString(inet) + ", " + port;
		}
	}
}
