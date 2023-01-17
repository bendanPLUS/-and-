package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CD33_最长公共子串_213 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		System.out.println(maxCommonSub2(s1, s2));
	}

	public int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		//初始化 第一行 和 第一列
		for (int j = 0; j < str2.length; j++)
			if (str1[0] == str2[j]) dp[0][j] = 1;
		for (int i = 1; i < str1.length; i++)
			if (str1[i] == str2[0]) dp[i][0] = 1;

		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		return dp;
	}

	public String lcst2(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) return null;

		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		//行 列
		int row = 0;
		int col = str2.length() - 1;
		//记录当前长度值
		int max = 0;
		//记录最大值结尾位置
		int end = 0;

		while (row < chs1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < chs1.length && j < chs2.length) {
				if (chs1[i] != chs2[j]) len = 0;
				else len++;
				if (len > max) {
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if (col > 0) col--;
			else row++;
		}
		return str1.substring(end - max + 1, end + 1);
	}

	public static String maxCommonSub1(String s1, String s2) {
		//求dp
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return "";
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[][] dp = new int[str1.length][str2.length];
		for (int i = 0; i < str1.length; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : 0;
		}
		for (int i = 1; i < str2.length; i++) {
			dp[0][i] = str2[i] == str1[0] ? 1 : 0;
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		//根据dp求出公共字串
		int maxLen = 0;
		int index = 0;
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				if (dp[i][j] > maxLen) {
					maxLen = dp[i][j];
					index = i;
				}
			}
		}
		return s1.substring(index - maxLen + 1, index + 1);
	}

	public static String maxCommonSub2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return "";
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int row = 0;
		int col = str2.length - 1;
		int max = 0;
		int end = 0;
		while (row < str1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < str1.length && j < str2.length) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		if (max == 0) {
			return "-1";
		}
		return s1.substring(end - max + 1, end + 1);
	}

}
