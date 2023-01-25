package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.util.*;

/**
 * 给定数组arr，设数组长度为n，arr中所有的值都为正整数且不重复。
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个整数aim，代表要找的钱数，求换钱的方法数有多少种。
 * 由于方法的种数比较大，所以要求输出对进行取模后的答案。
 */

public class CD19_换钱的方法数_196 {
	public static final int mod = 1_000_000_007;

	public static int process(int[] arr, int n, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) return 0;
		int[] dp = new int[aim + 1];
		for (int i = 0; arr[0] * i <= aim; i++)
			dp[arr[0] * i] = 1;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < aim + 1; j++)
				dp[j] += j - arr[i] > 0 ? dp[j - arr[i]] : 0;
		}
		return dp[aim];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int aim = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(process(arr, n, aim));
	}
}
