package com.bendanplus.algorithm.剑指offer;

public class 面试题14_JZ67剪绳子 {

	public int cutRope(int n) {
		if (n < 2) return 0;
		if (n == 2) return 1;
		if (n == 3) return 2;

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		int max = 0;
		for (int i = 4; i < dp.length; i++) {
			max = 0;
			for (int j = 1; j <= i / 2; j++) {
				int tmp = dp[j] * dp[i - j];
				max = Math.max(max, tmp);
			}
			dp[i] = max;
		}
		return dp[n];
	}
}
