package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 插入排序：直接插入排序
 */
public class DirectInsertionSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 3, 1 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 移位法
	 *
	 * @param array
	 */
	public static void sort(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		for (int i = 1; i < array.length; i++) {
			int j = i - 1;
			int temp = array[i];

			while (j >= 0 && temp < array[j]) {
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = temp;
		}

	}

	/**
	 * 交换法
	 *
	 * @param array
	 */
	public static void sort1(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		for (int i = 1; i < array.length; i++) {
			int j = i - 1;
			while (j >= 0 && array[i] < array[j]) {
				array[j + 1] = array[j] + array[j + 1];
				array[j] = array[j + 1] - array[j];
				array[j + 1] = array[j + 1] - array[j];

				i--;
				j--;
			}
		}
	}
}
