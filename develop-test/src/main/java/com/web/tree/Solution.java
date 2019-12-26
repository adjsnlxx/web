package com.web.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// 1.4142136573791504
		System.out.println(Math.sqrt(2));
		System.out.println(new Solution().calcSqrt(2));
		//		TreeNode root = new TreeNode(1);
		//
		//		TreeNode left = new TreeNode(2);
		//		root.left = left;
		//
		//		TreeNode right = new TreeNode(3);
		//		root.right = right;
		//
		//		List<Integer> result = new Solution().PrintFromTopToBottom(root);
		//		System.out.println(Arrays.toString(result.toArray()));
	}

	/**
	 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
	 *
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		if (root == null) {
			return list;
		}

		ArrayList<TreeNode> queue = new ArrayList<>();
		queue.add(root);

		while (queue.size() > 0) {
			TreeNode temp = queue.remove(0);
			list.add(temp.val);

			if (temp.left != null) {
				queue.add(temp.left);
			}

			if (temp.right != null) {
				queue.add(temp.right);
			}
		}

		return list;
	}

	public double calcSqrt(double x) {
		double i = 0;
		double j = x;
		double mid = 0d;
		while (i < j) {
			mid = (i + j) / 2;
			if (Math.abs(mid * mid - x) < 0.000001) {
				break;
			} else if (mid * mid > x) {
				j = mid;
			} else if (mid * mid < x) {
				i = mid;
			}
		}

		String s = String.format("%.6f",mid);
		return Double.parseDouble(s);
	}
}
