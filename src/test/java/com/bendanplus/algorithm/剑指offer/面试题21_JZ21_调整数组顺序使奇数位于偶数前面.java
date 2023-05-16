package com.bendanplus.algorithm.剑指offer;

public class 面试题21_JZ21_调整数组顺序使奇数位于偶数前面 {

	private int[] reOrderArray(int[] array) {
		if (array == null || array.length == 0) return array;
		int a = 0;
		int b = a + 1;
		while (a < array.length) {
			while ((array[a] & 1) == 1) a++;
			b = a + 1;
			while (b < array.length && (array[b] & 1) == 0) b++;
			//ab交换位置

			if (b < array.length) {
				int tmp = array[b];
				for (int i = b; i > a; i--)
					array[i] = array[i - 1];
				array[a] = tmp;
			} else break;
		}
		return array;
	}
}
