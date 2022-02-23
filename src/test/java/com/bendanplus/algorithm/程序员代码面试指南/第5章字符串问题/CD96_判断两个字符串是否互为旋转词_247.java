package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 如果一个字符串为str，把字符串的前面任意部分挪到后面形成的字符串叫str的旋转词。
 * 比如str=“12345”，str的旋转串有“12345”、“45123”等等。给定两个字符串，判断是否为旋转词。
 */
public class CD96_判断两个字符串是否互为旋转词_247 {
	public static boolean isRotation(String a, String b) {
		//长度不同直接返回false
		if (a == null || b == null || a.length() != b.length()) return false;

		String b2 = b + b;
		//看b2是否包含a
		return b2.contains(a);
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		String line = null;
//		while ((line = bf.readLine()) != null) {
//			String str1 = bf.readLine();
//			String str2 = bf.readLine();
//			System.out.println(isRotation(str1, str2) ? "YES" : "NO");
//		}

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String str1 = sc.next();
		String str2 = sc.next();
		//Rotation回旋
		System.out.println(isRotation(str1, str2) ? "YES" : "NO");
	}
}
