package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class CD168_二叉树的按层打印与ZigZag打印_129 {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}

		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		int n = nextInt();
		int root = nextInt();
		TreeNode node = read();
		bfs(node);
		zigzag(node);
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


	//层遍历
	// 从头开始，取出当前节点并打印，左右有孩子分别加入，直到列表为空
	private static void bfs(TreeNode root) {
		if (root == null) return;
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		int level = 1;
		while (!list.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Level ").append(level).append(" :");
			int size = list.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = list.poll();
				sb.append(" ").append(cur.val);
				if (cur.left != null) list.add(cur.left);
				if (cur.right != null) list.add(cur.right);
			}
			level++;
			System.out.println(sb);
		}
	}

	private static void zigzag(TreeNode root) {
		if (root == null) return;

		LinkedList<TreeNode> l1 = new LinkedList<>();
		LinkedList<TreeNode> l2 = new LinkedList<>();
		l1.push(root);
		int level = 1;

		while (!l1.isEmpty() || !l2.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Level ").append(level);
			if (level % 2 == 1) { // 1 % 2 = 1
				sb.append(" from left to right:");
				while (!l1.isEmpty()) {
					TreeNode cur = l1.pop();
					sb.append(" ").append(cur.val);
					if (cur.left != null) l2.push(cur.left);
					if (cur.right != null) l2.push(cur.right);
				}
			} else {
				sb.append(" from right to left:");
				while (!l2.isEmpty()) {
					TreeNode cur = l2.pop();
					sb.append(" ").append(cur.val);
					if (cur.right != null) l1.push(cur.right);
					if (cur.left != null) l1.push(cur.left);
				}
			}
			level++;
			System.out.println(sb);
		}
	}
}
