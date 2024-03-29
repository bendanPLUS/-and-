package com.bendanplus.algorithm.剑指offer;

public class 面试题12_JZ65矩阵中的路径 {


	/**
	 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
	 *
	 *
	 * @param matrix char字符型二维数组
	 * @param word string字符串
	 * @return bool布尔型
	 */


	// 回溯法经典题目
	public boolean hasPath(char[][] matrix, String word) {
		// write code here
		boolean res = false;
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		//矩阵中坐标(i,j)
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				res = judge(matrix, visited, word, i, j, 0);
				if (res)
					return res;
			}
		}
		return res;
	}

	/***
	 * @param matrix
	 * @param visited 矩阵标记已经走过的位置为true
	 * @param word 要匹配的路径
	 * @param i 矩阵中坐标(i,j)
	 * @param j 矩阵中坐标(i,j)
	 * @param k k代表走了几步了
	 * @return
	 */
	public boolean judge(char[][] matrix, boolean[][] visited, String word, int i, int j, int k) {
		if (k == word.length())
			return true;
		boolean res = false;
		if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length &&
				!visited[i][j] && matrix[i][j] == word.charAt(k)) {
			visited[i][j] = true;
			res = judge(matrix, visited, word, i + 1, j, k + 1) ||
					judge(matrix, visited, word, i - 1, j, k + 1) ||
					judge(matrix, visited, word, i, j + 1, k + 1) ||
					judge(matrix, visited, word, i, j - 1, k + 1);
			visited[i][j] = false;
		}
		return res;
	}

}
