package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 给定数组arr，arr中所有的值都为 正整数且不重复。
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 */

/**
 * 类似 完全背包问题
 * 动态规划:
 * dp[i][j]: 前i个货币组成价值j所需要得到最少货币数量
 * 集合划分：(使用第i种货币0张 | 1张 | 2张 | ....)
 * (dp[i-1][j] | dp[i-1][j-arr[i]] + 1 | dp[i-1][j-arr[i-2]] + 2 | ...)
 */
public class CD12_换钱的最少货币数_192 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int length = Integer.parseInt(info[0]);
		int aim = Integer.parseInt(info[1]);
		String[] stringArr = br.readLine().split(" ");
		int[] arr = new int[length];
		for (int i = 0; i < length; i++)
			arr[i] = Integer.parseInt(stringArr[i]);

		int minAmount = minCoins(arr, aim);
		System.out.println(minAmount);
	}

	public static int getMinAmount(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		// 暴力递归
		//return process1(arr, 0, aim);

		//记忆化搜索
        /*
        int[][] cache = new int[arr.length+1][aim+1];
        for(int i =0;i<arr.length+1;i++){
            for(int j=0;j<aim+1;j++) {
                cache[i][j] = Integer.MAX_VALUE;
            }
        }
        return process2(arr,0,aim,cache);
        */
		//动态规划
		//return process3(arr,aim);
		//动态规划空间压缩
		return process4(arr, aim);
	}


	//代码指南里的解法1
	//dp[i][j]: 前i个货币组成价值j所需要得到最少货币数量
	//dp[i][j] = min{dp[i-1][j],dp[i][j-arr[i]]+1}
	//TODO 需要优化！！
	public static int minCoins(int[] arr, int aim) {
		if (arr == null || arr.length <= 0 || aim < 0) return -1;
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		int dp[][] = new int[n][aim + 1];

		//初始化第0行所有值 dp[0][j]-->arr[0] 组成 j
		for (int j = 1; j <= aim; j++) {
			dp[0][j] = max;
			if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) dp[0][j] = dp[0][j - arr[0]] + 1;
		}
		int left = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= aim; j++) {
				left = max;
				if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) left = dp[i][j - arr[i]] + 1;
				dp[i][j] = Math.min(dp[i - 1][j], left);
			}
		}
		return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
	}

	//动态规划求解：
	// dp[x]表示凑出x金额时的最优解，外层循环是各个面额的钱coins[i]，
	// 内层循环就是目标金额j。可以知道，在遍历不同面额coins[i]时，
	// 如果我们选择coins[i]这个面额，那我们就是在dp[j-coins[i]]的最优解上加一个coins[i]面额的钱，
	// 否则就还是之前的值dp[j]不变。于是写出状态转移方程：dp[j] = min(dp[j], dp[j - coins[i]] + 1)
	public static int process4(int[] arr, int aim) {
		int[] dp = new int[aim + 1];
		for (int j = 1; j < aim + 1; j++) {
			dp[j] = -1;
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr[i]; j < aim + 1; j++) {
				//j=0不用变
				if (dp[j - arr[i]] != -1) {
					if (dp[j] != -1) {
						dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
					} else {
						dp[j] = dp[j - arr[i]] + 1;
					}
				}
			}
		}
		return dp[aim];
	}

	public static int process3(int[] arr, int aim) {
		int[][] dp = new int[arr.length + 1][aim + 1];
		//basecase：index到达length的时候，只有你的aim是0才能成功
		for (int j = 1; j < aim + 1; j++) {
			dp[arr.length][j] = -1;
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j < aim + 1; j++) {
				//如果有j-arr[i] >=0,就选择dp[i][j-arr[i]]和dp[i+1][j]中除了-1以外最小的
				int count = j - arr[i] < 0 ? -1 : dp[i][j - arr[i]];
				count = count == -1 ? -1 : count + 1;
				//res是-1，答案就是count，如果res不是-1，count是-1，答案就是res，都不是-1就是最小的哪个
				dp[i][j] = dp[i + 1][j] == -1 ? count : count == -1 ? dp[i + 1][j] : Math.min(dp[i + 1][j], count);
			}
		}
		return dp[0][aim];
	}

	public static int process2(int[] arr, int index, int rest, int[][] cache) {
		if (cache[index][rest] != Integer.MAX_VALUE) {
			return cache[index][rest];
		}
		if (index == arr.length) {
			cache[index][rest] = rest == 0 ? 0 : -1;
			return cache[index][rest];
		}
		int res = -1;
		for (int i = 0; (rest - i * arr[index]) >= 0; i++) {
			int count = process2(arr, index + 1, rest - i * arr[index], cache);
			count = count == -1 ? -1 : count + i;
			res = res == -1 ? count : count == -1 ? res : Math.min(res, count);
		}
		cache[index][rest] = res;
		return cache[index][rest];
	}

	public static int process1(int[] arr, int index, int rest) {
		if (index == arr.length) {
			return rest == 0 ? 0 : -1;
		}
		int res = -1;
		// i:这个index用几张
		for (int i = 0; (rest - i * arr[index]) >= 0; i++) {
			int count = process1(arr, index + 1, rest - i * arr[index]);
			count = count == -1 ? -1 : count + i;
			//res是-1，答案就是count，如果res不是-1，count是-1，答案就是res，都不是-1就是最小的哪个
			res = res == -1 ? count : count == -1 ? res : Math.min(res, count);
		}
		return res;
	}
}
