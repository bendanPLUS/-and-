package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.util.Scanner;

public class CD92_跳跃游戏_235 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		int jump = 0;
		int curr = 0;
		int next = 0;
		for (int i = 0; i < n; i++) {
			if (curr < i) {
				jump++;
				curr = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		System.out.println("结果---->" + jump);
	}
}
