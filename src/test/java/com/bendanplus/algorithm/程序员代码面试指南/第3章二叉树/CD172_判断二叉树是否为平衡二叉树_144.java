package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CD172_判断二叉树是否为平衡二叉树_144 {
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static int nextInt() {
		try {
			st.nextToken();
			return (int) st.nval;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static TreeNode read() {
		int fal, lth, rth;
		fal = nextInt();
		lth = nextInt();
		rth = nextInt();
		TreeNode node = new TreeNode(fal);
		if (lth != 0) {
			node.left = read();
		}
		if (rth != 0) {
			node.right = read();
		}
		return node;
	}

	/**
	 * 解发的大概思路：
	 * 利用二叉树的后序遍历，对于任何一个节点node，先遍历node的左子树
	 * + 改写求二叉树的深度
	 */
	public static int getHeight(TreeNode root) {
		if (root == null) return 0;
		//后续遍历求高度，前序遍历求深度
		int leftHeight = getHeight(root.left);
		//一旦子树已经不是平衡树，整棵树就不是平衡树
		if (leftHeight == -1) return -1;
		int rightHeight = getHeight(root.right);
		if (rightHeight == -1) return -1;
		//左右子树高度差大于1，就不是平衡树
		if (Math.abs(leftHeight - rightHeight) > 1) return -1;
		//以当前根节点为最大高度
		return Math.max(leftHeight, rightHeight) + 1;
	}


	/***
	 * 求二叉树的深度
	 * 利用后序遍历特性  左  右  根
	 * 递归
	 */
	public int TreeDepth(TreeNode root) {
		if (root == null) return 0;
		//获取左子树的深度
		int leftHeight = TreeDepth(root.left);
		//获取右子树的深度
		int rightHeight = TreeDepth(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
		//return leftHeight - rightHeight > 0 ? leftHeight + 1 : rightHeight + 1;
	}

	public static void main(String[] args) {
		int n = nextInt();
		int root = nextInt();
		TreeNode node = read();
		System.out.println(getHeight(node) != -1);
	}
}
