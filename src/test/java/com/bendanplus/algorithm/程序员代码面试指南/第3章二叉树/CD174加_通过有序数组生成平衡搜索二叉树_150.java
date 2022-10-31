package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

/***
 * 给定一个有序数组sortArr，已知其中没有重复值，并且该二叉搜索树的中序遍历与sortArr一致
 */
public class CD174加_通过有序数组生成平衡搜索二叉树_150 {
	public TreeNode generateTree(int[] sortArr) {
		if (sortArr == null) return null;
		return generate(sortArr, 0, sortArr.length);
	}

	private TreeNode generate(int[] sortArr, int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(sortArr[mid]);
		root.left = generate(sortArr, start, mid - 1);
		root.right = generate(sortArr, mid + 1, end);
		return root;
	}
}
