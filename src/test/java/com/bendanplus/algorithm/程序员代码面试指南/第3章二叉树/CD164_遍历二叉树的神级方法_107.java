package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD164_遍历二叉树的神级方法_107 {


	/**
	 * 先序遍历
	 */
	public static void preOrder(Node root) {
		if (null == root) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		while (root != null) {
			Node mostRight = root.left;
			// 左子树不为空
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != root) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					sb.append(root.value).append(" ");
					mostRight.right = root;
					root = root.left;
					continue;
				}
				if (mostRight.right == root) {
					mostRight.right = null;
				}
			} else {
				sb.append(root.value).append(" ");
			}
			root = root.right;
		}
		System.out.println(sb.toString());
	}

	/**
	 * Morris中序遍历的过程
	 * 步骤1：假设当前子树的头节点为h，让h的左子树的最右节点的right指针指向h，
	 * 然后h的左子树继续步骤1的处理过程，直到遇到某一个没有左子树的节点，记为node。进入步骤2；
	 * Node cur1 = root;
	 * Node cur2 = null;
	 * cur2 = cur1.left;
	 * if (cur2 != null) {
	 * while (cur2.right != null && cur2.right != cur1) cur2 = cur2.right;
	 * if (cur2.right == null) {
	 * cur2.right = cur1;
	 * cur1 = cur1.left;
	 * continue;
	 * }            }
	 * 步骤2：从node开始通过每个节点的right指针进行移动，并依次移动，假定移动到节点为cur，
	 * 对每一个cur节点都判断cur节点的左子树中最右节点是否指向cur。
	 * 1，如果指向cur，则让cur节点的左子树中的最右节点的rigth指向null ，
	 * 然后打印，继续通过cur的right指针移动到下一个节点，重复步骤2；
	 * 2，如果不是指向cur，以cur为头节点的子树 重复步骤1；
	 */

//              root = root.left;
//				sb.append(root.value).append(" ");
//				root = root.right;

	/**
	 * 中序遍历
	 */
	public static void inOrder(Node root) {
		if (null == root) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		while (root != null) {
			Node mostRight = root.left;

			if (mostRight != null) {

				while (mostRight.right != null && mostRight.right != root) mostRight = mostRight.right;

				if (mostRight.right == null) {
					mostRight.right = root;
					root = root.left;
					continue;
				}

				if (mostRight.right == root) mostRight.right = null;
				sb.append(root.value).append(" ");
				//通过root的右指针进行移动
				root = root.right;
			}
		}
		System.out.println(sb.toString());
	}


	//后序 有左子树第二次到达时 逆序打印数的右边界 + 打印整棵树的右边界
	//后序 有左子树第二次到达时 逆序打印数的右边界 + 打印整棵树的右边界

	/**
	 * 后序遍历
	 */
	public static void postOrder(Node root) {
		if (null == root) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		Node head = root;
		while (root != null) {
			Node mostRight = root.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != root) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = root;
					root = root.left;
					continue;
				} else {
					mostRight.right = null;
					printEdge(root.left, sb);
				}
			}
			root = root.right;
		}
		printEdge(head, sb);
		System.out.println(sb.toString());
	}

	private static void printEdge(Node node, StringBuilder sb) {
		Node tail = reverse(node);
		Node cur = tail;
		while (cur != null) {
			sb.append(cur.value).append(" ");
			cur = cur.right;
		}
		reverse(tail);
	}

	private static Node reverse(Node node) {
		Node pre = null, next;
		while (node != null) {
			next = node.right;
			node.right = pre;
			pre = node;
			node = next;
		}
		return pre;
	}

	private static Node createTree(BufferedReader bf) throws IOException {
		String[] str = bf.readLine().split(" ");
		Node root = new Node(Integer.valueOf(str[0]));
		if (!str[1].equals("0")) {
			root.left = createTree(bf);
		}
		if (!str[2].equals("0")) {
			root.right = createTree(bf);
		}
		return root;
	}

	private static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Node root = null;
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
			bf.readLine();
			root = createTree(bf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		preOrder(root);
		inOrder(root);
		postOrder(root);
	}

}
