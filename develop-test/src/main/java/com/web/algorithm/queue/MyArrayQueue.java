package com.web.algorithm.queue;

/**
 * 用数组实现一个顺序队列
 */
public class MyArrayQueue {

    private int addIndex;

    private int[] data;

    public MyArrayQueue(int count) {
        addIndex = -1;
        data = new int[count];
    }

    public void add(int value) {
        if (addIndex == data.length - 1) {
            return;
        }

        data[++addIndex] = value;
    }

    public int remove() {
        if (addIndex == -1){
            return -1;
        }

        int headValue = data[0];
        int[] newData = new int[data.length];
        for (int i = 1; i <= addIndex; i++) {
            newData[i - 1] = data[i];
        }

        data = newData;
        addIndex--;

        return headValue;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("[");

        for (int i = 0; i <= addIndex; i++) {
            msg.append(data[i]);

            if (i != addIndex) {
                msg.append(",");
            }
        }

        msg.append("]");
        return msg.toString();
    }

    public static void main(String[] args) {
        MyArrayQueue queue = new MyArrayQueue(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        queue.remove();
        queue.add(4);

        System.out.println(queue.toString());
    }
}
