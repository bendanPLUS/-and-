package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 描述
 * 给定一个字符串数组strs[]，在strs中有些位置为null，但在不为null的位置上
 * ，其字符串是按照字典序由小到大出现的。在给定一个字符串str，
 * 请返回str在strs中出现的最左位置，如果strs中不存在str请输出“-1”。
 * <p>
 * <p>
 * 输入描述：
 * 输出包含多行，第一行包含一个整数n代表strs的长度，第二行一个字符串，代表str，接下来n行，
 * 每行包含一个字符串构成，字符串只包含小写字符，如果这一行为“0”，代表该行字符串为空，这n行字符串代表strs。
 * （数据保证当字符串不为空时，所有字符均为小写字母；保证所有的字符串长度都小于10，1 \leq n \leq 10^51≤n≤105）
 */
public class CD99_在有序但是含有空的数组中查找_258 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//字符串个数
		int n = Integer.parseInt(in.readLine());
		String[] strs = new String[n];
		//目标字符串
		String str = in.readLine();
		//将字符串写入字符串数组
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			if (!line.equals("0")) strs[i] = line;
		}
		System.out.println(getIndex(strs, str));
	}


	//二分法
	public static int getIndex(String[] strs, String str) {
		if (strs == null || strs.length <= 0 || str == null) return -1;
		int res = -1;
		int left = 0;
		int right = strs.length - 1;
		int mid = 0;
		int i = 0;
		//二分 只要 左指针和右指针不重合
		while (left <= right) {
			mid = (left + right) / 2;
			if (strs[mid] != null && strs[mid].equals(str)) {
				res = mid;
				//要左半部分
				right = mid - 1;
			} else if (strs[mid] != null) {
				if (strs[mid].compareTo(str) < 0) {
					//要右半部分
					left = mid + 1;
				} else {
					//要左半部分
					right = mid - 1;
				}
				//strs[mid] == null
			} else {
				i = mid;
				//从右到左遍历左半区 发现第一个不为null的位置 i
				while (strs[i] == null && --i >= left) ;

				if (i < left || strs[i].compareTo(str) < 0) {
					left = mid + 1;
				} else {
					res = strs[i].equals(str) ? i : res;
					right = i - 1;
				}
			}
		}
		return res;
	}


}
