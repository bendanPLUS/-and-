package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 丑数
 * url:https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。
 * 例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。
 * 习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
 */
@Slf4j
public class 面试题49_JZ49_丑数 {
	public int GetUglyNumber_Solution(int index) {
		if (index <= 6) return index;
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		int i2 = 0;
		int i3 = 0;
		int i5 = 0;
		while (arrayList.size() < index) {
			int m2 = arrayList.get(i2) * 2;
			int m3 = arrayList.get(i3) * 3;
			int m5 = arrayList.get(i5) * 5;
			int min = Math.min(m2, Math.min(m3, m5));
			arrayList.add(min);
			if (m2 == min) i2++;
			if (m3 == min) i3++;
			if (m5 == min) i5++;
		}
		return arrayList.get(arrayList.size() - 1);
	}

	@Test
	public void test() {
		final int i = GetUglyNumber_Solution(100);
		final boolean ugly = isUgly(i);
		log.info("第index是否为丑数:{}  第index丑数是:{}", ugly, i);
	}

	// 判断是否是丑数
	public boolean isUgly(int number) {
		while (number % 2 == 0) number = number / 2;
		while (number % 3 == 0) number = number / 3;
		while (number % 5 == 0) number = number / 5;
		return number == 1 ? true : false;

	}
}
