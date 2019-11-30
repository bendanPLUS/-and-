package com.bendanplus.algorithm.leetcode;

public class Solution_5最长回文串 {

	public String preProcess(String s) {
		int n = s.length();
		if (n == 0) {
			return "^$";
		}
		String ret = "^";
		for (int i = 0; i < n; i++)
			ret += "#" + s.charAt(i);
		ret += "#$";
		return ret;
	}

	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return s;
		return null;
	}
}
