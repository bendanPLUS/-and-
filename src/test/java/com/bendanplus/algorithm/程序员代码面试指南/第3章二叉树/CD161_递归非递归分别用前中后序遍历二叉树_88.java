package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;


import com.bendanplus.algorithm.剑指offer.TreeNode;

import java.util.Stack;

public class CD161_递归非递归分别用前中后序遍历二叉树_88 {


	/**
	 * 递归方式
	 * 方法名称 pre/in/post-Order-Recur   recursion递归
	 */
	//前序递归遍历
	public void preOrderRecur(TreeNode head) {
		if (head == null) return;
		//根节点
		System.out.println(head.val + "");
		//左节点
		preOrderRecur(head.left);
		//右节点
		preOrderRecur(head.right);
	}

	//中序递归遍历
	public void inOrderRecur(TreeNode head) {
		if (head == null) return;
		//左节点
		inOrderRecur(head.left);
		//根节点
		System.out.println(head.val + "");
		//右节点
		inOrderRecur(head.right);
	}

	//后序递归遍历
	public void postOrderRecur(TreeNode head) {
		if (head == null) return;
		//左节点
		inOrderRecur(head.left);
		//右节点
		inOrderRecur(head.right);
		//根节点
		System.out.println(head.val + "");
	}


	/**
	 * 非递归方式
	 * 递归到方式无非就是利用函数栈来保存信息， 我们可以自己申请数据结构来保存信息，也可实现递归的功能
	 *
	 * @param head
	 */
	//非递归 前序遍历  弹出节点后 先插入右孩 再插入左孩
	public void preOrderUnRecur(TreeNode head) {
		if (head == null) return;
		System.out.println("pre-order 前序遍历");
		//申请个栈用于存储数据
		Stack<TreeNode> stack = new Stack();
		stack.add(head);
		while (!stack.empty()) {
			//弹出一个元素
			final TreeNode cur = stack.pop();
			//打印弹出的元素
			System.out.println(cur.val + "");
			if (cur.right != null) stack.add(cur.right);
			if (cur.left != null) stack.add(cur.left);
		}
		System.out.println("");
	}

	//中序非递归遍历
	public void inOrderUnRecur(TreeNode head) {
		if (head == null) return;
		System.out.println("in-order 中序遍历");
		Stack<TreeNode> stack = new Stack<>();
		//只要有一个满足条件即可
		while (head != null || !stack.empty()) {
			//步骤1:现将head的所有左子节点遍历进入栈
			while (head != null) {
				stack.add(head);
				head = head.left;
			}
			//步骤2：然后右子树重复 步骤1
			if (!stack.empty()) {
				head = stack.pop();
				System.out.print(head.val + " ");
				head = head.right;
			}
		}
		/**
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
		 */
		System.out.println("");
	}

	//后续非递归遍历
	public void posOrderUnRecur(TreeNode head) {
		System.out.println("pos-order 后序遍历");
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.add(head);
		if (head == null) return;
		while (!stack1.empty()) {
			head = stack1.pop();
			stack2.add(head);
			if (head.left != null) stack1.add(head.left);
			if (head.right != null) stack1.add(head.right);
		}
		while (!stack2.empty()) System.out.print(stack2.pop().val + " ");
	}


}
