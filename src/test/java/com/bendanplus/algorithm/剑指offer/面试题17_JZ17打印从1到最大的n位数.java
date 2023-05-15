package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class 面试题17_JZ17打印从1到最大的n位数 {

	@Test
	public void test面试题17() {
		log.info("结果:[{}]", printNumbers(3));
	}

	public int[] printNumbers(int n) {
		int len = (int) Math.pow(10, n); //10的n次幂
		int index = 1;
		int[] res = new int[len - 1];
		for (int i = 0; i < res.length; i++)
			res[i] = index++;
		return res;
	}
}
