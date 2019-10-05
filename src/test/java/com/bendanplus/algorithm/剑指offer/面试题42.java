package com.bendanplus.algorithm.剑指offer;

/**
 * 连续子数组的最大和
 * 输入一个整型数组,数组中有整数也有负数,数组中的一个或者连续多个整数成为一个子数组.
 * 求所有子数组和的最大值.
 */
public class 面试题42 {
	public int FindGreatestSumOfSubArray(int[] array) {
		int maxSum = array[0];
		int curSum = array[0];
		for (int i = 1; i < array.length; i++) {
			curSum = curSum > 0 ? curSum + array[i] : array[i];
			maxSum = Math.max(curSum, maxSum);
		}
		return maxSum;
	}
}
