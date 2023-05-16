package com.bendanplus.algorithm.剑指offer;

//操作给定的二叉树，将其变换为源二叉树的镜像。
public class 面试题27_JZ27_二叉树的镜像 {
	public TreeNode Mirror(TreeNode pRoot) {
		if (pRoot == null) return pRoot;
		TreeNode tmp = pRoot.left;
		pRoot.left = pRoot.right;
		pRoot.right = tmp;
		Mirror(pRoot.left);
		Mirror(pRoot.right);
		return pRoot;
	}
}
