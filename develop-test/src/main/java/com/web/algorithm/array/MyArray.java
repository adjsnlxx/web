package com.web.algorithm.array;

/**
 * 实现一个支持动态扩容的数组
 */
public class MyArray {

	private int index;
	private int[] data;

	public MyArray(int maxCount) {
		this.index = -1;
		this.data = new int[maxCount];
	}

	public void add(int value) {
		if (this.index >= this.data.length - 1) {
			int[] oldData = this.data;
			this.data = new int[this.data.length * 2];
			for (int i = 0; i < oldData.length; i++) {
				this.data[i] = oldData[i];
			}
		}

		this.data[++this.index] = value;
	}

	public void remove(int index) {
		if (index < 0 || index >= this.data.length) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		if (this.index == index) {
			this.data[this.index--] = 0;
		} else {
			for (int i = index; i < this.index; i++) {
				this.data[index] = this.data[i + 1];
			}

			this.data[this.index--] = 0;
		}
	}

	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("[");
		for (int i = 0; i < this.data.length; i++) {
			msg.append(this.data[i]);
			if (i == this.data.length - 1) {
				msg.append("]");
			} else {
				msg.append(",");
			}
		}

		return msg.toString();
	}

	public static void main(String[] args) {
		MyArray array = new MyArray(2);
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);

		array.remove(1);

		System.out.println(array.toString());
	}
}
