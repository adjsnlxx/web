package com.web.algorithm.linkedlist;

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

	public static void main(String[] args) {
		MyLinkedList a = new MyLinkedList();
		a.add(1);
		a.add(4);
		a.add(5);

		MyLinkedList b = new MyLinkedList();
		b.add(3);
		b.add(7);
		b.add(9);

		System.out.println(a.toString());

		MyLinkedList reverseList = new MyLinkedList();
		a.reverse(reverseList, a.getHead());
		System.out.println(reverseList.toString());

		MyLinkedList mergeList = a.merge(a, b);
		System.out.println(mergeList.toString());

		MyNode middleNode = mergeList.findMiddleNode();
		System.out.println(middleNode.value);
	}
}
