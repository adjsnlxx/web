package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 插入排序：希尔排序
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 3, 1 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int gap = array.length / 2;
		for (; gap > 0; gap /= 2) {
			for (int j = 0; j < gap; j++) {
				for (int i = 0; i + gap < array.length; i += gap) {
					if (array[i] > array[i + gap]) {
						array[i] = array[i] + array[i + gap];
						array[i + gap] = array[i] - array[i + gap];
						array[i] = array[i] - array[i + gap];
					}
				}
			}
		}
	}
}
