package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class CD103_判断数组中所有的数字是否只出现一次_255 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");

		System.out.println(isUnique(arr) ? "YES" : "NO");
	}
	//TODO 堆排序处理这道题
	public static Boolean isUnique(String[] arr) {
		if (arr == null || arr.length == 0) return true;
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (set.add(arr[i]) == false) return false;
		}
		return true;
	}

}
