package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import com.bendanplus.algorithm.剑指offer.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class CD163_二叉树的序列化_103 {

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
		int fa, lch, rch;
		fa = nextInt();
		lch = nextInt();
		rch = nextInt();
		TreeNode node = new TreeNode(fa);
		if (lch != 0) {
			node.left = read();
		}
		if (rch != 0) {
			node.right = read();
		}
		return node;
	}

	//前序遍历 实现序列化
	public static String serialByPre(TreeNode node, StringBuilder sb) {
		if (node == null)
			return "#!";
		String str = node.val + "!";
		str += serialByPre(node.left, sb);
		str += serialByPre(node.right, sb);
		return str;
	}

	//前序遍历 实现反序列化
	public TreeNode reconByPreString(String preStr) {
		//str= "12！3！#！#！#！"

		final String[] split = preStr.split("!");// [12,3,#,#,#]
		Queue<String> queue = new LinkedList<>();
		for (String str : split)
			queue.add(str);
		return reconPreOrder(queue);
	}

	private TreeNode reconPreOrder(Queue<String> queue) {
		final String poll = queue.poll();
		if (poll.equals("#"))
			return null;
		TreeNode root = new TreeNode(Integer.valueOf(poll));
		root.left = reconPreOrder(queue);
		root.right = reconPreOrder(queue);
		return root;
	}

	//层遍历 序列化
	public static StringBuilder levelOrder(TreeNode node) {
		StringBuilder sb = new StringBuilder();
		sb.append(node.val + "!");
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.left != null) {
				sb.append(node.left.val + "!");
				queue.offer(node.left);
			} else {
				sb.append("#!");
			}
			if (node.right != null) {
				sb.append(node.right.val + "!");
				queue.offer(node.right);
			} else {
				sb.append("#!");
			}
		}
		return sb;
	}

	public static void main(String[] args) {
		int n = nextInt();
		int root = nextInt();
		TreeNode node = read();
		StringBuilder sb = new StringBuilder();
		//前序遍历 实现序列化
		String preOrderRes = serialByPre(node, sb);
		System.out.println(preOrderRes);

		StringBuilder levelOrderRes = levelOrder(node);
		System.out.println(levelOrderRes);
	}
}
