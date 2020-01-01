package com.web.algorithm.stack;

/**
 * 用数组实现一个顺序栈
 *
 * 特性：先进后出
 */
public class MyArrayStack {

    private int index;

    private int[] data;

    MyArrayStack(int count) {
        index = -1;
        data = new int[count];
    }

    public void push(int value){
        if (index >= data.length - 1) {
            return;
        }

        data[++index] = value;
    }

    public int pop() {
        if (index < 0) {
            return -1;
        }

        int oldData = data[index];
        data[index--] = 0;
        return oldData;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");

        for (int i = 0; i <= index; i++) {
            msg.append(data[i]);

            if (i != index) {
                msg.append(",");
            }
        }

        msg.append("]");
        return msg.toString();
    }

    public static void main(String[] args) {
        MyArrayStack stack = new MyArrayStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();

        System.out.println(stack.toString());
    }
}
