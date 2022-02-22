package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CD176在二叉树中找到两个节点的最近公共祖先 {

	static Node findCommonRoot(Node node, int t1, int t2) {
		if (node == null || node.val == t1 || node.val == t2)
			return node;

		//二叉树的后序遍历 左右根 遍历公共祖先之前一定会先遍历出 t1 t2
		Node left = findCommonRoot(node.left, t1, t2);
		Node right = findCommonRoot(node.right, t1, t2);

		return left == null ? right : (right != null ? node : left);
	}

	static class Node {
		int val;
		Node left;
		Node right;

		public Node(int val) {
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

	public static Node read() {
		int fal, lth, rth;
		fal = nextInt();
		lth = nextInt();
		rth = nextInt();
		Node node = new Node(fal);
		if (lth != 0) {
			node.left = read();
		}
		if (rth != 0) {
			node.right = read();
		}
		return node;
	}

	public static void main(String[] args) {
		int n = nextInt();
		int root = nextInt();
		Node node = read();
		int t1 = nextInt();
		int t2 = nextInt();

		Node res = findCommonRoot(node, t1, t2);
		System.out.println(res.val);
	}
}
