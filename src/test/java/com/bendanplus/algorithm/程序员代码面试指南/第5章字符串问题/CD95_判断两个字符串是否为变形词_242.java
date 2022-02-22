package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.util.Scanner;

/**
 * 描述
 * 给定两个字符串str1和str2，如果str1和str2中出现的字符种类出现的一样且每种字符出现的次数也一样
 * ，那么str1和str2互为变形词。请判断str1和str2是否为变形词
 */
public class CD95_判断两个字符串是否为变形词_242 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String str1 = sc.next();
		String str2 = sc.next();
		if (isDeformation(str1, str2) == true) System.out.println("true");
		else System.out.println("false");
	}

	public static boolean isDeformation(String str1, String str2) {
		//如果长度不同直接返回false
		if (str1 == null || str2 == null || str1.length() != str2.length()) return false;

		//申请一个长度为256的数组
		int[] map = new int[256];
		//将str1各个字符进行计数   例如'a'编码值为97
		for (int i = 0; i < str1.length(); i++)
			map[str1.charAt(i)]++;

		for (int i = 0; i < str2.length(); i++) {
			map[str2.charAt(i)]--;
			if (map[str2.charAt(i)] <= -1) return false;
		}
		return true;
	}
}
