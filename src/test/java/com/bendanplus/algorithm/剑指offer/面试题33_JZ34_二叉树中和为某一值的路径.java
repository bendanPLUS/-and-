package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;

//输入一颗二叉树的根节点root和一个整数expectNumber，
// 找出二叉树中结点值的和为expectNumber的所有路径。
//1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
//2.叶子节点是指没有子节点的节点
//3.路径只能从父节点到子节点，不能从子节点到父节点
//4.总节点数目为n
public class 面试题33_JZ34_二叉树中和为某一值的路径 {

	private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		backtracking(root, target, new ArrayList<>());
		return result;
	}

	private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
		if (node == null) return;
		path.add(node.val);
		target -= node.val;
		if (target == 0 && node.left == null && node.right == null) result.add(new ArrayList<>(path));
		else {
			backtracking(node.left, target, path);
			backtracking(node.right, target, path);
		}
		path.remove(path.size() - 1);
	}
}
