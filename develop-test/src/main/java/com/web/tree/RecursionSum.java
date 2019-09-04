package com.web.tree;

public class RecursionSum {

	public static int calcRecursionSum(int n) {
		if (n <= 0) {
			return 0;
		}

		return calcRecursionSum(n - 1) + n;
	}

	public static void main(String[] args) {
		int result = calcRecursionSum(3);
		System.out.println(result);
	}
}
