package com.bendanplus.algorithm.程序员代码面试指南.第8章数组与矩阵;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CD9_未排序数组中累加和为给定值的最长子数组长度_355 {
	public static int maxLength(int[] arr, int k) {

		if (arr == null || arr.length == 0) {
			return 0;
		}
		//map中的key用来记录累加和，对应的value是这个累加和第一次出现的下标
		Map<Integer, Integer> map = new HashMap<>();
		//这个很关键的，当数组从0开始的累加和是k时就会用到，所以一定要保证<0,-1>已经在map中了，这个当前i个和等于k时就用到了
		//当{1，2，3} k = 3时就可以体现出来，好好体会！
		map.put(0, -1);//累加和为0，最早出现的位置是-1；即一个元素都没出现时，累加和为0；
		//sum用来记录数组前i项的和，length用来记录最后的答案
		int sum = 0, len = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			//看看map中是否已经存过sum-k这个累加和了，有的话从那个值到目前的i就是length了
			if (map.containsKey(sum - k)) len = Math.max(len, i - map.get(sum - k));
			if (!map.containsKey(sum)) // TODO 只记录最早出现 和为sum的位置
				map.put(sum, i);
		}
		return len;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		int k = scanner.nextInt();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scanner.nextInt();
		}
		int ans = maxLength(arr, k);
		System.out.println(ans);
	}

}
