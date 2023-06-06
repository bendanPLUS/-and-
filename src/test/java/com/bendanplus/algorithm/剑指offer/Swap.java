package com.bendanplus.algorithm.剑指offer;

public class Swap {
	public static void swap(int[] array, int i, int j) {
		if (array == null || array.length == 0) return;
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
