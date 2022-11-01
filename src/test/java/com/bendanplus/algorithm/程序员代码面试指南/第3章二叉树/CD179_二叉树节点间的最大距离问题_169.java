package com.bendanplus.algorithm.程序员代码面试指南.第3章二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


// 信息体结构
class Info {
	public int maxDistance;      // 最大距离
	public int height;           // 树高
	public Info(int maxDistance, int height) {
		this.maxDistance = maxDistance;
		this.height = height;
	}
}
/**
 * 从二叉树的节点 A 出发，可以向上或者向下走，但沿途的节点只能经过一次，
 * 当到达节点 B 时，路径上的节点数叫作 A 到 B 的距离。
 * 现在给出一棵二叉树，求整棵树上每对节点之间的最大距离。
 *
 * 树型DP套路。对于以某个节点为根节点的子树，有如下两种情况：
 *https://www.nowcoder.com/practice/88331be6da0d40749b068586dc0a2a8b?tpId=101&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D4%26tpId%3D101%26type%3D101
 * path1：当前节点不参与路径，此时左右子树中最大路径较大的那个就是当前节点不参与路径时的最长路径。
 *        如上图红色节点（根节点）的子树中，最长路径就是两个黄色节点经过蓝色节点的路径，并未经过红色节点自身。
 * path2：当前节点参与路径，此时最大的路径应该是：左树高+右树高+1（1为当前节点）。
 *        如上图中蓝色节点的子树中，最长路径就是两个黄色节点经过自己的那条路径。
 * 这时候对于某个节点而言，只要向左右孩子节点收集信息，就能够计算它的最大路径，
 * 需要的信息有：(1) 左子树高度；(2) 右子树高度；(3) 左右孩子的最长路径。根据信息(3)我们可以求得当前节点不参与路径时的最长路径；
 * 根据信息(1)和(2)我们可以求得当前节点参与路径时的最长路径。而为了在根节点汇总整棵树的信息，每个节点需要把当前收集到的信息做一个汇总，方便自己的父节点使用：
 * 计算以当前节点为根节点的子树高度：height=max(left_height, right_height)+1
 * 以当前节点为根节点的子树所包含的最大路径长度：max(左子树最大路径, 右子树最大路径, 左树高+右树高+1)
 */
public class CD179_二叉树节点间的最大距离问题_169 {

	private static Info process(TreeNode root) {
		if (root == null)
			return new Info(0, 0);
		Info leftInfo = process(root.left);        // 左树信息
		Info rightInfo = process(root.right);      // 右树信息
		// 经过头节点的路径
		int path1 = leftInfo.height + rightInfo.height + 1;
		// 不经过头结点的路径
		int path2 = Math.max(leftInfo.maxDistance,
				rightInfo.maxDistance);    // 左右子树中路径大的那个
		return new Info(Math.max(path1, path2), Math.max(leftInfo.height,
				rightInfo.height) + 1);
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] params = br.readLine().split(" ");
		int n = Integer.parseInt(params[0]), rootVal = Integer.parseInt(params[1]);
		// 构建树
		HashMap<Integer, TreeNode> map = new HashMap<>();
		TreeNode root = new TreeNode(rootVal);
		map.put(rootVal, root);
		for (int i = 0; i < n; i++) {
			params = br.readLine().split(" ");
			int val = Integer.parseInt(params[0]);
			int leftVal = Integer.parseInt(params[1]);
			int rightVal = Integer.parseInt(params[2]);
			TreeNode node = map.get(val);
			if (leftVal != 0) {
				node.left = new TreeNode(leftVal);
				map.put(leftVal, node.left);
			}
			if (rightVal != 0) {
				node.right = new TreeNode(rightVal);
				map.put(rightVal, node.right);
			}
		}
		// 树形DP
		Info info = process(root);
		System.out.println(process(root).maxDistance);
	}


}