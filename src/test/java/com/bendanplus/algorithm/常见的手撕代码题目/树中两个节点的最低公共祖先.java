package com.bendanplus.algorithm.常见的手撕代码题目;


import com.bendanplus.algorithm.剑指offer.TreeNode;
import lombok.val;

/**
 * 二叉查找树解法:
 * 二叉查找树中，两个节点 p, q 的公共祖先 root 满足 root.val >= p.val && root.val <= q.val。
 * 传送门:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * 普通二叉树解法: 没有父节点
 * 在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class 树中两个节点的最低公共祖先 {
	//二叉搜索树 中序遍历的话是递增的
	//这里采用后序遍历的方式
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.val > p.val && root.val > q.val)
			return lowestCommonAncestor1(root.left, p, q);
		if (root.val < p.val && root.val < q.val)
			return lowestCommonAncestor1(root.right, p, q);
		return root;
	}

	/**
	 * 普通二叉树
	 * 在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == root || q == root)
			return root;
		final TreeNode left = lowestCommonAncestor2(root.left, p, q);
		final TreeNode right = lowestCommonAncestor2(root.right, p, q);
		return left == null ? right : (right == null ? left : root);

		//TODO
		/** left == null ? right : (right == null ? left : root);
		 *  可分解为两步
		 *  1.
		 *  if (left != null && right != null)
		 * 			return root;
		 *  2.
		 *  return left == null ? right : left;
		 */
	}
}
