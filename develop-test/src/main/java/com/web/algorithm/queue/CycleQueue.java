package com.web.algorithm.queue;

/**
 * 实现一个循环队列
 */
public class CycleQueue {

    private int headIndex;
    private int tailIndex;

    private int[] data;

    public CycleQueue(int count) {
        data = new int[count];
    }

    public boolean enqueue(int value) {
        if ((tailIndex + 1) % data.length == headIndex) {
            return false;
        }

        data[tailIndex] = value;
        tailIndex = (tailIndex + 1) % data.length;
        return true;
    }

    public int dequeue() {
        if (headIndex == tailIndex) {
            return -1;
        }

        int oldValue = data[headIndex];
        data[headIndex] = 0;
        headIndex = (headIndex + 1) % data.length;
        return oldValue;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");

        for (int i = 0; i < data.length; i++) {
            msg.append(data[i]);

            if (i != data.length - 1) {
                msg.append(",");
            }
        }

        msg.append("]");
        return msg.toString();
    }

    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        queue.dequeue();
        queue.enqueue(4);

        queue.dequeue();

        System.out.println(queue.toString());
    }
}
