package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.util.Scanner;

public class CD131_找到字符串的最长无重复字符子串_284 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			max = Math.max(max, arr[i]);
		}
		System.out.println(getMaxSubLen(arr, max));
	}

	public static int getMaxSubLen2(int[] arr, int max) {
		if (arr == null || arr.length == 0) return 0;
		int[] map = new int[256];
		int pre = -1;
		int len = 0;
		for (int i = 0; i < 256; i++)
			map[i] = -1;
		for (int i = 0; i < arr.length; i++) {
			pre = Math.max(pre, map[arr[i]]);
			len = Math.max(len, i - pre);
			map[arr[i]] = i;
		}
		return len;
	}
	public static int getMaxSubLen(int[] arr, int max) {
		if (arr == null || arr.length == 0)
			return 0;
		//index表示遍历到的某个数，value表示这个字符最近一次出现的位置
		//map[arr[i]]表示之前的遍历中最近一次出现arr[i]的位置
		int[] map = new int[max+1];
		for (int i = 0; i < map.length; i++) {
			map[i] = -1;
		}
		//如果当前遍历到arr[i], 表示在必须以arr[i-1]字符结尾时，
		//最长无重复子串开始位置的前一个位置
		int pre = -1;
		int len = 0;
		int cur = 0;
		//假设arr[i]之前出现过的位置是a, 若a在pre左侧，则当前最长长度为i-pre;
		//若a在pre右侧，则当前最长长度为i-a
		for (int i = 0; i < arr.length; i++) {
			//既然要么-a，要么-pre，那就谁大谁当被减去的数吧
			pre = Math.max(pre, map[arr[i]]);
			cur = i-pre;
			len = Math.max(len, cur);
			map[arr[i]] = i;
		}
		return len;
	}
}
