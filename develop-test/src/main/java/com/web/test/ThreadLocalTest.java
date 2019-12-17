package com.web.test;

public class ThreadLocalTest {

	public static void main(String[] args) {
		// 创建ThreadLocal
		ThreadLocal<String> map = new ThreadLocal<>();

		// 设置
		map.set("hello ThreadLocal");

		// 获取
		String value = map.get();

		// 输出
		System.out.println("result value = "+value);
	}
}
