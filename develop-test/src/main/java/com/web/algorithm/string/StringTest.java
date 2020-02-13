package com.web.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringTest {

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

	/**
	 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
	 * '.' 匹配任意单个字符
	 * '*' 匹配零个或多个前面的那一个元素
	 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
	 * 说明:
	 * s 可能为空，且只包含从 a-z 的小写字母。
	 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
	 * 示例 1:
	 * 输入:
	 * s = "aa"
	 * p = "a"
	 * 输出: false
	 * 解释: "a" 无法匹配 "aa" 整个字符串。
	 * 示例 2:
	 * 输入:
	 * s = "aa"
	 * p = "a*"
	 * 输出: true
	 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
	 * 示例 3:
	 * 输入:
	 * s = "ab"
	 * p = ".*"
	 * 输出: true
	 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
	 * 示例 4:
	 * 输入:
	 * s = "aab"
	 * p = "c*a*b"
	 * 输出: true
	 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
	 * 示例 5:
	 * 输入:
	 * s = "mississippi"
	 * p = "mis*is*p*."
	 * 输出: false
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static boolean isMatch(String text, String pattern) {
		if (text == null || pattern == null) {
			return false;
		}

		if (pattern.isEmpty()) {
			return text.isEmpty();
		}

		boolean firstMatch = (text.length() > 0 && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern));
		} else {
			return firstMatch && isMatch(text.substring(1), pattern.substring(1));
		}
	}

	public static boolean isMatch1(String text, String pattern) {
		if (text == null || pattern == null || pattern.isEmpty() && text.length() > 0) {
			return false;
		}

		boolean[][] data = new boolean[text.length() + 1][pattern.length() + 1];
		return checkString(data, 0, 0, text, pattern);
	}

	public static boolean checkString(boolean[][] data, int i, int j, String text, String pattern) {
		if (data[i][j]) {
			return true;
		}

		boolean result = false;
		if (j == pattern.length()) {
			result = (i == text.length());
		} else {
			boolean firstMatch = (i < text.length() && text.charAt(i) == pattern.charAt(j)) || pattern.charAt(j) == '.';

			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				result = checkString(data, i, j + 2, text, pattern) || firstMatch && checkString(data, i + 1, j, text,
						pattern);
			} else {
				result = firstMatch && checkString(data, i + 1, j + 1, text, pattern);
			}
		}

		data[i][j] = result;
		return result;
	}

	/**
	 * 判断字符串交错
	 * 给定三个字符串a, b, c, 判断c是否可以通过字符串a, b的子串按顺序交错组成.
	 * 例如:
	 * a: "btdne" b: "yeac" c: "bytedance"
	 * 返回: True
	 * a: "bytece" b: "dan" c: "bytedance"
	 * 返回: True
	 * a: "bytec" b: "dan" c: "bytedance"
	 * 返回: False
	 * a: "byte" b: "danced" c: "bytedance"
	 * 返回: False
	 *
	 * @param s1
	 * @param i
	 * @param s2
	 * @param j
	 * @param res
	 * @param s3
	 * @return
	 */
	public boolean is_Interleave(String s1, int i, String s2, int j, String res, String s3) {
		if (res.equals(s3) && i == s1.length() && j == s2.length()) {
			return true;
		}

		boolean ans = false;
		if (i < s1.length()) {
			ans |= is_Interleave(s1, i + 1, s2, j, res + s1.charAt(i), s3);
		}

		if (j < s2.length()) {
			ans |= is_Interleave(s1, i, s2, j + 1, res + s2.charAt(j), s3);
		}

		return ans;
	}

	public boolean isInterleave(String s1, String s2, String s3) {
		return is_Interleave(s1, 0, s2, 0, "", s3);
	}

	public void printReverse() {
		String s = "abcdef";
		print(s, 0);
	}

	public void print(String s, int index) {
		if (s == null || index == s.length()) {
			return;
		}

		print(s, index + 1);
		System.out.println(s.charAt(index));
	}

	public int kthGrammar(int N, int K) {
		if (N == 1) {
			return 0;
		}

		if (K == 2) {
			return 1;
		}

		K = Math.round(K / 2f);

		return kthGrammar(N - 1, K);
	}

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(1));
//		System.out.println(new StringTest().kthGrammar(3, 4));
//		new StringTest().printReverse();

//		String s1 = "btdne";
//		String s2 = "yeac";
//		String s3 = "bytedance";
//
//		System.out.println(new StringTest().isInterleave(s1, s2, s3));

		//		char[] s = "hello".toCharArray();
		//		reverseString(s);
		//		System.out.println(Arrays.toString(s));

		//		String s = "the sky is blue";
		//		System.out.println(reverseWords(s));

		//		String text = "aab";
		//		String pattern = "c*a*b";
		//		System.out.println(isMatch1(text, pattern));
	}
}
