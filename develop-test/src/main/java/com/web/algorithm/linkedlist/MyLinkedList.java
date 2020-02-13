package com.web.algorithm.linkedlist;

import com.web.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedList {

	// 头结点
	private MyNode head;

	// 尾结点
	private MyNode tail;

	public MyLinkedList() {

	}

	public MyNode getHead() {
		return head;
	}

	public MyNode getTail() {
		return tail;
	}

	public void add(int value) {
		MyNode node = new MyNode(value);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			MyNode temp = head;
			for (; temp != null; temp = temp.next) {
				if (temp.next == null) {
					temp.next = node;
					node.pre = temp;

					tail = node;
					break;
				}
			}
		}

	}

	public void remove(int value) {
		MyNode node = head;

		for (; node != null; node = node.next) {
			if (node != null && node.value == value) {
				if (node.pre == null) {
					head = node.next;
				} else {
					node.pre.next = node.next;
				}

				break;
			}
		}

	}

	/**
	 * 反转
	 *
	 * @return
	 */
	public void reverse(MyLinkedList reverseList, MyNode node) {
		if (node == null) {
			return;
		}

		reverse(reverseList, node.next);
		reverseList.add(node.value);
	}

	public MyNode reverse(MyNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		MyNode p = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return p;
	}




	/**
	 * 实现两个有序的链表合并为一个有序链表
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public MyLinkedList merge(MyLinkedList a, MyLinkedList b) {
		MyLinkedList list = new MyLinkedList();
		MyNode aNode = a.getHead();
		MyNode bNode = b.getHead();

		while (aNode != null && bNode != null) {
			if (aNode.value <= bNode.value) {
				list.add(aNode.value);
				aNode = aNode.next;
			} else {
				list.add(bNode.value);
				bNode = bNode.next;
			}
		}

		while (aNode != null) {
			list.add(aNode.value);
			aNode = aNode.next;
		}

		while (bNode != null) {
			list.add(bNode.value);
			bNode = bNode.next;
		}

		return list;
	}

	/**
	 * 实现求链表的中间结点
	 * 环的检测也可以用到这种方法。
	 * 就是用快慢指针，快的前进两步，慢的前进一步，等到快的指针到结尾时，慢的指针就到了中点。
	 *
	 * @return
	 */
	public MyNode findMiddleNode() {
		MyNode fast = head;
		MyNode slow = head;

		while (fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("{");
		for (MyNode node = head; node != null; node = node.next) {
			msg.append(node.value).append(",");
		}

		msg.deleteCharAt(msg.length() - 1);
		msg.append("}");

		return msg.toString();
	}

	class MyNode {

		private int value;

		private MyNode pre;

		private MyNode next;

		MyNode(int temp) {
			value = temp;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public MyNode getNext() {
			return next;
		}

		public void setNext(MyNode next) {
			this.next = next;
		}

		public MyNode getPre() {
			return pre;
		}

		public void setPre(MyNode pre) {
			this.pre = pre;
		}
	}

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		if (root == null)  {
			return result;
		}

		LinkedList<TreeNode> q = new LinkedList<>();
		q.addFirst(root);

		boolean toR = true;

		while (!q.isEmpty()) {
			int size = q.size();
			LinkedList<Integer> tmp = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				TreeNode temp = null;
				if (toR) {
					temp = q.removeFirst();
					if (temp.left != null) {
						q.addLast(temp.left);
					}

					if (temp.right != null) {
						q.addLast(temp.right);
					}
				} else {
					temp = q.removeLast();
					if (temp.right != null) {
						q.addFirst(temp.right);
					}

					if (temp.left != null) {
						q.addFirst(temp.left);
					}
				}

				tmp.addLast(temp.val);
			}

			result.add(tmp);
			toR = !toR;
		}

		return result;
	}

	public static int[][] merge(int[][] intervals) {
		List<int[]> res = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			return res.toArray(new int[0][]);
		}

		// Arrays.sort(intervals, (a, b) -> a[0] - b[0]);// a[0] - b[0]大于0就交换顺序
		// 根据二维数组第一个数字大小按每一行整体排序
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int i = 0;
		while (i < intervals.length) {
			int left = intervals[i][0];
			int right = intervals[i][1];
			// i不能到最后一行,所以要小于(数组的长度 - 1)
			// 判断所在行的right和下一行的left大小,对right重新进行赋最大值,之后再不断进行while循环判断
			while (i < intervals.length - 1 && right >= intervals[i + 1][0]) {
				i++;
				right = Math.max(right, intervals[i][1]);
			}
			res.add(new int[] { left, right });
			i++;
		}
		return res.toArray(new int[0][]);
	}


	public static boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}
		int[] s1map = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
		}

		for (int i = 0; i <= s2.length() - s1.length(); i++) {
			int[] s2map = new int[26];
			for (int j = 0; j < s1.length(); j++) {
				s2map[s2.charAt(i + j) - 'a']++;
			}

			if (matches(s1map, s2map)) {
				return true;
			}
		}
		return false;
	}
	public static boolean matches(int[] s1map, int[] s2map) {
		for (int i = 0; i < 26; i++) {
			if (s1map[i] != s2map[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int value = 197;

		System.out.println(Integer.toBinaryString(value));

//		TreeNode root = new TreeNode(1);
//		TreeNode node2 = new TreeNode(2);
//		TreeNode node3 = new TreeNode(3);
//
//		root.left = node2;
//		root.right = node3;
//
//		TreeNode node4 = new TreeNode(4);
//		node2.left = node4;
//
//		TreeNode node5 = new TreeNode(5);
//		node3.right = node5;
//
//		zigzagLevelOrder(root);

//		MyLinkedList a = new MyLinkedList();
//		a.add(1);
//		a.add(4);
//		a.add(5);
//
//		MyLinkedList b = new MyLinkedList();
//		b.add(3);
//		b.add(7);
//		b.add(9);
//
//		System.out.println(a.toString());
//
//		MyLinkedList reverseList = new MyLinkedList();
//		a.reverse(reverseList, a.getHead());
//		System.out.println(reverseList.toString());
//
//		MyLinkedList mergeList = a.merge(a, b);
//		System.out.println(mergeList.toString());
//
//		MyNode middleNode = mergeList.findMiddleNode();
//		System.out.println(middleNode.value);
	}
}
