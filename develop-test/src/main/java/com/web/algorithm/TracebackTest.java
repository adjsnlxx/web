package com.web.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TracebackTest {

	public static void main(String[] args) {
		traceback1();
	}

	/**
	 * 给定一组不同的整数，返回其所有的可能组合
	 */
	public static void traceback1() {
		int[] data = new int[] { 1, 2, 3 };
		List<List<Integer>> list = new ArrayList<>();
		calcTraceback(list, new ArrayList<>(), data);

	}

	private static void calcTraceback(List<List<Integer>> list, List<Integer> tempList, int[] data) {
		if (tempList.size() == data.length) {
			list.add(new ArrayList<>(tempList));

			System.out.println(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < data.length; i++) {
				if (tempList.contains(data[i])) {
					continue;
				}

				tempList.add(data[i]);

				calcTraceback(list, tempList, data);

				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
