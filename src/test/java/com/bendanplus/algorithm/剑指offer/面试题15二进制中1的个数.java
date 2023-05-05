package com.bendanplus.algorithm.剑指offer;

public class 面试题15二进制中1的个数 {
	public int NumberOf1(int n) {
		int cnt = 0;
		while (n != 0) {
			cnt++;
			n &= (n - 1);
		}
		return cnt;
	}
	//Integer.bitCount(n) Integer源码
	public int NumberOf2(int n) {

		// HD, Figure 5-2
		n = n - ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n + (n >>> 4)) & 0x0f0f0f0f;
		n = n + (n >>> 8);
		n = n + (n >>> 16);
		return n & 0x3f;
	}

}
