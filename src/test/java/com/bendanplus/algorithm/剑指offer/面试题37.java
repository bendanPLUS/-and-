package com.bendanplus.algorithm.剑指offer;

/**
 * 请实现两个函数,分别用来序列化和反序列化二叉树
 */
public class 面试题37 {
	int index = -1;
	String Serialize(TreeNode root) {
		StringBuffer res = new StringBuffer();
		if (root == null) {
			res.append("#,");
			return res.toString();
		}
		res.append(root.val + ",");
		res.append(Serialize(root.left));
		res.append(Serialize(root.right));
		return res.toString();
	}

	TreeNode Deserialize(String str) {
		index++;
		String[] s = str.split(",");
		TreeNode node = null;
		if (!s[index].equals("#")) {
			node = new TreeNode(Integer.valueOf(s[index]));
			node.left = Deserialize(str);
			node.right = Deserialize(str);
		} else {
			node = null;
		}
		return node;
	}
}
