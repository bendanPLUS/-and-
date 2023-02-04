package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD116_翻转字符串1_262 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		System.out.println(retota(input.toCharArray()));
	}

	public static String retota(char[] chas) {
		if (chas == null || chas.length == 0) return "";
		int before = 0;
		reserve(chas, 0, chas.length - 1);
		for (int i = 0; i < chas.length; i++) {
			if (chas[i] == ' ') {
				reserve(chas, before, i - 1);
				before = i + 1;
			}
		}
		if (chas[chas.length - 1] != ' ') reserve(chas, before, chas.length - 1);
		return new String(chas);
	}

	public static void reserve(char[] chas, int start, int end) {
		while (start < end) {
			char temp = chas[start];
			chas[start] = chas[end];
			chas[end] = temp;
			start++;
			end--;
		}
	}
}