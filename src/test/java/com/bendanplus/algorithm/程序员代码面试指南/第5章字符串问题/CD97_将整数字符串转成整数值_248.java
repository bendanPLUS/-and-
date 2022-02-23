package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD97_将整数字符串转成整数值_248 {
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		String str = sc.readLine();
		System.out.println(convert(str));
	}


	public static int convert(String str) {
		if (!isValid(str)) return 0;
		final char[] chars = str.toCharArray();
		//校验成功开始转化
		int pos = chars[0] == '-' ? -1 : 1;
		int res = 0;
		int cur = 0;
		int minq = Integer.MIN_VALUE / 10;//-214748364
		int minr = Integer.MIN_VALUE % 10;//-8

		//负数从1开始 正数从零开始
		for (int i = pos == -1 ? 1 : 0; i < chars.length; i++) {
			//正数负数 都按照负数处理
			cur = '0' - chars[i];
			//-214748365 <-214748364 || -214748364 =-214748364 && -9 < -8
			if (res < minq || (res == minq && cur < minr)) return 0;
			res = res * 10 + cur;
		}
		if (pos == 1 && res == Integer.MIN_VALUE) return 0;
		return pos == -1 ? res : -res;
	}

	//校验String是否是正常的数字
	public static boolean isValid(String str) {
		if (str == null || str.length() == 0) return false;
		final char[] chars = str.toCharArray();
		//判断第一位数
		//如果第一位不是'-' 且第一位不是数字
		if (chars[0] != '-' && (chars[0] > '9' || chars[0] < '0')) return false;
		//如果第一位是 '-'
		if (chars[0] == '-' && (chars.length == 1 || chars[1] == '0')) return false;
		//如果第一位是0
		if (chars[0] == '0' && chars.length >= 2) return false;
		//第一位就不是数字的情况判断完成
		//判断第二位到最后一位
		for (int i = 1; i < chars.length; i++)
			if ((chars[i] > '9' || chars[i] < '0')) return false;

		return true;
	}
}
