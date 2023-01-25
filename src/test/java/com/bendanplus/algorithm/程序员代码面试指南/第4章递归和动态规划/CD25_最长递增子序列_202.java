package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CD25_最长递增子序列_202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		int[] getdp2 = getdp2(arr);
	}

	public static int[] getdp2(int[] arr) {
		//dp[i] 以i为结尾的 最长递增子序列的长度
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}


	//获取最长递增子序列长度
	public int[] getdp(int[] arr) {
		//dp[i]表述以i结尾的最长递增子序列长度
		int dp[] = new int[arr.length];
		//初始化一些值
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++)
				if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
		}
		return dp;
	}


	public int[] generateLIS(int[] arr, int[] dp) {
		int len = 0;
		int index = 0;

		//找出dp中最大值 并标记长度len和位置index
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > len) {
				len = dp[i];
				index = i;
			}
		}

		int[] lis = new int[len];
		//lis的最后一位数 肯定是 arr[index]的值
		lis[--len] = arr[index];
		for (int i = index; i >= 0; i--) {
			if (arr[i] < arr[index] && dp[i] + 1 == dp[index]) {
				lis[--len] = arr[i];
				index = i;
			}
		}
		return lis;
	}


	public static int lengthOfLIS(int[] nums) {
		// 1.minTail[i]用来记录所有长度为i+1的递增序列中，末尾的最小值。可以用反正法证明minTail数组必然是一个单调递增数组。下面关键步骤是通过遍历nums数组逐渐的得到minTail
		// 2.dp[i] 记录以数组nums中第i个数字结尾的LIS的长度。
		// 3.通多上面两个数组求出最小LIS
		int[] minTail = new int[nums.length];//minTail数组真实长度就是LIS长度，不确定，所以创建了一个和nums等长的数组，初始值均为0。
		int[] dp = new int[nums.length];//数组dp长度等于nums长度
		int len = 0;//表示minTail目前用到的长度

		for (int l = 0; l < nums.length; l++) { // 随着数组的遍历不断地跟新minTail
			// 搜索第一个大于nums[l]的数的位置。
			int i = Arrays.binarySearch(minTail, 0, len, nums[l]);
			while (i >= 0) { //若查找到minTail中包含nums[l],接着二分查找直到找到第一个大于nums[l]的数
				i = Arrays.binarySearch(minTail, i + 1, len, nums[l]);
			}
			i = -i - 1;//得到大于nums[l]的第一个值的索引

			// 已有序列都小于num
			if (i == len) {
				len++;
			}
			// 替换掉第一个大于或等于nums[l]的数,也就是说长为i+1的递增子序列最小值可以是更小的nums[l]。如果数组中没有比num大的数则num添加到末尾。
			minTail[i] = nums[l];
			dp[l] = i + 1;
		}

		// 下面代码用来找到按照字母表排序最小的最长递增子序列
		int[] res = new int[len];
		int index = res.length - 1;
		int next = Integer.MAX_VALUE;
		for (int k = nums.length - 1; k >= 0; k--) {
			if (nums[k] <= next && dp[k] == index + 1) { //满足该条件求得的序列就是目标LIS。假设已知LIS最后一个数字（其实就是minTail中最后一个非0值
				//通过该判断求LIS前一个数值？（首先该条件为nums[k]是LIS倒数第二个数值的充分条件，但还需证明由该条件得到的LIS按字典排序最小）。假设除了k满足该条件，
				//还有i，j...满足，那么i，j...不可能在k之后（因为倒着遍历nums），所以推出num[i,j,...]必然大于nums[k]。绝不可能小于或者等于，否则可以推出dp[k]==index+2
				res[index] = nums[k];
				next = res[index];
				index--;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int val : res)
			sb.append(val).append(" ");
		System.out.println(sb.toString());

		return len;
	}
}
