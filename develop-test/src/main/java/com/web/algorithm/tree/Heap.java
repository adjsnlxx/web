package com.web.algorithm.tree;

/**
 * 堆符合2个条件：
 * 1、堆是一个完全二叉树
 * 2、堆中的每个节点小于等于或者大于等于其子节点的值
 */
public class Heap {

	private int[] data;// 数组，从下标1开始存储数据
	private int capacity;// 堆可以存储最大的数据个数
	private int count;// 堆中已经存储的数据个数

	public Heap(int capacity) {
		data = new int[capacity + 1];
		this.capacity = capacity;
		count = 0;
	}

	public void insert(int value) {
		// 判断堆是否满了
		if (count == capacity) {
			return;
		}

		// 将插入的值放在最后
		++count;
		data[count] = value;

		// 堆化（自下往上）
		int index = count;
		int temp = 0;
		while (index / 2 > 0 && data[index] > data[index / 2]) {
			temp = data[index];
			data[index] = data[index / 2];
			data[index / 2] = temp;

			index = index / 2;
		}
	}

	public int removeMax() {
		if (count == 0) {
			return -1;
		}

		int temp = data[count];
		heapify(data, count, 1);
		return temp;
	}

	public void heapify(int[] tmpData, int n, int i) {
		int maxPos = i;
		int temp = 0;
		while(true) {
			maxPos = i;

			if (2 * i < n && tmpData[2 * i] > tmpData[i]) {
				maxPos = 2 * i;
			}

			if (2 * i + 1 < n && tmpData[2 * i + 1] > tmpData[i]) {
				maxPos = 2 * i + 1;
			}

			if (maxPos == i) {
				break;
			}

			temp = tmpData[maxPos];
			tmpData[maxPos] = tmpData[i];
			tmpData[i] = temp;

			i = maxPos;
		}
	}

	public void buildHeap() {
		for (int i = count / 2; i >= 1; i--) {
			heapify(data, count, i);
		}
	}

	public void sort() {
		buildHeap();

		int k = count;

		while (k > 1) {
			swap(data, 1, k);

			--k;

			heapify(data, k, 1);
		}
	}

	private void swap(int[] a, int aIndex, int bIndex) {
		int temp = a[aIndex];
		a[aIndex] = a[bIndex];
		a[bIndex] = temp;
	}
}
