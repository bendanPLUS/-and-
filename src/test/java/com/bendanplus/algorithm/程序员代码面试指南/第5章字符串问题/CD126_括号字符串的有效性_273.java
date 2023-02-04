package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.util.LinkedList;
import java.util.Scanner;

public class CD126_括号字符串的有效性_273 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String input = sc.nextLine();
			boolean res = validBracket(input);
			if (res) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	public static boolean validBracket(String input) {
		if (StringUtils.isBlank(input)) return false;

		char[] chars = input.toCharArray();
		int status = 0;
		for (int i = 0; i < chars.length; i++) {
			char iChar = chars[i];
			if (iChar != '(' && iChar != ')') return false;
			if (iChar == ')' && --status < 0) return false;//如果）更多 直接返回false
			if (iChar != '(') status++;
		}
		return status == 0;
	}
}
