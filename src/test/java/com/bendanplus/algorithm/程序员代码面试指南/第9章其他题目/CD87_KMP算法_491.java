package com.bendanplus.algorithm.程序员代码面试指南.第9章其他题目;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定两个字符串str和match，长度分别为N和M。实现一个算法，如果字符串str中含有子串match，
 * 则返回match在str中的开始位置，不含有则返回-1
 * 若出现了多次，则按照升序输出所有出现位置
 */
public class CD87_KMP算法_491 {
	public static StringBuilder sb = new StringBuilder();

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) return -1;
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (next[mi] == -1) si++;
			else mi = next[mi];
		}
		return mi == ms.length ? si - mi : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) return new int[]{-1};
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) next[pos++] = ++cn;
			else if (cn > 0) cn = next[cn];
			else next[pos++] = 0;
		}
		return next;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String m = br.readLine();
		int res = getIndexOf(s, m);
		System.out.println(res);
	}
}
