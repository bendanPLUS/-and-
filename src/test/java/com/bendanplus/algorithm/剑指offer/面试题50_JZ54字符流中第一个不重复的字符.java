package com.bendanplus.algorithm.剑指offer;

import org.apache.commons.lang3.StringUtils;

/**
 * 第一个只出现一次的字符位置
 * url:https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&tqId=11187&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。
 */
public class 面试题50_JZ54字符流中第一个不重复的字符 {
	public int FirstNotRepeatingChar(String str) {
		if (str == null || str.length() == 0) return 0;
		int[] cnts = new int[256];
		for (int i = 0; i < str.length(); i++)
			cnts[str.charAt(i)]++;

		for (int j = 0; j < str.length(); j++)
			if (cnts[str.charAt(j)] == 1) return j;
		return -1;


	}
}
