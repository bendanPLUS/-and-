package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import com.bendanplus.algorithm.剑指offer.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 给定一颗二叉树的根节点 root，按照如下两种标准分别实现二叉树的边界节点的逆时针打印。
 * 标准一：
 * 1，根节点为边界节点。
 * 2，叶节点为边界节点。
 * 3，如果节点在其所在的层中是最左的或最右的，那么该节点也是边界节点。
 * 标准二：
 * 1，根节点为边界节点。
 * 2，叶节点为边界节点。
 * 3，树左边界延伸下去的路径为边界节点。
 * 4，树右边界延伸下去的路径为边界节点。
 */
public class CD162_打印二叉树的边界节点_95 {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	private static int nextInt() {
		try {
			st.nextToken();
			return (int) (st.nval);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static TreeNode build() {
		int fa = nextInt();
		int lch = nextInt();
		int rch = nextInt();
		TreeNode root = new TreeNode(fa);
		if (lch != 0) {
			root.left = build();
		}
		if (rch != 0) {
			root.right = build();
		}
		return root;
	}

	public static void main(String[] args) {
		int n = nextInt(), r = nextInt();
		TreeNode root = build();
		//打印标准一
		print1(root);
		System.out.println();
		//打印标准二
		print2(root, new StringBuilder());
	}

	//打印标准一
	public static void print1(TreeNode root) {
		if (root == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		//1 获取二叉树的高度
		int height = getHeight(root, 0);
		TreeNode[][] edgeNode = new TreeNode[height][2];
		//2 用一个二维数组 记录每一层的最左节点 和 最右节点
		setEdgeNode(root, 0, edgeNode);
		for (int i = 0; i < edgeNode.length; ++i) {
			sb.append(edgeNode[i][0].val + " ");
		}
		//3 先序遍历 递归 寻找叶子节点 并且不是边界节点 最左节点or最右节点
		leafInNode(root, 0, edgeNode, sb);
		for (int i = edgeNode.length - 1; i >= 0; --i) {
			if (edgeNode[i][0] != edgeNode[i][1]) sb.append(edgeNode[i][1].val + " ");
		}
		System.out.print(sb);
	}

	//获取二叉树的高度 递归
	public static int getHeight(TreeNode root, int h) {
		if (root == null) return h;
		return Math.max(getHeight(root.left, h + 1), getHeight(root.right, h + 1));
	}

	//用一个二维数组 记录每一层的最左节点 和 最右节点
	//递归方式的先序遍历
	public static void setEdgeNode(TreeNode root, int h, TreeNode[][] edgeNode) {
		if (root == null) return;
		//递归方式的先序遍历
		//记录最左节点 每层最左节点 如果为null才进行赋值 每一层遍历的第一个节点就是
		edgeNode[h][0] = edgeNode[h][0] == null ? root : edgeNode[h][0];
		//记录最右节点 每层最右节点 每一层遍历的最后一个节点就是 ，所以要一直赋值
		edgeNode[h][1] = root;
		setEdgeNode(root.left, h + 1, edgeNode);
		setEdgeNode(root.right, h + 1, edgeNode);
	}

	//先序遍历 递归 寻找叶子节点 并且不是边界节点 最左节点or最右节点
	public static void leafInNode(TreeNode root, int h, TreeNode[][] edgeNode, StringBuilder sb) {
		if (root == null) return;
		//TODO 先序遍历 递归
		//寻找叶子节点 并且不是边界节点 最左节点or最右节点
		if (root.left == null && root.right == null && root != edgeNode[h][0] && root != edgeNode[h][1])
			sb.append(root.val + " ");
		leafInNode(root.left, h + 1, edgeNode, sb);
		leafInNode(root.right, h + 1, edgeNode, sb);
	}

	//---------------------------------------------------------------------------------

	//打印标准二
	public static void print2(TreeNode root, StringBuilder sb) {
		if (root == null) {
			return;
		}
		sb.append(root.val + " ");
		if (root.left != null && root.right != null) {
			printLeftEdge(root.left, true, sb);
			printRightEdge(root.right, true, sb);
		} else {
			print2(root.left != null ? root.left : root.right, sb);
		}
		System.out.print(sb);
	}

	public static void printLeftEdge(TreeNode root, boolean print, StringBuilder sb) {
		if (root == null) {
			return;
		}
		if (print || (root.left == null && root.right == null)) {
			sb.append(root.val + " ");
		}
		printLeftEdge(root.left, print, sb);
		printLeftEdge(root.right, print && (root.left == null ? true : false), sb);
	}

	public static void printRightEdge(TreeNode root, boolean print, StringBuilder sb) {
		if (root == null) {
			return;
		}
		printRightEdge(root.left, print && root.right == null ? true : false, sb);
		printRightEdge(root.right, print, sb);
		if (print || (root.left == null && root.right == null)) {
			sb.append(root.val + " ");
		}
	}
}


