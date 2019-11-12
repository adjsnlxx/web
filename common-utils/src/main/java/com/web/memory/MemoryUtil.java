package com.web.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

public class MemoryUtil {

	public static void main(String[] args) {
		System.out.println("runtime total memory = "+Runtime.getRuntime().totalMemory());

		System.out.println("managemnt total memory = "+ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString());

		System.out.println("managemnt non heap memory = "+ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().toString());

		List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		memoryPoolMXBeans.forEach((pool) -> {
			System.out.println(pool.getName());
			System.out.println(pool.getUsage());
		});

	}
}
