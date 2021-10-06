package com.bendanplus.algorithm.程序员代码面试指南.第三章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CD175在二叉树中找到一个节点的后继节点 {

	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static int nextInt() {
		try {
			st.nextToken();
			return (int) st.nval;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode parent;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode getSuccessorNode(TreeNode node) {
		if (node == null) {
			return node;
		}

		//如果node有右子树，那么后继节点就是右子树的最左节点
		if (node.right != null) {
			return getMostLeft(node.right);
		}
		//如果node没有有右子树， 则一直向上寻找一个 父节点是子节点的左节点 parent.left == node
		else {
			TreeNode parent = node.parent;
			//如果node不是父节点的左节点 就一直向上遍历
			while (parent != null && parent.left != node) {
				node = parent;
				parent = parent.parent;
			}
			return parent;
		}

	}

	public static TreeNode getMostLeft(TreeNode node) {
		if (node == null) {
			return node;
		}

		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) throws Exception {
		// 树的建立
		int size = nextInt();
		int root = nextInt();
		TreeNode node = new TreeNode(root);
		TreeNode head = buildTree(null);
		int cur = nextInt();
		TreeNode curNode = findNode(head, cur);
		TreeNode successorNode = getSuccessorNode(curNode);
		System.out.println(successorNode.val);
	}

	public static TreeNode findNode(TreeNode node, int cur) {
		if (node == null) {
			return null;
		}
		TreeNode left = findNode(node.left, cur);
		TreeNode right = findNode(node.right, cur);
		if (node.val == cur) {
			return node;
		} else {
			return left != null ? left : right;
		}
	}

	public static TreeNode buildTree(TreeNode parent) {
		int fa = nextInt();
		int left = nextInt();
		int right = nextInt();

		TreeNode node = new TreeNode(fa);
		node.parent = parent;
		if (left != 0) {
			node.left = buildTree(node);
		}
		if (right != 0) {
			node.right = buildTree(node);
		}
		return node;
	}
}
