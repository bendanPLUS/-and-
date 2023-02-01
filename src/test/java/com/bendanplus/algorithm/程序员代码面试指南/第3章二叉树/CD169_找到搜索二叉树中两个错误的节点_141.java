package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

/**
 * 描述
 * 一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，
 * 请按升序输出这两个错误节点的值。(每个节点的值各不相同)
 * <p>
 * 输入描述：
 * 第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
 * <p>
 * 以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。
 * (如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
 * <p>
 * ps:节点的编号就是该节点的值。
 * 输出描述：
 * 请按升序输出这两个错误节点的值。
 */
public class CD169_找到搜索二叉树中两个错误的节点_141 {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

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
		int[] res = getTwoErrNodes
				(root);
		System.out.print(res[0] + " " + res[1]);
	}


	/**
	 * 题解：
	 * 第一个错误节点：第一次降序时较大的节点
	 * 第二个错误节点：最后一次降序时较小的节点
	 * 所以只需要改写一个基本的中序遍历
	 */
	//利用中序遍历
	public static int[] getTwoErrNodes(TreeNode root) {
		if (root == null) {
			return null;
		}
		int[] error = new int[2];
		TreeNode pre = null;
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				if (pre != null && pre.value > root.value) {
					//较大的错误节点
					error[0] = error[0] == 0? pre.value : error[0];
					//较小的错误节点
					error[1] = root.value;
					//所以 error[0] > error[1]
				}
				pre = root;
				root = root.right;
			}
		}
		return error;
	}

}


class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value) {
		this.value = value;
	}
}
