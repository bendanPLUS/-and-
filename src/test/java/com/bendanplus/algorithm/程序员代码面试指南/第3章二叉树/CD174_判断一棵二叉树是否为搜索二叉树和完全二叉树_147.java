package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一棵二叉树，已经其中没有重复值的节点，请判断该二叉树是否为搜索二叉树和完全二叉树。
 */
public class CD174_判断一棵二叉树是否为搜索二叉树和完全二叉树_147 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 构建二叉树
		String[] params = br.readLine().split(" ");
		int n = Integer.parseInt(params[0]);
		TreeNode root = new TreeNode(Integer.parseInt(params[1]));
		HashMap<Integer, TreeNode> map = new HashMap<>();
		map.put(root.value, root);
		for (int i = 0; i < n; i++) {
			params = br.readLine().split(" ");
			int val = Integer.parseInt(params[0]);
			int leftVal = Integer.parseInt(params[1]);
			int rightVal = Integer.parseInt(params[2]);
			TreeNode node = map.get(val);
			if (leftVal != 0) {
				node.left = new TreeNode(leftVal);
				map.put(leftVal, node.left);
			}
			if (rightVal != 0) {
				node.right = new TreeNode(rightVal);
				map.put(rightVal, node.right);
			}
		}
		// 判断二叉搜索树
		System.out.println(isBST(root));
		// 判断完全二叉树
		System.out.println(isCBT(root));
	}

	// 判断二叉搜索树 二叉搜索树直接检验中序遍历序列的单调递增性即可 即改写一个二叉树的中序遍历
	private static boolean isBST(TreeNode root) {
		int prev = Integer.MIN_VALUE;
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				if (root.value <= prev) return false;     // 破坏了中序遍历的单调递增特性，直接返回false
				else prev = root.value;
				root = root.right;
			}
		}
		return true;
	}


	/**
	 * 完全二叉树采用层次遍历的方式检验，
	 * 1.如果遇到有右孩子没有左孩子的节点，不是完全二叉树；
	 * 2.如果遇到一个左右孩子不双全的节点，那之后遍历的所有节点都应该是叶子节点，不满足就不是完全二叉树。
	 */
	private static boolean isCBT(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean flag = false;      // 孩子不双全的节点是否出现过
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.left == null && root.right != null) return false;    // 有右孩子没有左孩子
			if ((root.left != null || root.right != null) && flag) return false;    // 出现过左右孩子不双全的节点，之后必须全部是叶子节点
			if (root.left == null || root.right == null) flag = true;    // 遇到左右孩子不双全的节点
			if (root.left != null) queue.offer(root.left);
			if (root.right != null) queue.offer(root.right);
		}
		return true;
	}
}
