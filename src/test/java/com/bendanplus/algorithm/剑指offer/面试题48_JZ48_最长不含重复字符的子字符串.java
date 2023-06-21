package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不含重复字符的子字符串
 * 测试地址:
 * https://www.nowcoder.com/practice/fafbd351003a499f9a80c3f641295f2a?tpId=101&tqId=33199&tPage=2&rp=2&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
@Slf4j
public class 面试题48_JZ48_最长不含重复字符的子字符串 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(in.nextInt());
		}
		final int i = longestSubstringWithoutDuo(sb.toString());
		log.info("结果等于 {}", i);
	}

	private static int longestSubstringWithoutDuo(String str) {
		if (str == null || str.length() == 0) return 0;
		//dp[i] 代表以i结尾 从0到i 最长不含重复字符的子字符串 的长度
		int[] dp = new int[str.length()];
		dp[0] = 1;
		int maxDp = 1;
		for (int dpIndex = 1; dpIndex < str.length(); dpIndex++) {
			int i = dpIndex - 1;
			//dpIndex - dp[dpIndex - 1] 代表  以dpIndex-1结尾最长不含重复字符子串 子串的起始位置 从dpIndex-1结束位置 从后往前遍历 --> 直到起始为止dpIndex - dp[dpIndex - 1]
			for (; i >= dpIndex - dp[dpIndex - 1]; i--)
				//如果相等说明 到达该点就是dp[i]的长度
				if (str.charAt(dpIndex) == str.charAt(i)) break;

			//两种情况:1.没有与str.charAt(dpIndex)相同的字符 2.有与str.charAt(dpIndex)相同的字符
			//从dpIndex开始往前遍历最长不含重复字符的子字符串 的长度
			dp[dpIndex] = dpIndex - i;
			maxDp = dp[dpIndex] > maxDp ? dp[dpIndex] : maxDp;
		}
		return maxDp;
	}
}
