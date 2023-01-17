package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CD43_最小编辑代价_217 {
	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) //可以等于""
			return 0;
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] shorts = chs1.length > chs2.length ? chs2 : chs1;
		char[] longs = chs1.length > chs2.length ? chs1 : chs2;
		int[] dp = new int[shorts.length + 1];

		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		for (int j = 0; j <= longs.length; j++)
			dp[j] = ic * j;
		//从左到右 从上到下
		for (int i = 1; i < longs.length + 1; i++) {
			int pre = dp[0];//相当于 dp[i-1][j-1]
			dp[0] = i * dc;
			for (int j = 1; j < shorts.length + 1; j++) {
				int tmp = dp[j];//tmp 相当于 dp[i-1][j]
				if (longs[i - 1] == shorts[j - 1]) dp[j] = pre;//dp[i][j]=dp[i-1][j-1];
				else dp[j] = pre + rc;//dp[i-1][j-1]+rc
				dp[j] = Math.min(dp[j], tmp + dc);
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				pre = tmp;//dp[j]没更新之前 相当与下一个值 dp[j+1]的dp[i-1][j-1]
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str1 = in.readLine().trim();
		String str2 = in.readLine().trim();
		String[] ops = in.readLine().split(" ");
		int ic = Integer.parseInt(ops[0]);
		int dc = Integer.parseInt(ops[1]);
		int rc = Integer.parseInt(ops[2]);
		System.out.println(minCost2(str1, str2, ic, dc, rc));
	}
}
