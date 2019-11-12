package com.web.common;

public class AssertTest {

	public static void main(String[] args) {
		// 默认断言是关闭的，可通过设置一下jvm的参数，参数是-enableassertions或者-ea（推荐）
		int a = 1;
		int b = 2;

		assert a > b : "错误，a不大于b";

		System.out.println("ok");
	}
}
