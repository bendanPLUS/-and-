package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 面试题32_JZ60把二叉树打印成多行 {

	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (pRoot == null) return ret;
		queue.add(pRoot);
		while (!queue.isEmpty()) {
			int size = queue.size();
			ArrayList<Integer> list = new ArrayList<>();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
			ret.add(list);
		}
		return ret;
	}

	public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (pRoot == null) return ret;
		queue.add(pRoot);
		boolean reverse = false;
		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList<>();
			int cnt = queue.size();
			while (cnt-- > 0) {
				TreeNode node = queue.poll();
				if (node == null) continue;
				list.add(node.val);
				queue.add(node.left);
				queue.add(node.right);
			}
			if (reverse) Collections.reverse(list);
			reverse = !reverse;
			if (list.size() != 0) ret.add(list);
		}
		return ret;
	}
}
