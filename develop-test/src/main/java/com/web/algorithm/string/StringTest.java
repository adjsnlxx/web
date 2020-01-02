package com.web.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringTest {

	public static void main(String[] args) {
		//		char[] s = "hello".toCharArray();
		//		reverseString(s);
		//		System.out.println(Arrays.toString(s));

		String s = "the sky is blue";
		System.out.println(reverseWords(s));
	}

	/**
	 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
	 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
	 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
	 *  
	 * 示例 1：
	 * 输入：["h","e","l","l","o"]
	 * 输出：["o","l","l","e","h"]
	 * 示例 2：
	 * 输入：["H","a","n","n","a","h"]
	 * 输出：["h","a","n","n","a","H"]
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/reverse-string
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param s
	 */
	public static void reverseString(char[] s) {
		if (s == null || s.length < 2) {
			return;
		}

		int startIndex = 0;
		int endIndex = s.length - 1;
		char temp;
		while (startIndex < endIndex) {
			temp = s[startIndex];
			s[startIndex] = s[endIndex];
			s[endIndex] = temp;

			startIndex++;
			endIndex--;
		}
	}

	/**
	 * 给定一个字符串，逐个翻转字符串中的每个单词。
	 *  
	 * 示例 1：
	 * 输入: "the sky is blue"
	 * 输出: "blue is sky the"
	 * 示例 2：
	 * 输入: "  hello world!  "
	 * 输出: "world! hello"
	 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
	 * 示例 3：
	 * 输入: "a good   example"
	 * 输出: "example good a"
	 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
	 *  
	 * 说明：
	 * 无空格字符构成一个单词。
	 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
	 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		List<String> list = new ArrayList<>();

		int c;
		int preIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			c = (int) s.charAt(i);

			if (c == 32 || c == 160) {
				if (preIndex == i) {
					preIndex = i + 1;
					continue;
				}

				String subStr = s.substring(preIndex, i);
				list.add(subStr);

				preIndex = i + 1;
			} else if (i == s.length() - 1) {
				String subStr = s.substring(preIndex);
				list.add(subStr);
			}
		}

		int startIndex = 0;
		int endIndex = list.size() - 1;
		String tempStr = null;
		while (startIndex < endIndex) {
			tempStr = list.get(startIndex);
			list.set(startIndex, list.get(endIndex));
			list.set(endIndex, tempStr);

			startIndex++;
			endIndex--;
		}

		StringBuilder msg = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			msg.append(list.get(i));

			if (i != list.size() - 1) {
				msg.append(" ");
			}
		}

		return msg.toString();
	}
}
