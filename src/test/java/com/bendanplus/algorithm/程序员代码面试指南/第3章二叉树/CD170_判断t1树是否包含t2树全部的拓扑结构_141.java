package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.*;

public class CD170_判断t1树是否包含t2树全部的拓扑结构_141 {
	static class TreeNode {
		int val;
		TreeNode parent;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public void createTree(TreeNode root, int[][] a) {
		if (root == null) {
			return;
		}
		int i = root.val;
		int l = a[i][0];
		int r = a[i][1];

		if (l != 0) {
			TreeNode leftNode = new TreeNode(l);
			root.left = leftNode;
			leftNode.parent = root;
			createTree(leftNode, a);
		}
		if (r != 0) {
			TreeNode rightNode = new TreeNode(r);
			root.right = rightNode;
			rightNode.parent = root;
			createTree(rightNode, a);
		}
	}

	boolean res = false;

	public void traversalStart(TreeNode root1, TreeNode root2) {
		if (res) {
			return;
		}
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				res = true;
				traversalJudge(root1, root2);
			}
			traversalStart(root1.left, root2);
			traversalStart(root1.right, root2);
		}
	}


	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
		boolean result=false;
		if(root1!=null&&root2!=null){
			if(root1.val==root2.val)
				result =DoseTree1HasTree2(root1,root2);
			if(!result)  //写成了 root1.val!=root2.val; 导致出错了；
				result = HasSubtree(root1.left,root2);
			if(!result)
				result = HasSubtree(root1.right, root2);
		}
		return result;
	}
	public static boolean DoseTree1HasTree2(TreeNode root1,TreeNode root2) {
		if(root2==null)  return true;
		if(root1==null)  return false;
		if(root1.val!=root2.val)
			return false;
		return DoseTree1HasTree2(root1.left, root2.left)
				&&DoseTree1HasTree2(root1.right, root2.right);
	}


	public void traversalJudge(TreeNode root1, TreeNode root2) {
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				traversalJudge(root1.left, root2.left);
				traversalJudge(root1.right, root2.right);
			} else {
				res = false;
			}
		} else if (root2 != null) {
			res = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

		String[] strN = scanner.readLine().split(" ");
		int n = Integer.parseInt(strN[0]);

		int[][] node = new int[n + 1][2];
		String[] strNode = scanner.readLine().split(" ");

		//先构建根节点
		int rootVal = Integer.parseInt(strNode[0]);
		int left = Integer.parseInt(strNode[1]);
		int right = Integer.parseInt(strNode[2]);
		node[rootVal][0] = left;
		node[rootVal][1] = right;
		TreeNode root = new TreeNode(rootVal);

		for (int i = 1; i < n; i++) {
			strNode = scanner.readLine().split(" ");
			node[Integer.parseInt(strNode[0])][0] = Integer.parseInt(strNode[1]);
			node[Integer.parseInt(strNode[0])][1] = Integer.parseInt(strNode[2]);
		}

		CD170_判断t1树是否包含t2树全部的拓扑结构_141 code11 = new CD170_判断t1树是否包含t2树全部的拓扑结构_141();
		code11.createTree(root, node);
		//scanner = new BufferedReader(new InputStreamReader(System.in));

		String[] strN1 = scanner.readLine().split(" ");
		int n1 = Integer.parseInt(strN1[0]);

		int[][] node1 = new int[n + 1][2];
		String[] strNode1 = scanner.readLine().split(" ");

		//先构建根节点
		rootVal = Integer.parseInt(strNode1[0]);
		left = Integer.parseInt(strNode1[1]);
		right = Integer.parseInt(strNode1[2]);
		node1[rootVal][0] = left;
		node1[rootVal][1] = right;
		TreeNode root1 = new TreeNode(rootVal);

		for (int i = 1; i < n1; i++) {
			strNode1 = scanner.readLine().split(" ");
			node1[Integer.parseInt(strNode1[0])][0] = Integer.parseInt(strNode1[1]);
			node1[Integer.parseInt(strNode1[0])][1] = Integer.parseInt(strNode1[2]);
		}


		code11.createTree(root1, node1);

		code11.traversalStart(root, root1);

		System.out.println(code11.res);
	}
}
