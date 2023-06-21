package com.bendanplus.algorithm.剑指offer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 把数组排成最小的数
 * 输入一个正整数数组,把数组里所有数字拼接起来排成一个数,
 * 打印出拼接出的所有数字中最小的一个.
 */
public class 面试题45_JZ45_把数组排成最小的数 {

	public static void main(String[] args) {
		int[] nums = new int[]{3, 32, 321};
		String s = PrintMinNumber(nums);

	}


	public static String PrintMinNumber(int[] numbers) {
		if (numbers == null || numbers.length == 0)
			//牛客网要求返回空串
			return "";
		String[] strArray = new String[numbers.length];
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strArray.length; i++)
			strArray[i] = String.valueOf(numbers[i]);

		Arrays.sort(strArray, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
		/**
		 Arrays.sort(strArray, new Comparator<String>() {
		@Override public int compare(String o1, String o2) {
		//if (StringUtils.isBlank(o1) || StringUtils.isBlank(o2)) {
		if (o1 == null || o1.length() == 0 || o2 == null || o2.length() == 0) {
		throw new IllegalArgumentException("argument should not be null");
		}
		String s1 = o1 + o2;
		String s2 = o2 + o1;
		return s1.compareTo(s2);
		}
		});*/
		for (String s : strArray)
			sb.append(s);
		return sb.toString();
	}
}
