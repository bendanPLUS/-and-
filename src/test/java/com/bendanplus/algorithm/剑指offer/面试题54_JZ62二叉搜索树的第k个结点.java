package com.bendanplus.algorithm.剑指offer;


/**
 * 二叉查找树的第 K 个结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）
 * 中，按结点数值大小顺序第三小结点的值为4。
 */
public class 面试题54_JZ62二叉搜索树的第k个结点 {

    int index = 0;
    TreeNode node = new TreeNode(-1);

    TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 0) return null;
        //中序遍历 递归处理
        node = kthNode(pRoot.left, k);
        if (node != null) return node;
        index++;
        if (index == k) return pRoot;
        node = kthNode(pRoot.right, k);
        if (node != null) return node;
        return null;
    }


    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 0) return null;
        node = KthNode(pRoot.left, k);
        if (node != null) return node;

        index++;
        if (index == k) return pRoot;

        node = KthNode(pRoot.right, k);
        if (node != null) return node;
        return null;
    }
}
