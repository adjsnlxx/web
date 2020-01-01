package com.web.algorithm.stack;

public class MyLinkedStack {

    private ListNode tail;

    MyLinkedStack() {
        tail = null;
    }

    class ListNode {
        private int value;

        private ListNode pre;

        ListNode(int value) {
            this.value = value;
        }
    }

    public void push(int value) {
        ListNode node = new ListNode(value);

        if (tail == null) {
            tail = node;
        } else {
            ListNode temp = tail;
            tail = node;
            node.pre = temp;
        }
    }

    public int pop() {
        if (tail == null) {
            return -1;
        } else {
            int oldValue = tail.value;
            ListNode pre = tail.pre;
            tail = pre;
            return oldValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");

        ListNode temp = tail;
        for (; temp != null; ) {
            msg.append(temp.value);

            temp = temp.pre;
            if (temp == null) {
                msg.append("]");
            } else {
                msg.append(",");
            }
        }

        return msg.toString();
    }

    public static void main(String[] args) {
        MyLinkedStack stack = new MyLinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

//        stack.pop();

        System.out.println(stack.toString());
    }
}
