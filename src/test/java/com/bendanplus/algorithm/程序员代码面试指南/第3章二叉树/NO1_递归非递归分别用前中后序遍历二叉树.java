package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;


import com.bendanplus.algorithm.剑指offer.TreeNode;

import java.util.Stack;

public class NO1_递归非递归分别用前中后序遍历二叉树 {

	//中序非递归遍历
	public void inOrderUnRecur(TreeNode head) {
		System.out.println("in-order 中序遍历");
		if (head != null) {
			Stack<TreeNode> stack = new Stack<>();
			while (!stack.empty() || head != null) {
				if (head != null) {
					stack.add(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.val + " ");
					head = head.right;
				}
			}
			System.out.println("");
		}
	}

	//后续非递归遍历
	public void posOrderUnRecur(TreeNode head) {
		System.out.println("pos-order 后序遍历");
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.add(head);
		if (head == null)
			return;
		while (!stack1.empty()) {
			head = stack1.pop();
			stack2.add(head);
			if (head.left != null)
				stack1.add(head.left);
			if (head.right != null)
				stack1.add(head.right);
		}
		while (!stack2.empty())
			System.out.print(stack2.pop().val + " ");
	}


}
