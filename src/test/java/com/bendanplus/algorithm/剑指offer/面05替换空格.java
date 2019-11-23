package com.bendanplus.algorithm.剑指offer;

import java.util.HashMap;

/**
 * Stringbuffer代表一个字符序列可变的字符串,当一个Stringbuffer被创建以后,通过Stringbuffer提供的
 * append() reverse() setCharAt() setLength()方法改变这个字符串序列
 * Stringbuffer是线程安全所有方法都是synchronized修饰的
 */
public class 面05替换空格 {
	public String replaceSpace(StringBuffer str) {
		if (str == null || str.length() == 0)
			return str.toString();
		int oldTail = str.length() - 1;
		for (int i = 0; i <= oldTail; i++)
			if (str.charAt(i) == ' ')
				str.append("  ");

		int newTail = str.length() - 1;
		//从后向前遍历
		while (oldTail >= 0 && oldTail != newTail) {
			char c = str.charAt(oldTail--);
			if (c == ' ') {
				str.setCharAt(newTail--, '0');
				str.setCharAt(newTail--, '2');
				str.setCharAt(newTail--, '%');
			} else
				str.setCharAt(newTail--, str.charAt(c));
		}
		//这是是出给C++人做的[奸笑] java用repaceAll
		return str.toString().replaceAll(" ", "02%");
	}
}