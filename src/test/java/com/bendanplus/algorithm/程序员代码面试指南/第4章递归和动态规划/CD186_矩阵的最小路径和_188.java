package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.util.Scanner;

/**
 * 描述
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
 * 路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 * 输入描述：
 * 第一行输入两个整数 n 和 m，表示矩阵的大小。
 * <p>
 * 接下来 n 行每行 m 个整数表示矩阵。
 */
public class CD186_矩阵的最小路径和_188 {
	public static int minPathSum(int[][] grid) {
		int[][] dp = new int[grid.length][grid[0].length];
		dp[0][0] = grid[0][0];      //起点位置
		//求第1列的值
		for (int i = 1; i < grid.length; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		//求第1行的值
		for (int i = 1; i < grid[0].length; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		//循环结束，最后的结果即为最小值
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}

		return dp[grid.length - 1][grid[0].length - 1];
	}

	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) return 0;
		int more = Math.max(m.length, m[0].length);
		int less = Math.min(m.length, m[0].length);
		boolean rowMore = more == m.length;
		int[] arr = new int[less];

		arr[0] = m[0][0];
		for (int i = 1; i < less; i++)
			arr[i] = arr[i - 1] + (rowMore ? m[0][i] : m[i][0]);
		for (int i = 1; i < more; i++) {
			arr[0] = arr[0] + (rowMore ? m[0][i] : m[i][0]);
			for (int j = 1; j < less; j++) {
				arr[j] = Math.min(arr[j], arr[j - 1]) + (rowMore ? m[i][j] : m[j][i]);
			}
		}
		return arr[less - 1];
	}

	public static int minPathSum3(int[][] grid) {
		return minPathSum2(grid);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		System.out.println(minPathSum2(arr));
	}
}
