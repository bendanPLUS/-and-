package com.bendanplus.algorithm.剑指offer;

/**
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。
 * 给定一个数，判断这个数是否在该二维数组中。
 */
public class 面试题04二维数组中的查找 {
	public boolean Find(int target, int[][] matrix) {
		//边界条件判断
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int hang = matrix.length - 1;
		int lie = 0;
		//从左下角开始想遍历
		while (hang >= 0 && lie < matrix[0].length) {
			if (matrix[hang][lie] == target)
				return true;
			else if (matrix[hang][lie] > target)
				hang--;
			else
				lie++;
		}
		return false;
	}
}
