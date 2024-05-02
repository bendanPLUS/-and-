package com.bendanplus.algorithm.剑指offer;

public class 面试题16数值的整数次方 {

	public double Power(double base, int exponent) {
		if (powerEqual(base, 0.0) && exponent < 0) return 0.0;
		int absExponent = exponent > 0 ? exponent : -exponent;
		double res = getPower(base, absExponent);
		return exponent > 0 ? res : 1 / res;
	}

	//此时 exponent一定是大于0的整数
	private double getPower(double base, int exponent) {
		if (exponent == 0) return 1;
		if (exponent == 1) return base;
		//递归
		double res = getPower(base, exponent / 2);
		res *= res;
		if ((exponent & 1) == 1) res *= base;
		return res;
	}


	private boolean powerEqual(double base1, double base2) {
		if (base1 - base2 <= 0.0000000000001 && base2 - base1 <= 0.000000000000001) return true;
		return false;
	}
}
