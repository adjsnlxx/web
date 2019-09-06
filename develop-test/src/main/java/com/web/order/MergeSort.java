package com.web.order;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 3, 1 };
		array = sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static int[] sort(int[] array) {
		if (array.length <= 1) {
			return array;
		}

		int temp = array.length >> 1;

		int[] left = Arrays.copyOfRange(array, 0, temp);
		int[] right = Arrays.copyOfRange(array, temp, array.length);
		return mergeArray(sort(left), sort(right));
	}

	public static int[] mergeArray(int[] left, int[] right) {
		int i = 0, j = 0, k = 0;
		int[] result = new int[left.length + right.length];
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				result[k++] = left[i++];
			} else {
				result[k++] = right[j++];
			}
		}

		while (i < left.length) {
			result[k++] = left[i++];
		}

		while (j < right.length) {
			result[k++] = right[j++];
		}

		return result;
	}

}
