package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD98_字符串的统计字符串_253 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		String ans = process(str);
		System.out.println(ans);
	}

	public static String process(String str) {
		if (str.length() == 0 || str == null) return "";

		StringBuilder res = new StringBuilder();
		final char[] chars = str.toCharArray();
		res.append(chars[0]);
		int num = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i - 1] != chars[i]) {
				//计算出 chars[i-1] 的个数 num   ， chars[i]还没有计算
				res.append('_').append(num).append('_').append(chars[i]);
				num = 1;
			} else num++;
		}
		return res.append('_').append(num).toString();
	}
}
