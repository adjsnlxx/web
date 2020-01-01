package com.web.algorithm.stack;

public class MyBrower {

    private MyArrayStack goStack;

    private MyArrayStack backStack;

    public MyBrower() {
        goStack = new MyArrayStack(3);
        backStack = new MyArrayStack(3);
    }

    public void go() {
        int oldValue = backStack.pop();
        if (oldValue != -1) {
            goStack.push(oldValue);
        }

        System.out.println("go:" + goStack.toString() + ", back:" + backStack.toString());
    }

    public void back() {
        int oldValue = goStack.pop();
        if (oldValue != -1) {
            backStack.push(oldValue);
        }

        System.out.println("go:" + goStack.toString() + ", back:" + backStack.toString());
    }

    public void play(int value) {
        goStack.push(value);
    }

    public static void main(String[] args) {
        MyBrower brower = new MyBrower();
        brower.play(1);
        brower.play(2);
        brower.play(3);

        brower.back();

        brower.go();
    }
}
