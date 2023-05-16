package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.*;

public class 面试题32_JZ77按之字形顺序打印二叉树 {

	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (pRoot == null) return result;
		queue.add(pRoot);
		// 初始偶数层 0层 正序 不倒序
		boolean reverse = false;
		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList<>();
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}

			//奇数层倒序  z字型 之字形顺序打印
			if (reverse) Collections.reverse(list);
			reverse = !reverse;
			result.add(list);
		}

		return result;
	}

	public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (pRoot == null) return result;
		Stack<TreeNode> s1 = new Stack<>();//奇数
		Stack<TreeNode> s2 = new Stack<>();//偶数
		s1.add(pRoot);
		int layer = 1;
		while (!s1.isEmpty() || !s2.isEmpty()) {
			ArrayList<Integer> oneLayerList = new ArrayList<>();
			if ((layer & 1) == 1) {
				while (!s1.isEmpty()) {
					TreeNode node = s1.pop();
					oneLayerList.add(node.val);
					if (node.left != null) s2.add(node.left);
					if (node.right != null) s2.add(node.right);
				}
				if (!oneLayerList.isEmpty()) layer++;
				result.add(oneLayerList);
			} else {
				while (!s2.isEmpty()) {
					TreeNode node = s2.pop();
					oneLayerList.add(node.val);
					if (node.right != null) s1.add(node.right);
					if (node.left != null) s1.add(node.left);
				}
				if (!oneLayerList.isEmpty()) {
					layer++;
					result.add(oneLayerList);
				}
			}
		}
		return result;
	}


}
