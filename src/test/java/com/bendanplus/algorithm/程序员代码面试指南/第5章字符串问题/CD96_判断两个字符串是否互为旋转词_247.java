package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.bendanplus.algorithm.程序员代码面试指南.第9章其他题目.CD87_KMP算法_491.getIndexOf;
import static com.bendanplus.algorithm.程序员代码面试指南.第9章其他题目.CD87_KMP算法_491.getNextArray;

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
		//return b2.contains(a);
		return getIndexOf(b2, a) == -1 ? false : true;
	}

	//KMP算法
	private static int getIndexOf(String s, String m) {
		if (s == null || m == null || s.equals("") || m.equals("") || m.length() < 1 || s.length() < m.length())
			return -1;
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0, mi = 0; //si原字符串指针，想同则++ ；mi匹配串指针，想同则++
		//获取匹配串ms某位置结尾与前面字符串的最大匹配长度
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (ms[mi] == -1) si++;//第一个字符匹配串和原字符串不想等
			else mi = next[mi];
		}
		return mi == ms.length ? si - mi : -1;//匹配成功则返回，s相同串的第一个字母的位置
	}


	/**
	 * next[i]的含义是在ms[i]之前的字符串ms[0....i-1]中，
	 * 必须以ms[i-1]结尾的后缀子串（不能包含ms[0]）与必须包含ms[0]开头的前缀子串（不能包含ms[i-1]）最大匹配长度
	 */
	private static int[] getNextArray(char[] ms) {
		if (ms.length == 0) return new int[]{-1};
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int cn = 0, pos = 2;
		while (pos < next.length) {
			if (next[pos - 1] == next[cn]) next[pos++] = ++cn;
			else if (cn > 0) cn = next[cn];
			else next[pos++] = 0;
		}
		return next;
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
