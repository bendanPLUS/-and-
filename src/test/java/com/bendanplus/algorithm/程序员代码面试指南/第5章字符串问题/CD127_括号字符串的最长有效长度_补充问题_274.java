package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD127_括号字符串的最长有效长度_补充问题_274 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.print(MaxLength(str));
	}

	private static int MaxLength(String str) {
		if (StringUtils.isBlank(str)) return 0;
		char[] chars = str.toCharArray();
		int[] dp = new int[str.length()];
		dp[0] = 0;
		int max = -1;
		for (int i = 1; i < dp.length; i++) {
			if (chars[i] == ')') {
				//dp[i-1]匹配字符串的前一个位置
				int pre = i - dp[i - 1] - 1;
				if (pre >= 0 && chars[pre] == '(')
					dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0);
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}
}
