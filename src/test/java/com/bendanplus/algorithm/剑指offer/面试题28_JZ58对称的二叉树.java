package com.bendanplus.algorithm.剑指offer;

import java.util.*;
import java.util.Stack;

/**
 * 描述
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class 面试题28_JZ58对称的二叉树 {
	boolean isSymmetrical(TreeNode pRoot) {
		return isSymmetrical(pRoot, pRoot);
	}

	private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.left, root2.right);
	}
}
