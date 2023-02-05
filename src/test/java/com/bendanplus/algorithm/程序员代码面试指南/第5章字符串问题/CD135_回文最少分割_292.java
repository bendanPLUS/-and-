package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD135_回文最少分割_292 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().trim();
		System.out.print(minCut(str));
	}

	public static int minCut(String str) {
		if (str == null || "".equals(str)) return 0;
		char[] chars = str.toCharArray();
		//dp[i]代表以i起始位置 到 chars[chars.length-1] 成为回文串最少分割数
		int[] dp = new int[chars.length + 1];
		dp[chars.length] = -1;

		//p[i][j] 代表chars[i] 到 chars[j] 是否是回文串
		boolean[][] p = new boolean[chars.length][chars.length];

		for (int i = chars.length - 1; i >= 0; i--) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = i; j < chars.length; j++) {
				if (chars[i] == chars[j] && (j - i < 2 || p[i + 1][j - 1])) {
					p[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j - 1] + 1);
				}
			}
		}
		return dp[0];

	}
}