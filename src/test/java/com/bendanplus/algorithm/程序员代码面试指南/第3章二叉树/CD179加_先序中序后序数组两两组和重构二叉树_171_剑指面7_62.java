package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

public class CD179加_先序中序后序数组两两组和重构二叉树_171_剑指面7_62 {

	//已知前序遍历和中序遍历结果重新建立二叉树
	public TreeNode generateTree(int[] pre, int[] in) {
		if (pre == null || in == null) return null;
		return generate(pre, 0, pre.length - 1, in, 0, in.length - 1);

	}

	private TreeNode generate(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd) return null;
		TreeNode root = new TreeNode(pre[preStart]);

		for (int i = inStart; i <= inEnd; i++) {
			if (pre[preStart] == in[i]) {
				root.left = generate(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
				root.right = generate(pre, preStart + i - inStart + 1, inEnd, in, i + 1, inEnd);
				break;
			}
		}
		return root;
	}
}
