package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import org.junit.platform.commons.util.StringUtils;

import java.util.Scanner;

public class CD122_数组中两个字符串的最小距离_266 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str1 = sc.next();
		String str2 = sc.next();
		String[] strs = new String[n];
		for (int i = 0; i < n; i++) {
			strs[i] = sc.next();
		}
		sc.close();
		int res = minDistance(strs, str1, str2);
		System.out.println(res);
	}


	/**
	 * O(N)的时间复杂度，从左往右扫一遍，空间复杂度O(1)
	 * 扫到了记录last1和last2分别表示str1和str2最近出现的下标，
	 * 那么i - last1或i - last2就是最短的距离，一直更新这个最短距离。
	 */
	public static int minDistance(String[] strs, String str1, String str2) {
		if (StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) return -1;
		if (str1.equals(str2)) return 0;
		int last1 = -1, last2 = -1;//两个指针从左到右一直到找数组里面等于 str1或者str2的位置
		int resMin = Integer.MIN_VALUE;
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(str1)) {
				resMin = Math.min(resMin, last2 == -1 ? resMin : i - last2);
				last1 = i;
			}
			if (strs[i].equals(str2)) {
				resMin = Math.min(resMin, last1 == -1 ? resMin : i - last1);
				last2 = i;
			}
		}
		return resMin == Integer.MIN_VALUE ? -1 : resMin;
	}
}
