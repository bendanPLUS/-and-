package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CD104_字符串的调整II_260 {
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String str = input.readLine();
		char[] chars = str.toCharArray();
		char[] res = replace(chars);
		System.out.print(new String(res));
	}

	public static char[] replace(char[] chars) {
		if (chars == null || chars.length == 0) {
			return chars;
		}
		int num = 0;
		int len = 0;
		for (len = 0; len < chars.length; len++)
			if (chars[len] == ' ') num++;

		int index = len + num * 2 - 1;
		char[] res = new char[index + 1];
		for (int i = len - 1; i > -1; i--) {
			if (chars[i] != ' ') res[index--] = chars[i];
			else {
				res[index--] = '0';
				res[index--] = '2';
				res[index--] = '%';
			}
		}
		return res;
	}

}
