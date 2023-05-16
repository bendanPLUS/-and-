package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 面试题32_JZ32_从上往下打印二叉树 {

	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();
		if (root == null) return list;
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			list.add(node.val);
			if (node.left != null) queue.add((node.left));
			if (node.right != null) queue.add((node.right));
		}
		return list;
	}

	public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		ArrayList<Integer> ret = new ArrayList<>();

		queue.add(root);
		while (!queue.isEmpty()) {
			int cnt = queue.size();
			while (cnt-- > 0) {
				TreeNode t = queue.poll();
				if (t == null) continue;
				ret.add(t.val);
				queue.add(t.left);
				queue.add(t.right);
			}
		}
		return ret;
	}
}
