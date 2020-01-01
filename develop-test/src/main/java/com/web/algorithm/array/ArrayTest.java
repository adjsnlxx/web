package com.web.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTest {

    public static void main(String[] args) {
        //		int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
        //		long curTime = System.currentTimeMillis();
        //		List<List<Integer>> list = threeSum(nums);
        //		System.out.println("interval time = " + (System.currentTimeMillis() - curTime));
        //
        //		List<Integer> temp = null;
        //		while (list.size() > 0 && (temp = list.remove(0)) != null) {
        //			System.out.println(Arrays.toString(temp.toArray()));
        //		}

        //		int[] nums = new int[]{2,2,1,1,1,2,2};
//		int[] nums = new int[] { 3, 2, 3 };
//		System.out.println(majorityElement(nums));

//        int[] nums1 = new int[]{1, 2, 0};// 3
//        int[] nums2 = new int[]{3, 4, -1, 1};// 2
//        int[] nums3 = new int[]{7, 8, 9, 11, 12};// 1
//
//        System.out.println(firstMissingPositive(nums1));
//        System.out.println(firstMissingPositive(nums2));
//        System.out.println(firstMissingPositive(nums3));

        // 3,2,0,-4
        ListNode node0 = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        System.out.println(hasCycle(node0));
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();

        for (int index1 = 0; index1 < nums.length - 2; index1++) {
            if (nums[index1] > 0) {
                break;
            }

            if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
                continue;
            }

            for (int index2 = index1 + 1, index3 = nums.length - 1; index2 < index3; ) {
                int sum = nums[index1] + nums[index2] + nums[index3];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[index1], nums[index2], nums[index3]));

                    while (index2 < index3 && nums[index2] == nums[index3]) {
                        index2++;
                    }

                    while (index2 < index3 && nums[index3] == nums[index3 - 1]) {
                        index3--;
                    }

                    index2++;
                    index3--;
                } else if (sum < 0) {
                    index2++;
                } else {
                    index3--;
                }
            }
        }

        return list;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        if (nums == null) {
            return -1;
        }

        Arrays.sort(nums);

        int i = 0;
        int j = 0;
        for (i = 0, j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j]) {
                if ((j - i) > (nums.length / 2)) {
                    return nums[i];
                } else {
                    i = j;
                }
            }
        }

        if (i < j) {
            if ((j - i) > (nums.length / 2)) {
                return nums[i];
            }
        }

        return -1;
    }

    /**
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,0]
     * 输出: 3
     * <p>
     * 示例 2:
     * <p>
     * 输入: [3,4,-1,1]
     * 输出: 2
     * <p>
     * 示例 3:
     * <p>
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * 说明:
     * <p>
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-missing-positive
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 1;
        }

        int minValue = nums[0];
        int maxValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            }

            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
        }

        boolean found = false;
        int i = 1;
        for (; i <= maxValue; i++) {
            found = false;
            for (int j = 0; j < nums.length; j++) {
                if (i == nums[j]) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return i;
            }
        }

        if (i > maxValue) {
            return i;
        }

        return 0;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            if (slow != null && slow.next != null) {
                if (slow.next == fast.next.next) {
                    return true;
                }
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        return false;
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        ListNode head = null;


        int i = 1;
        ListNode aNode = lists[0];
        ListNode bNode = null;

        while (i < lists.length) {
            bNode = lists[i];

            while (aNode != null && bNode != null) {
                if (aNode.val <= bNode.val) {
                    if (head == null) {
                        head = aNode;
                    }else {
                        head.next = aNode;
                    }

                    aNode = aNode.next;
                }else {
                    if (head == null) {
                        head = bNode;
                    }else {
                        head.next = bNode;
                    }

                    bNode = bNode.next;
                }
            }

            if (aNode != null) {
                head.next = aNode;
            }

            if (bNode != null) {
                head.next = bNode;
            }

            i++;
            aNode = head;
        }

        return aNode;
    }
}
