package com.bendanplus.algorithm.程序员代码面试指南.第4章递归和动态规划;

import java.util.Scanner;

public class CD44_字符串的交错组成_220 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		String s3 = sc.nextLine();
		int a = s1.length(), b = s2.length(), c = s3.length();
		if (a + b != c) {
			System.out.print("NO");
		} else if (func(s1, s2, s3)) {
			System.out.print("YES");
		} else {
			System.out.print("NO");
		}
	}


	public boolean isCrossl(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null) return false;

		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		char[] chaim = aim.toCharArray();
		if (chaim.length != (ch1.length + ch2.length)) return false;
		boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];

		dp[0][0] = true;
		for (int i = 1; i < ch1.length + 1; i++) {
			dp[i][0] = false;
			if (ch1[i - 1] == chaim[i - 1]) dp[i][0] = true;
		}

		for (int j = 1; j < ch2.length + 1; j++) {
			dp[0][j] = false;
			if (ch2[j - 1] == chaim[j - 1]) dp[0][j] = true;
		}

		for (int i = 1; i < ch1.length + 1; i++) {
			for (int j = 1; j < ch2.length + 1; j++) {
				if ((dp[i - 1][j] && ch1[i - 1] == chaim[i + j - 1]) || (dp[i][j - 1] && ch2[j - 1] == chaim[i + j - 1]))
					dp[i][j] = true;
			}
		}
		return dp[ch1.length][ch2.length];
	}

	public static boolean func(String s1, String s2, String s3) {
		boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
		f[0][0] = true;
		for (int i = 1; i <= s1.length(); i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
				f[i][0] = true;
			} else {
				break;
			}
		}
		for (int j = 1; j <= s2.length(); j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
				f[0][j] = true;
			} else {
				break;
			}
		}
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				f[i][j] = (f[i - 1][j] == true && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (f[i][j - 1] == true && s2.charAt(j - 1) == s3.charAt(i + j - 1));
			}
		}
		return f[s1.length()][s2.length()];
	}
}
