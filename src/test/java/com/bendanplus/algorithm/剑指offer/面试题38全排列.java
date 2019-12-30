package com.bendanplus.algorithm.剑指offer;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串的排列
 * 输入一个字符串,打印出该字符串中字符的所有排列.
 * 例如: abc 则打印出abc所排列出的所有字符串 abc acb bac bca cab cba
 */
@Slf4j
public class 面试题38全排列 {

	@Test
	public void test面试题38() {
		final ArrayList<String> res = Permutation("abc");
		log.info("结果:[{}]", res);
	}
	public ArrayList<String> Permutation(String str) {
		ArrayList<String> array = new ArrayList();
		if (StringUtils.isBlank(str))
			return array;
		char[] chars = str.toCharArray();
		Permutation001(chars, array, 0);
		Collections.sort(array);
		return array;
	}
    //
	private void Permutation001(char[] chars, ArrayList<String> array, int begin) {
		if (begin == chars.length - 1) {
			String str = String.valueOf(chars);
			if (!array.contains(str))
				array.add(str);
		} else {
			for (int i = begin; i < chars.length; i++) {
				swep(chars, begin, i);
				Permutation001(chars, array, begin + 1);
				swep(chars, begin, i);
			}
		}
	}
	private void swep(char[] chars, int i, int j) {
		if (chars == null || chars.length == 0)
			return;
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
}
