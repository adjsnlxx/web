package com.web.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayTest {

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

	/**
	 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
	 * <p>
	 * 示例 1:
	 * <p>
	 * 输入: [1,2,0]
	 * 输出: 3
	 * <p>
	 * 示例 2:
	 * <p>
	 * 输入: [3,4,-1,1]
	 * 输出: 2
	 * <p>
	 * 示例 3:
	 * <p>
	 * 输入: [7,8,9,11,12]
	 * 输出: 1
	 * 说明:
	 * <p>
	 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/first-missing-positive
	 *
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 1;
		}

		int minValue = nums[0];
		int maxValue = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < minValue) {
				minValue = nums[i];
			}

			if (nums[i] > maxValue) {
				maxValue = nums[i];
			}
		}

		boolean found = false;
		int i = 1;
		for (; i <= maxValue; i++) {
			found = false;
			for (int j = 0; j < nums.length; j++) {
				if (i == nums[j]) {
					found = true;
					break;
				}
			}

			if (!found) {
				return i;
			}
		}

		if (i > maxValue) {
			return i;
		}

		return 0;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static boolean hasCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null && fast.next.next != null) {
			if (slow != null && slow.next != null) {
				if (slow.next == fast.next.next) {
					return true;
				}
			}

			fast = fast.next.next;
			slow = slow.next;
		}

		return false;
	}

	/**
	 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
	 * <p>
	 * 示例:
	 * <p>
	 * 输入:
	 * [
	 *   1->4->5,
	 *   1->3->4,
	 *   2->6
	 * ]
	 * 输出: 1->1->2->3->4->4->5->6
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
	 *
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		if (lists.length == 1) {
			return lists[0];
		}

		ListNode head = null;

		int i = 1;
		ListNode aNode = lists[0];
		ListNode bNode = null;

		while (i < lists.length) {
			bNode = lists[i];

			while (aNode != null && bNode != null) {
				if (aNode.val <= bNode.val) {
					if (head == null) {
						head = aNode;
					} else {
						head.next = aNode;
					}

					aNode = aNode.next;
				} else {
					if (head == null) {
						head = bNode;
					} else {
						head.next = bNode;
					}

					bNode = bNode.next;
				}
			}

			if (aNode != null) {
				head.next = aNode;
			}

			if (bNode != null) {
				head.next = bNode;
			}

			i++;
			aNode = head;
		}

		return aNode;
	}

	/**
	 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
	 * 说明：每次只能向下或者向右移动一步。
	 * 示例:
	 * 输入:
	 * [
	 * [1,3,1],
	 * [1,5,1],
	 * [4,2,1]
	 * ]
	 * 输出: 7
	 * 解释: 因为路径 1→3→1→1→1 的总和最小。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param grid
	 * @return
	 */
	public static int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}

		int[][] dp = new int[grid.length][grid[0].length];
		dp[0][0] = grid[0][0];

		for (int i = 1; i < grid.length; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}

		for (int j = 1; j < grid[0].length; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}

		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	/**
	 * 给定不同面额的硬币 coins 和一个总金额 amount。
	 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
	 * 示例 1:
	 * 输入: coins = [1, 2, 5], amount = 11
	 * 输出: 3
	 * 解释: 11 = 5 + 5 + 1
	 * 示例 2:
	 * 输入: coins = [2], amount = 3
	 * 输出: -1
	 * 说明:
	 * 你可以认为每种硬币的数量是无限的。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/coin-change
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param coins
	 * @param amount
	 * @return
	 */
	private static int minValue = Integer.MAX_VALUE;

	public static int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0) {
			return -1;
		}

		if (coins.length == 1 && coins[0] == amount) {
			return 1;
		}

		Arrays.sort(coins);
		recursion(coins, amount, 0, coins.length - 1);
		return minValue == Integer.MAX_VALUE ? -1 : minValue;
	}

	private static void recursion(int[] coins, int amount, int count, int index) {
		if (index < 0 || count + amount / coins[index] >= minValue) {
			return;
		}

		if (amount % coins[index] == 0) {
			minValue = Math.min(minValue, count + amount / coins[index]);
		}

		for (int i = amount / coins[index]; i >= 0; i--) {
			recursion(coins, amount - (i * coins[index]), count + i, index - 1);
		}
	}

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}

		int max = 0;
		int min = prices[0];

		for (int i = 1; i < prices.length; i++) {
			max = Math.max(prices[i] - min, max);
			min = Math.min(prices[i], min);
		}

		return max;
	}

	/**
	 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
	 * 示例 1:
	 * 输入: [2,3,-2,4]
	 * 输出: 6
	 * 解释: 子数组 [2,3] 有最大乘积 6。
	 * 示例 2:
	 * 输入: [-2,0,-1]
	 * 输出: 0
	 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @return
	 */
	public static int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE;
		int a = 1;
		int b = 1;

		for (int i = 0; i < nums.length; i++) {
			int aa = a * nums[i];
			int bb = b * nums[i];

			a = Math.max(nums[i], Math.max(aa, bb));
			b = Math.min(nums[i], Math.min(aa, bb));

			max = Math.max(max, a);
		}

		return max;
	}

	/**
	 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
	 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
	 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
	 * 示例 1:
	 * 输入: [7,1,5,3,6,4]
	 * 输出: 7
	 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
	 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
	 * 示例 2:
	 * 输入: [1,2,3,4,5]
	 * 输出: 4
	 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
	 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
	 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
	 * 示例 3:
	 * 输入: [7,6,4,3,1]
	 * 输出: 0
	 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param prices
	 * @return
	 */
	public static int maxProfit1(int[] prices) {
		int sum = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				sum += (prices[i] - prices[i - 1]);
			}
		}

		return sum;
	}

	/**
	 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
	 * 示例:
	 * 输入:
	 * 1 0 1 0 0
	 * 1 0 1 1 1
	 * 1 1 1 1 1
	 * 1 0 0 1 0
	 * 输出: 4
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/maximal-square
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param matrix
	 * @return
	 */
	public static int maximalSquare(char[][] matrix) {
		return 0;
	}

	/**
	 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	 * 示例:
	 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
	 * 输出: 6
	 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/maximum-subarray
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param nums
	 * @return
	 */
	public static int maxSubArray(int[] nums) {
		if (nums == null) {
			return 0;
		}

		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > 0) {
				nums[i] += nums[i - 1];
			}

			max = Math.max(max, nums[i]);
		}

		return max;
	}

	/**
	 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
	 * 例如，给定三角形：
	 * [
	 * [2],
	 * [3,4],
	 * [6,5,7],
	 * [4,1,8,3]
	 * ]
	 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/triangle
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {
		return 0;
	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap();

		int temp = 0;
		for (int i = 0; i < nums.length; i++) {
			temp = target - nums[i];

			if (map.containsKey(temp)) {
				return new int[]{i, map.get(temp)};
			}

			map.put(nums[i], i);
		}

		return null;
	}

	public boolean isSubsequence(String s, String t) {
		int k = 0;
		boolean isFound = false;

		for (int i = 0; i < s.length(); i++) {
			isFound = false;

			for (int j = k; j < t.length(); j++) {
				if (s.charAt(i) == t.charAt(j)) {
					k = j + 1;
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String s = "leeeeetcode";
		String t =	"yyyyylyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyytyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyycyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyoyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyydyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";

		System.out.println(new ArrayTest().isSubsequence(s, t));

//		List<List<Integer>> triangle = new ArrayList<>();
//		List<Integer> a = new ArrayList<>();
//		a.add(2);
//		triangle.add(a);
//
//		a = new ArrayList<>();
//		a.add(3);
//		a.add(4);
//		triangle.add(a);
//
//		a = new ArrayList<>();
//		a.add(6);
//		a.add(5);
//		a.add(7);
//		triangle.add(a);
//
//		a = new ArrayList<>();
//		a.add(4);
//		a.add(1);
//		a.add(8);
//		a.add(3);
//		triangle.add(a);
//
//		System.out.println(minimumTotal(triangle));

		//		int[] nums = new int[] { -2, -1};
		//		System.out.println(maxSubArray(nums));
		//		int[] nums = new int[] { 7, 1, 5, 3, 6, 4 };
		//		System.out.println(maxProfit1(nums));

		//		int[] coins = new int[]{1, 2, 5};
		//		int amount = 11;
		//		System.out.println(coinChange(coins, amount));

		//		int[][] grid = new int[3][3];
		//		grid[0] = new int[] { 1, 3, 1 };
		//		grid[1] = new int[] { 1, 5, 1 };
		//		grid[2] = new int[] { 4, 2, 1 };
		//
		//		System.out.println(minPathSum(grid));

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
		//		int[] nums = new int[] { 3, 2, 3 };
		//		System.out.println(majorityElement(nums));

		//        int[] nums1 = new int[]{1, 2, 0};// 3
		//        int[] nums2 = new int[]{3, 4, -1, 1};// 2
		//        int[] nums3 = new int[]{7, 8, 9, 11, 12};// 1
		//
		//        System.out.println(firstMissingPositive(nums1));
		//        System.out.println(firstMissingPositive(nums2));
		//        System.out.println(firstMissingPositive(nums3));

		// 3,2,0,-4
		//        ListNode node0 = new ListNode(3);
		//        ListNode node1 = new ListNode(2);
		//        ListNode node2 = new ListNode(0);
		//        ListNode node3 = new ListNode(-4);
		//
		//        node0.next = node1;
		//        node1.next = node2;
		//        node2.next = node3;
		//        node3.next = node1;
		//
		//        System.out.println(hasCycle(node0));
	}
}
