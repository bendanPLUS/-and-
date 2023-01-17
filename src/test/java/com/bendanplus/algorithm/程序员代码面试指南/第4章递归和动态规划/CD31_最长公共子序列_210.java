package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD31_最长公共子序列_210 {
	public static void main(String[] args) throws Exception {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str1 = br.readLine();
			String str2 = br.readLine();
			String result = lcse(str1, str2);
			System.out.println(result.equals("") ? -1 : result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		//1.处理dp边界 dp[0][0]
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		//1.处理dp边界 第一行所有元素
		for (int j = 1; j < str2.length; j++)
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		//1.处理dp边界 第一列所有元素
		for (int i = 1; i < str1.length; i++)
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);

		//遍历 从左到右 从上倒下
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
			}
		}
		return dp;
	}

	public static String lcse(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) return null;
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		//dp[i][j]的含义是 以chs1[i]chs2[j]结尾的 最长公共子序列的长度为dp[i][j]
		final int[][] dp = getdp(chs1, chs2);
		//m行n列 m*n
		int m = str1.length();
		int n = chs2.length;
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;

		while (index >= 0) {
			if (m > 0 && n > 0 && dp[m][n] >= dp[m - 1][n] && dp[m][n] >= dp[m][n - 1]) {
				res[index--] = chs1[m];
				m--;
				n--;
			}
			if (m > 0 && dp[m][n] == dp[m - 1][n]) m--;
			if (n > 0 && dp[m][n] == dp[m][n - 1]) n--;
		}
		return String.valueOf(res);
	}
}