package com.web.algorithm.order;

import java.util.Arrays;

/**
 * 选择排序：堆排序
 * 堆的存储
 * 一般都用数组来表示堆，i结点的父结点下标就为(i – 1) / 2。
 * 它的左右子结点下标分别为2 * i + 1和2 * i + 2。如第0个结点左右子结点下标分别为1和2。
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] array = { 5, 4, 2, 3, 1 };
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		// 1、构建大顶堆
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			adjustHeap(array, i, array.length);
		}

		//2.调整堆结构+交换堆顶元素与末尾元素
		for (int j = array.length - 1; j > 0; j--) {
			//将堆顶元素与末尾元素进行交换
			swap(array, 0, j);
			//重新对堆进行调整
			adjustHeap(array, 0, j);
		}
	}

	/**
	 * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 *
	 * @param arr
	 * @param i
	 * @param length
	 */
	public static void adjustHeap(int[] arr, int i, int length) {
		//先取出当前元素i
		int temp = arr[i];
		//从i结点的左子结点开始，也就是2i+1处开始
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			//如果左子结点小于右子结点，k指向右子结点
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}

			//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
			if (arr[k] > temp) {
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}

		//将temp值放到最终的位置
		arr[i] = temp;
	}

	/**
	 * 交换元素
	 *
	 * @param arr
	 * @param a
	 * @param b
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
