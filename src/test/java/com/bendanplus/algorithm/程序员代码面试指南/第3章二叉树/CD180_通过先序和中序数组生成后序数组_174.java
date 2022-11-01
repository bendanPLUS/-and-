package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Arrays;

//给出一棵二叉树的先序和中序数组，通过这两个数组直接生成正确的后序数组。
public class CD180_通过先序和中序数组生成后序数组_174 {



	/**
	 * 先重构二叉树，然后进行后续遍历
	 */
	private static TreeNode buildTree(int[] preOrder, int[] inOrder) {
		if (preOrder.length != inOrder.length || preOrder.length == 0) return null;
		if (preOrder.length == 1) {
			return new TreeNode(preOrder[0]);
		} else {
			int rootVal = preOrder[0];
			TreeNode root = new TreeNode(rootVal);
			int idx = indexOf(inOrder, rootVal);
			root.left = buildTree(Arrays.copyOfRange(preOrder, 1, 1 + idx), Arrays.copyOfRange(inOrder, 0, idx));
			root.right = buildTree(Arrays.copyOfRange(preOrder, 1 + idx, preOrder.length), Arrays.copyOfRange(inOrder, idx + 1, inOrder.length));
			return root;
		}
	}

	private static int indexOf(int[] arr, int target) {
		int idx = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				idx = i;
				break;
			}
		}

		return idx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] params = br.readLine().split(" ");
		int[] preOrder = new int[n];
		for (int i = 0; i < n; i++) preOrder[i] = Integer.parseInt(params[i]);
		params = br.readLine().split(" ");
		int[] inOrder = new int[n];
		for (int i = 0; i < n; i++) inOrder[i] = Integer.parseInt(params[i]);
		// 重建二叉树
		TreeNode root = buildTree(preOrder, inOrder);
		// 后续遍历
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			TreeNode node = stack1.pop();
			stack2.push(node);
			if (node.left != null) stack1.push(node.left);
			if (node.right != null) stack1.push(node.right);
		}
		while (!stack2.isEmpty()) System.out.print(stack2.pop().value + " ");
	}
}
