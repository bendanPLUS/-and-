package com.bendanplus.algorithm.剑指offer;

import java.util.*;

public class 面试题32_JZ32_从上往下打印二叉树 {
    public List<List<Integer>> printFromTopToBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        boolean reverse = true;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while (size-- != 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (reverse) Collections.reverse(list);
            reverse = !reverse;
            res.add(list);
        }
        return res;
    }

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

    // 这样可以分行打印 按层打印
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode t = queue.poll();
                if (t == null) continue;
                res.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return res;
    }
}
