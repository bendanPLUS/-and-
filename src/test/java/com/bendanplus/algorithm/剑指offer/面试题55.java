package com.bendanplus.algorithm.剑指offer;

/**
 * 55.1 二叉树的深度
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
 * url:https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13&tqId=11191&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class 面试题55 {
	public int TreeDepth(TreeNode root) {
		//就完事了 搞那么麻烦
		//return root == null ? 0 : 1 + Math.max(TreeDepth(root.left),TreeDepth(root.right));
		if (root == null) {
			return 0;
		}
		int left = TreeDepth(root.left);
		int right = TreeDepth(root.right);
		return left > right ? left + 1 : right + 1;
	}

	/**
	 * 55.2 平衡二叉树左右子树高度差不超过 1
	 * url :https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
	 */
	public boolean IsBalanced_Solution(TreeNode root) {
		return height(root) != -1;
	}

	private int height(TreeNode root) {
		if (root == null)
			return 0;
		int left = height(root.left);
		if (left == -1)
			return -1;
		int right = height(root.right);
		if (right == -1)
			return -1;
		return Math.abs(left - right) <= 1 ? Math.max(left + 1, right + 1) : -1;
	}
}
