package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class CD165_在二叉树中找到累加和为指定值的最长路径 {


	private static int res = 0;
	private static Map<Integer, Integer> sumMap = new HashMap<>();

	public static void main(String[] args) {
		int n = nextInt();
		int root = nextInt();
		Node node = read();
		int sum = nextInt();
		sumMap.put(0, 0);// 设置第0层的虚拟节点，和为0
		traversal(node, sum, 0, 1);
		System.out.println(res);
	}

	static void traversal(Node node, int sum, int preSum, int level) {
		if (node == null) return;

		// 1. 求前缀和，并加入sumMap，不存在时才加 只添加最早出现的 的 curSum --> level
		int curSum = preSum + node.val;
		if (!sumMap.containsKey(curSum)) {
			sumMap.put(curSum, level);
		}

		// 2. 判断是否存在前缀满足curSum - sum, 保存最大level差
		if (sumMap.containsKey(curSum - sum)) {
			res = Math.max(res, level - sumMap.get(curSum - sum));
		}

		// 3. 递归搜索所有节点
		traversal(node.left, sum, curSum, level + 1);
		traversal(node.right, sum, curSum, level + 1);

		// 4. 返回上一层前，删除 回溯在当前level加入的路径
		if (level == sumMap.get(curSum)) sumMap.remove(curSum);
	}

	static class Node {
		int val;
		Node left;
		Node right;

		public Node(int val) {
			this.val = val;
		}
	}

	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static int nextInt() {
		try {
			st.nextToken();
			return (int) st.nval;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Node read() {
		int fal, lth, rth, val;
		fal = nextInt();
		lth = nextInt();
		rth = nextInt();
		val = nextInt();
		Node node = new Node(val);
		if (lth != 0) {
			node.left = read();
		}
		if (rth != 0) {
			node.right = read();
		}
		return node;
	}
}

