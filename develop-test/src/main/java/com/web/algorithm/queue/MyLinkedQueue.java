package com.web.algorithm.queue;

/**
 * 用链表实现一个链式队列
 */
public class MyLinkedQueue {

    private ListNode head;

    public MyLinkedQueue() {

    }

    class ListNode {
        private int value;

        private ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public void add(int value) {
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
        } else {
            ListNode temp = head;
            while (temp != null) {
                if (temp.next == null) {
                    temp.next = node;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
    }

    public int remove() {
        if (head == null) {
            return -1;
        } else {
            int oldValue = head.value;
            ListNode temp = head.next;
            if (temp == null) {
                head = null;
            } else {
                head = temp;
            }

            return oldValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");

        ListNode temp = head;
        for (; temp != null; ) {
            msg.append(temp.value);

            temp = temp.next;
            if (temp != null) {
                msg.append(",");
            }
        }

        msg.append("]");
        return msg.toString();
    }

    public static void main(String[] args) {
        MyLinkedQueue queue = new MyLinkedQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        queue.remove();
        queue.add(4);

        System.out.println(queue.toString());
    }
}
