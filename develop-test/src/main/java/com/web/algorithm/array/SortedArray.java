package com.web.algorithm.array;

/**
 * 实现一个大小固定的有序数组，支持动态增删改操作
 * 有序数组-倒序
 */
public class SortedArray {

	private int index;
	private int[] data;

	public SortedArray(int maxCount) {
		this.index = -1;
		this.data = new int[maxCount];
	}

	public void add(int value) {
		if (index == -1) {
			data[++index] = value;
		} else {
			int insertIndex = -1;
			for (int i = index; i >= 0; i--) {
				if (value < data[i]) {
					insertIndex = i;
					break;
				}
			}

			if (insertIndex == index && index == data.length - 1){
				return;
			}

			if (index == data.length - 1) {
				for (int i = index; i > 0; i--) {
					data[i] = data[i - 1];
				}
			} else {
				for (int i = index + 1; i > 0; i--) {
					data[i] = data[i - 1];
				}

				index++;
			}

			this.data[insertIndex + 1] = value;
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
		SortedArray array = new SortedArray(2);
		array.add(1);
		array.add(100);
		array.add(200);
		array.add(4);
		array.add(5);

		System.out.println(array.toString());
	}
}
