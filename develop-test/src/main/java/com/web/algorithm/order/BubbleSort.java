package com.web.algorithm.order;

/**
 * 交换排序：冒泡排序
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] array = { 2, 3, 70, 5 };
		sort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

		System.out.println();
	}

	public static void sort(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int length = array.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					array[j] = array[j] + array[j + 1];
					array[j + 1] = array[j] - array[j + 1];
					array[j] = array[j] - array[j + 1];
				}
			}
		}
	}
}
