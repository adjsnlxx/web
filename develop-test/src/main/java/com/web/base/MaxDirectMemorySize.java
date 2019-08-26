package com.web.base;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class MaxDirectMemorySize {

	public static void main(String[] args) {
		System.out.println("maxMemoryValue = " + sun.misc.VM.maxDirectMemory());

		ByteBuffer buffer = ByteBuffer.allocateDirect(0);
		try {
			Class<?> c = Class.forName("java.nio.Bits");
			Field maxMemory = c.getDeclaredField("maxMemory");
			maxMemory.setAccessible(true);
			synchronized (c) {
				Long maxMemoryValue = (Long) maxMemory.get(null);
				System.out.println("maxMemoryValue = " + maxMemoryValue);
			}

		} catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
