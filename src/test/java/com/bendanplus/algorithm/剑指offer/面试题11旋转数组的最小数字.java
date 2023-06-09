package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class 面试题11旋转数组的最小数字 {

	@Test
	public void test面试题11() {
		int[] array = {1};
		log.info("结果:[{}]", minNumberInRotateArray(array));
	}

	public int minNumberInRotateArray(int[] array) {
		if (array == null || array.length == 0) return 0;
		int p1 = 0;
		int p2 = array.length - 1;
		while (p2 > p1 + 1) {
			int mid = (p1 + p2) >> 1;
			if (array[mid] == array[p1] && array[mid] == array[p2])
				return minNumber(array, p1, p2);
			else if (array[mid] <= array[p2])
				p2 = mid;
			else
				p1 = mid;
		}
		return array[p2];
	}

	private int minNumber(int[] array, int l, int h) {
		for (int i = l; i < h; i++)
			if (array[i] > array[i + 1])
				return array[i + 1];
		return array[l];
	}

}
