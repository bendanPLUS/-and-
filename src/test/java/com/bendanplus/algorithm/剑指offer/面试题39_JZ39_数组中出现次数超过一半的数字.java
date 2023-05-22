package com.bendanplus.algorithm.剑指offer;

/**
 * 数组中出现次数超过一半的数字
 */
public class 面试题39_JZ39_数组中出现次数超过一半的数字 {


	public int MoreThanHalfNum_Solution(int[] array) {
		if (array == null || array.length == 0) return 0;
		int temp = array[0];
		int time = 1;
		int numbers = 0;
		for (int i = 1; i < array.length; i++) {
			time = temp == array[i] ? time + 1 : time - 1;
			if (time == 0) {
				temp = array[i];
				time = 1;
			}
		}
		for (int i = 0; i < array.length; i++)
			if (array[i] == temp) numbers++;
		return 2 * numbers <= array.length ? 0 : temp;
	}

	public int MoreThanHalfNum_Solution_老版本(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int temp = array[0];
		int time = 1;
		int numbers = 0;
		for (int i = 1; i < array.length; i++) {
			if (time == 0) {
				temp = array[i];
				time = 1;
			} else if (temp == array[i]) {
				time++;

			} else {
				time--;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == temp) {
				numbers++;
			}
		}
		if (2 * numbers <= array.length) {
			return 0;
		}
		return temp;
	}
}
