package com.web.algorithm.tree;

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
		if (count == capacity) {
			return;
		}

		++count;
		data[count] = value;

		int i = count;
		int temp = 0;
		while (i / 2 > 0 && data[i] > data[i / 2]) {
			temp = data[i/2];
			data[i/2] = data[i];
			data[i] = temp;

			i = i / 2;
		}
	}
}
