package com.web.algorithm.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackTest {

    public static void main(String[] args) {
//        String s = "(])";
//        System.out.println(isValid(s));

//        String s = "()(())";
//        System.out.println(longestValidParentheses(s));

//        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
//        String[] tokens1 = new String[]{"4", "13", "5", "/", "+"};
//        System.out.println(evalRPN(tokens));

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "()"
     * 输出: true
     * <p>
     * 示例 2:
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * <p>
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * <p>
     * 输入: "{[]}"
     * 输出: true
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        // '('，')'，'{'，'}'，'['，']'
        List<Character> left = new ArrayList<>();
        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);

            if (temp == '(' || temp == '{' || temp == '[') {
                left.add(temp);
            } else if (temp == ')' || temp == '}' || temp == ']') {
                if (left.isEmpty()) {
                    return false;
                }

                Character c = left.get(left.size() - 1);
                if ((c == '(' && temp == ')') || c == '{' && temp == '}' || c == '[' && temp == ']') {
                    left.remove(left.size() - 1);
                } else {
                    return false;
                }
            }
        }

        return left.isEmpty();
    }

    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     * <p>
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int maxSum = 0;
        List<Integer> list = new ArrayList();
        list.add(-1);

        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if (temp == '(') {
                list.add(i);
            } else {
                list.remove(list.size() - 1);

                if (list.isEmpty()) {
                    list.add(i);
                } else {
                    maxSum = Math.max(maxSum, i - list.get(list.size() - 1));
                }
            }
        }

        return maxSum;
    }

    /**
     * 根据逆波兰表示法，求表达式的值。
     * <p>
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * <p>
     * 说明：
     * <p>
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * 示例 1：
     * <p>
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: ((2 + 1) * 3) = 9
     * 示例 2：
     * <p>
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: (4 + (13 / 5)) = 6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
     *
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null) {
            return 0;
        }

        if (tokens.length == 1) {
            return Integer.valueOf(tokens[0]);
        }

        int result = 0;
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                Integer a = stack.remove(stack.size() - 1);
                Integer b = stack.remove(stack.size() - 1);

                if (tokens[i].equals("+")) {
                    result = b + a;
                } else if (tokens[i].equals("-")) {
                    result = b - a;
                } else if (tokens[i].equals("*")) {
                    result = b * a;
                } else if (tokens[i].equals("/")) {
                    result = b / a;
                }

                stack.add(result);
            } else {
                stack.add(Integer.valueOf(tokens[i]));
            }
        }

        return stack.get(stack.size() - 1);
    }

    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回滑动窗口中的最大值。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * <p>
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     *  
     * <p>
     * 提示：
     * <p>
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        int maxValue = 0;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i + k <= nums.length) {
                maxValue = nums[i];
                for (int j = i; j < (i + k); j++) {
                    if (nums[j] > maxValue) {
                        maxValue = nums[j];
                    }
                }

               result[index++] = maxValue;
            }
        }

        return result;
    }
}
