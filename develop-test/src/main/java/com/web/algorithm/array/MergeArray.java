package com.web.algorithm.array;

import java.util.Arrays;

/**
 * 实现两个有序数组合并为一个有序数组
 */
public class MergeArray {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 5, 7, 9, 10, 11, 13, 15 };
		int[] b = new int[] { 2, 4, 6, 8};

		int[] data = merge(a, b);
		System.out.println(Arrays.toString(data));
	}

	public static int[] merge(int[] a, int[] b) {
		int[] data = new int[a.length + b.length];

		int aIndex = 0;
		int bIndex = 0;
		int index = 0;
		while (aIndex < a.length && bIndex < b.length) {
			if (a[aIndex] < b[bIndex]) {
				data[index++] = a[aIndex++];
			} else {
				data[index++] = b[bIndex++];
			}
		}

		if (aIndex < a.length) {
			for (int i = aIndex; i < a.length; i++) {
				data[index++] = a[i];
			}
		}

		if (bIndex < b.length) {
			for (int i = bIndex; i < b.length; i++) {
				data[index++] = b[i];
			}
		}

		return data;
	}
}
