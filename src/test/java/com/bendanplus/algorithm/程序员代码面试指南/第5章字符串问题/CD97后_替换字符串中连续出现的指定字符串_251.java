package com.bendanplus.algorithm.程序员代码面试指南.第5章字符串问题;

import java.util.Optional;

/**
 * 功能描述：（替换字符串中连续出现的指定字符串）
 * 给定三个字符串str,from,to,已知from 子串中无重复字符，把str中所有from的子串全部替换成to字符串，
 * 对连续出现的from的部分要求只替换一个to字符，
 * 返回最终的结果字符串
 */
public class CD97后_替换字符串中连续出现的指定字符串_251 {
	public static void main(String[] args) {
//        System.out.println("123456qwer".regionMatches(2,"aa", 3,  2));
		System.out.println(replaceSpecialStr("123abc", "abc", "4567"));

	}

	public static String replaceSpecialStr(String str, String from, String to) {
		//特殊情况处理
//        if(!Optional.ofNullable(str).isPresent()){
//            return "";
//        }
//        if(!Optional.ofNullable(from).isPresent()){
//            return str;
//        }
//        if(!str.contains(from)){
//            return str;
//        }

		/**
		 * 当给定str字符串和from字符串都为空时，返回均为str
		 * 当str不包含from时，也直接返回str
		 */
		if (!Optional.ofNullable(str).isPresent() || !Optional.ofNullable(from).isPresent() || !str.contains(from))
			return str;
		char[] chars = str.toCharArray();
		char[] fromChars = from.toCharArray();
		int match = 0;//str和from 配对的位置
		for (int i = 0; i < chars.length; i++) {  //遍历str数组
			if (chars[i] == fromChars[match]) {   //这里用来判断str字符是否和from字符串一一匹配的
				if (match == from.length()) {  //match是from的最后一个字符
					clearChar(chars, i, from.length());
					match = 0;
				}
				match++;                       //match不是from的最后一个字符时，继续向后匹配
			} else {                              //匹配失败
				if (chars[i] == fromChars[0])  //如果当前位=等于from第一个字符，则i倒回去重新匹配，进入上面的if判断
					i--;
				match = 0;                       //match设置为0，接着进行下一个str字符匹配
			}
		}

		String res = "";   //表示结果字符串
		String cur = "";   //表示需要拼接的动态字符串
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != 0) cur = cur + String.valueOf(chars[i]);
			if (chars[i] == 0 && (i == 0 || chars[i - 1] != 0)) {//首位是空字符，加to, 当前为是空字符，前一位不是空字符则追加，这里体现了连续出现多次时，只替换一次的结果
				res = res + cur + to;
				cur = "";
			}

		}
		if (!cur.equals(""))   //防止最后一位不是空字符
			res = res + cur;
		return res;
	}


	public static void clearChar(char[] chars, int end, int len) {
		while (len-- != 0) //替换数组内某位置的连续字符时，需要两个一定的变量，一个是长度，一个是开始或者结束字符，一般是结束字符 。因为遍历时刚好能得到结束字符
			chars[end--] = 0;
	}
}
