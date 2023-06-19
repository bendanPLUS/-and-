package com.bendanplus.algorithm.剑指offer;

/**
 * 输入一颗二叉搜索树,将该二叉搜索树转换成一个排序的双向链表.
 * 要求不能创建任何新的节点,只能调整树中节点指针的指向
 */
public class 面试题36_JZ36_二叉搜索树与双向链表 {
    TreeNode preNode = null;
    TreeNode LinkedHead = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        TreeNode node = pRootOfTree;
        Convert(node.left);
        if (preNode == null) {
            preNode = node;
            LinkedHead = node;
        } else {
            preNode.right = node;
            //这一步不是连着呢么?
            node.left = preNode;
            preNode = node;
        }
        Convert(node.right);
        return LinkedHead;
    }
}
