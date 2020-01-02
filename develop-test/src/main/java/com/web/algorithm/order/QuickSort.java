package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 交换排序：快速排序
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = { 0, 2, 6, 4, 3 };
		sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));

		System.out.println(Integer.MAX_VALUE);
	}

	public static void sort(int[] array, int low, int high) {
		if (array == null || array.length == 0) {
			return;
		}

		if (low >= high) {
			return;
		}

		int left = low;
		int right = high;
		int key = array[left];

		while (left < right) {
			while (left < right && array[right] >= key) {
				right--;
			}

			while (left < right && array[left] <= key) {
				left++;
			}

			if (left < right) {
				swap(array, left, right);
			}
		}

		swap(array, low, left);
		sort(array, low, left - 1);
		sort(array, left + 1, high);
	}

	public static void swap(int array[], int low, int high) {
		int temp = array[low];
		array[low] = array[high];
		array[high] = temp;
	}
}
