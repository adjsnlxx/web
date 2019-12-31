package com.web.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTest {

	public static void main(String[] args) {
		//		int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
		//		long curTime = System.currentTimeMillis();
		//		List<List<Integer>> list = threeSum(nums);
		//		System.out.println("interval time = " + (System.currentTimeMillis() - curTime));
		//
		//		List<Integer> temp = null;
		//		while (list.size() > 0 && (temp = list.remove(0)) != null) {
		//			System.out.println(Arrays.toString(temp.toArray()));
		//		}

		//		int[] nums = new int[]{2,2,1,1,1,2,2};
		int[] nums = new int[] { 3, 2, 3 };
		System.out.println(majorityElement(nums));
	}

	/**
	 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	 * 满足要求的三元组集合为：
	 * [
	 * [-1, 0, 1],
	 * [-1, -1, 2]
	 * ]
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/3sum
	 *
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		if (nums == null) {
			return Collections.emptyList();
		}

		Arrays.sort(nums);

		List<List<Integer>> list = new ArrayList<>();

		for (int index1 = 0; index1 < nums.length - 2; index1++) {
			if (nums[index1] > 0) {
				break;
			}

			if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
				continue;
			}

			for (int index2 = index1 + 1, index3 = nums.length - 1; index2 < index3; ) {
				int sum = nums[index1] + nums[index2] + nums[index3];
				if (sum == 0) {
					list.add(Arrays.asList(nums[index1], nums[index2], nums[index3]));

					while (index2 < index3 && nums[index2] == nums[index3]) {
						index2++;
					}

					while (index2 < index3 && nums[index3] == nums[index3 - 1]) {
						index3--;
					}

					index2++;
					index3--;
				} else if (sum < 0) {
					index2++;
				} else {
					index3--;
				}
			}
		}

		return list;
	}

	/**
	 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
	 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
	 * 示例 1:
	 * 输入: [3,2,3]
	 * 输出: 3
	 * 示例 2:
	 * 输入: [2,2,1,1,1,2,2]
	 * 输出: 2
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/majority-element
	 *
	 * @param nums
	 * @return
	 */
	public static int majorityElement(int[] nums) {
		if (nums == null) {
			return -1;
		}

		Arrays.sort(nums);

		int i = 0;
		int j = 0;
		for (i = 0, j = i + 1; j < nums.length; j++) {
			if (nums[i] < nums[j]) {
				if ((j - i) > (nums.length / 2)) {
					return nums[i];
				} else {
					i = j;
				}
			}
		}

		if (i < j) {
			if ((j - i) > (nums.length / 2)) {
				return nums[i];
			}
		}

		return -1;
	}

}
