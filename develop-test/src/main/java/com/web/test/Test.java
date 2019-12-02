package com.web.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Test {

	private static void order(){
		List list = new ArrayList<Integer>();
		for (int i=0;i<10;i++){
			list.add(i);
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		System.out.println(Arrays.toString(list.toArray()));
		System.out.println("OK");
	}

	public static void main(String[] args) {
		order();

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		order();
	}



//	public static void main(String[] args) {
//		test1();
//		String ss = "helloo".replaceAll("(.)\\1+", "$1$1");
//		System.out.println(ss.replaceAll("(.)\\1(.)\\2", "$1$1$2"));
//	}

	/**
	 * 链接：https://www.nowcoder.com/questionTerminal/42852fd7045c442192fa89404ab42e92
	 * 来源：牛客网
	 * 输入描述:
	 * 第一行包括一个数字N，表示本次用例包括多少个待校验的字符串。
	 * 后面跟随N行，每行为一个待校验的字符串。
	 * 输出描述:
	 * N行，每行包括一个被修复后的字符串。
	 * 示例1
	 * 输入
	 * 2
	 * helloo
	 * wooooooow
	 * 输出
	 * hello
	 * woow
	 */
	public static void test1() {
		Scanner scanner = new Scanner(System.in);
		int line = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < line; i++) {
			// (.)\1 匹配两个连续的相同字符
			// $1 代表第一个正则表达式匹配到的字符串

			String ss = scanner.nextLine().replaceAll("(.)\\1+", "$1$1");
			System.out.println(ss);
			System.out.println(ss.replaceAll("(.)\\1(.)\\2", "$1$1$2"));
		}
	}
}
