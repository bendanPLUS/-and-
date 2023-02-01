package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个有 n 个不重复 整数的数组 arr，
 * 判断 arr 是否可能是节点值类型为整数的搜索二叉树后序遍历的结果。
 */
public class CD173_根据后序数组重建搜索二叉树_145 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] values = reader.readLine().split(" ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(values[i]);
		}
		System.out.println(isBST(arr) ? "true" : "false");
	}

	static boolean isBST(int[] arr) {
		if (arr == null || arr.length == 0) return false;
		return isBSTHelper(arr, 0, arr.length - 1);
	}


	/**
	 * 1. 根节点既有左子也有右子（节点3）
	 * 2. 根节点只有左子（节点 2）
	 * 3. 根节点只有右子（节点 4）
	 * 4. 只有根节点（节点 1、5）
	 */
	static boolean isBSTHelper(int[] arr, int start, int end) {
		if (start == end) return true;
		int leftSTreeEnd = -1;
		int rightSTreeStart = end;
		//找到 leftSTreeEnd --> 左子树的最后一个节点 和 rightSTreeStart --> 右子树的第一个节点
		for (int i = start; i < end; i++) {
			if (arr[end] > arr[i]) leftSTreeEnd = i;
			else
				//只初始化一次 记第一次 因为要找的是右子树的第一个节点
				rightSTreeStart = rightSTreeStart == end ? i : rightSTreeStart;
		}
		//判断  根节点只有左子树 or 根节点只有右子树情况的处理方式
		if (rightSTreeStart == end || leftSTreeEnd == -1) return isBSTHelper(arr, start, end - 1);
		if (rightSTreeStart != leftSTreeEnd + 1)  // mid = less+1
			return false;
		int leftSTreeStart = start;
		int rightSTreeEnd = end - 1;
		return isBSTHelper(arr, leftSTreeStart, leftSTreeEnd) && isBSTHelper(arr, rightSTreeStart, rightSTreeEnd);
	}
}
