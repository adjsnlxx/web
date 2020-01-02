package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 选择排序：简单选择排序
 */
public class SimpleSelectSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 3, 1 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int minIndex = 0;
		for (int i = 0; i < array.length; i++) {
			minIndex = i;

			for (int j = i; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}

			if (i != minIndex) {
				array[i] = array[i] + array[minIndex];
				array[minIndex] = array[i] - array[minIndex];
				array[i] = array[i] - array[minIndex];
			}
		}
	}
}
