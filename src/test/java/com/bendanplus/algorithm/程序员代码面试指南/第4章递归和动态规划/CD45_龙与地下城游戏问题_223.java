package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.util.Scanner;

public class CD45_龙与地下城游戏问题_223 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int aa = 0;
		// 注意 hasNext 和 hasNextLine 的区别
		while (in.hasNextInt()) { // 注意 while 处理多个 case
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] nums = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					nums[i][j] = in.nextInt();
				}
			}

			int[][] dp = new int[n + 1][m + 1];
			dp[n][m] = 1 - nums[n - 1][m - 1] <= 0 ? 1 : 1 - nums[n - 1][m - 1];
			for (int i = n - 1; i > 0; i--) {
				if (nums[i - 1][m - 1] > 0) {
					dp[i][m] = nums[i - 1][m - 1] - dp[i + 1][m] >= 0 ? 1 : dp[i + 1][m] - nums[i - 1][m - 1];
				} else if (nums[i - 1][m - 1] == 0) {
					dp[i][m] = dp[i + 1][m];
				} else {
					dp[i][m] = -nums[i - 1][m - 1] + dp[i + 1][m];
				}
			}

			for (int i = m - 1; i > 0; i--) {
				if (nums[i - 1][m - 1] > 0) {
					dp[n][i] = nums[n - 1][i - 1] - dp[n][i + 1] >= 0 ? 1 : dp[n][i + 1] - nums[n - 1][i - 1];
				} else if (nums[n - 1][i - 1] == 0) {
					dp[n][i] = dp[n][i + 1];
					;
				} else {
					dp[n][i] = -nums[n - 1][i - 1] + dp[n][i + 1];
				}
			}


			for (int i = n - 1; i > 0; i--) {
				for (int j = m - 1; j > 0; j--) {
					int a = Math.min(dp[i + 1][j], dp[i][j + 1]);
					if (nums[i - 1][j - 1] > 0) {
						dp[i][j] = nums[i - 1][j - 1] - a >= 0 ? 1 : a - nums[i - 1][j - 1];
					} else {
						dp[i][j] = nums[i - 1][j - 1] == 0 ? a : -nums[i - 1][j - 1] + a;
					}
				}
			}

			System.out.println(dp[1][1]);
		}
	}


	public int minHP1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 1;

		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row--][col--];
		//赋值dp矩阵 右下角的值
		dp[row][col] = m[row][col] > 0 ? 1 : 1 - m[row][col];

		//初始化最下面一行
		for (int j = col - 1; j >= 0; j--)
			dp[row][j] = Math.max(1, dp[row][j + 1] - m[row][j]);

		for (int i = row - 1; i >= 0; i--) {
			dp[i][col] = Math.max(1, dp[i][col + 1] - m[i][col]);
			for (int j = col - 1; j >= 0; j--) {
				int right = Math.max(1, dp[i][j + 1] - m[i][j]);
				int down = Math.max(1, dp[i + 1][j] - m[i][j]);
				dp[i][j] = Math.min(right, down);
			}
		}
		return dp[0][0];
	}
}
