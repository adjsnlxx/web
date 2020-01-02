package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 基数排序（桶排序）
 * 基数排序的思想就是先排好个位，然后排好个位的基础上排十位，以此类推，直到遍历最高位次，排序结束（仔细理解最后一句话）
 * 基数排序不是比较排序，而是通过分配和收集的过程来实现排序
 * 初始化10个桶(固定的)，桶下标为0-9
 * 通过得到待排序数字的个十百等位的数字，把这个数字对应的item放到对应的桶中
 * 基数排序有两种排序方式：LSD和MSD，最小位优先(从右边开始)和最大位优先(从左边开始)
 */
public class RadixSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 30, 1 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}

		int num = 0;
		while (max != 0) {
			max = max / 10;
			num++;
		}

		int[][] result = new int[10][array.length];

		// int base=1;//代表位数对应的数：1,10,100...
		int base = 1;
		// 从低位到高位，对每一位遍历，将所有元素分配到桶中
		for (int i = 0; i < num; i++) {
			// 存储各个桶中存储元素的数量
			int[] bucket = new int[10];
			int whichBucket = 0;
			int bucketLength = 0;
			for (int j = 0; j < array.length; j++) {
				whichBucket = (array[j] / base) % 10;
				bucketLength = bucket[whichBucket];

				result[whichBucket][bucketLength] = array[j];
				bucket[whichBucket] = bucketLength + 1;
			}

			int index = 0;
			for (int j = 0; j < result.length; j++) {
				for (int k = 0; k < bucket[j]; k++) {
					array[index++] = result[j][k];
				}
			}

			base *= 10;
		}
	}
}
