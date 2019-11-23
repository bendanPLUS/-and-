package com.bendanplus.algorithm.剑指offer;

public class 面07前序遍历和中序遍历结果重建二叉树 {

	/**
	 * 这个地方可以把中序遍历的结果放到map里 key=实际值 value=in数值对应的位置 这样就不用每一次递归都for循环了 直接在map里面拿
	 * private Map<Integer, Integer> indexForInOrders = new HashMap<>();
	 * for (int i = 0; i < in.length; i++){
	 * indexForInOrders.put(in[i], i);
	 * }
	 */
	//空壳方法
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		return reConstructBinaryTree(pre, 0, pre.length - 1,
				in, 0, in.length - 1);
	}

	private TreeNode reConstructBinaryTree(
			int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd)
			return null;
		int val = pre[preStart];
		TreeNode root = new TreeNode(val);

		for (int i = inStart; i < inEnd + 1; i++) {
			if (pre[preStart] == in[i]) {
				root.left = reConstructBinaryTree(
						pre, preStart + 1, preStart + i - inStart,
						in, inStart, i - 1);
				root.right = reConstructBinaryTree(
						pre, preStart + i - inStart + 1, preEnd,
						in, i + 1, inEnd);
				break;
			}
		}
		return root;
	}
}
