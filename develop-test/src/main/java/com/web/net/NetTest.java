package com.web.net;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class NetTest {

	public static void main(String[] args) {
		boolean bool = true;
		int data = 0x10;
		System.out.println(bool);
//		new NetTest().testNetOrder();
//		System.out.println(Byte.toUnsignedInt((byte) 1));
	}

	public void testNetOrder() {
		ByteBuffer buffer= ByteBuffer.allocate(4);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.asIntBuffer().put(1);
		System.out.println(Arrays.toString(buffer.array()));
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println(Arrays.toString(buffer.array()));
	}
}
