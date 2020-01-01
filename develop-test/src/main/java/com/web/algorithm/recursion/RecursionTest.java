package com.web.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionTest {

    public static void main(String[] args) {
//        System.out.println(calcFibonacciSequence(8));
//        System.out.println(calcFactorial(3));

//        int[] nums = new int[]{1, 2, 3};
//        List<List<Integer>> list = new ArrayList<>();
//        List<Integer> temp = new ArrayList<>();
//        list = findArrange(list, temp, nums);
//
//        for (int i = 0; i < list.size(); i++) {
//            temp = list.get(i);
//
//            System.out.println(Arrays.toString(temp.toArray()));
//        }

        System.out.println(climbStairs(3));
    }

    /**
     * 编程实现斐波那契数列求值 f(n)=f(n-1)+f(n-2)
     *
     * @return
     */
    public static int calcFibonacciSequence(int n) {
        // 终结条件
        if (n == 0 || n == 1) {
            return 1;
        }

        // 递归公式
        return calcFibonacciSequence(n - 1) + calcFibonacciSequence(n - 2);
    }

    /**
     * 编程实现求阶乘 n!
     *
     * @param n
     * @return
     */
    public static int calcFactorial(int n) {
        // 终结条件
        if (n == 1) {
            return 1;
        }

        //  递归公式 f(n) = n * f(n - 1)
        return n * calcFactorial(n - 1);
    }

    /**
     * 编程实现一组数据集合的全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findArrange(List<List<Integer>> list, List<Integer> temp, int[] nums) {
        // 终结条件
        if (temp.size() == nums.length) {
            list.add(new ArrayList<>(temp));
            return list;
        }

        // 递归公式
        else {
            for (int i = 0; i < nums.length; i++) {
                if (temp.contains(nums[i])) {
                    continue;
                }

                temp.add(nums[i]);
                findArrange(list, temp, nums);

                temp.remove(temp.size() - 1);
            }
        }

        return list;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
       if (n <= 2) {
           return n;
       }

       int a = 1;
       int b = 2;
       int temp = 0;
       for (int i = 2; i < n; i++) {
           temp = a + b;

           a = b;
           b = temp;
       }

       return temp;
    }
}
