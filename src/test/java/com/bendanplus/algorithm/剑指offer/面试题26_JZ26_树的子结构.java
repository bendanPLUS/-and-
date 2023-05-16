package com.bendanplus.algorithm.剑指offer;

//输入两棵二叉树A，B，判断B是不是A的子结构。（我们约定空树不是任意一个树的子结构）
public class 面试题26_JZ26_树的子结构 {

	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) return false;
		return DoseTree1HasTree2(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
	}

	private boolean DoseTree1HasTree2(TreeNode root1, TreeNode root2) {
		if (root2 == null) return true;
		if (root1 == null) return false;
		if (root1.val != root2.val) return false;
		return DoseTree1HasTree2(root1.left, root2.left) && DoseTree1HasTree2(root1.right, root2.right);
	}
}
