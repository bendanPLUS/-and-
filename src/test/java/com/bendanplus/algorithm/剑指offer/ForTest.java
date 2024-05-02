package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class ForTest {

    @Test
    public void test() {
        int[] nums = {4, 5, 1, 6, 2, 7, 3, 8};
        // List<Integer> leastNumbers = getLeastNumbers(nums, 4);
        //String res = ReverseSentence("student a am  1");
        //log.info("[结果={}]", res);
        String s = new String("sdajjjsdsabcdefghi");
        log.info("结果等于 {}", longestSubstring(s));
        //丑数
        int index = 77;
        log.info("第x丑数是:{}", uglyNumber(index));
    }

    @Test
    public void test面试题21() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] res = reOrderArray(array);
        log.info("array:{}", res);
    }

    //面试题21_JZ21_调整数组顺序使奇数位于偶数前面_快排
    private int[] reOrderArray(int[] array) {
        if (array == null || array.length == 0) return null;
        int l = 0, h = 0;
        while (l < array.length) {
            //找到一个偶数
            while ((array[l] & 1) == 1) h = l++ + 1;
            //找一个奇数
            while (h < array.length && ((array[h] & 1) == 0)) h++;
            if (h >= array.length) break;
            int tmp = array[h];
            for (int i = h; i > l; i--)
                array[i] = array[i - 1];
            array[l] = tmp;
        }
        return array;
    }

    //面试题22_JZ22_链表中倒数最后k个结点
    public ListNode FindKthToTail(ListNode pHead, int k) {
        return null;
    }


    // 面试题23_JZ55链表中环的入口
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        return null;
    }

    // 面试题24_JZ24_反转链表
    public ListNode ReverseList(ListNode head) {
        return null;
    }

    //面试题25_JZ25_合并两个排序的链表
    //输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }
        if (list1 == null) cur.next = list2;
        if (list2 == null) cur.next = list1;
        return head.next;
    }


    //面试题26_JZ26_树的子结构 我们约定空树不是任意一个树的子结构
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return DoseTree1HasTree2(root1, root2) || DoseTree1HasTree2(root1.left, root2) || DoseTree1HasTree2(root1.right, root2);
    }

    private boolean DoseTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        return root1.val == root2.val ? DoseTree1HasTree2(root1.left, root2.left) && DoseTree1HasTree2(root1.right, root2.right) : false;
    }

    //面试题27_JZ27_二叉树的镜像
    public TreeNode Mirror(TreeNode pRoot) {
        if (pRoot == null) return null;
        TreeNode left = Mirror(pRoot.left);
        TreeNode right = Mirror(pRoot.right);
        pRoot.left = right;
        pRoot.right = left;
        return pRoot;
    }

    //面试题28_JZ28对称的二叉树
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isSymmetrical(root1.left, root1.right) && isSymmetrical(root1.right, root1.left);
    }

    //面试题30_JZ30_包含min函数的栈
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    //面试题31_JZ31_栈的压入弹出序列

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (index < popA.length && stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    //面试题32_JZ32_从上往下打印二叉树
    public List<List<Integer>> printFromTopToBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        boolean flag = false;
        while ((size = queue.size()) != 0) {
            List<Integer> list = new ArrayList<>();
            while (size-- != 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (flag = !flag) Collections.reverse(list);
            res.add(list);
        }
        return res;
    }

    // 面试题33_JZ33_二叉搜索树的后序遍历序列
    // 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    public boolean VerifySquenceOfBST(int[] sequence, int left, int right) {
        if (left >= right) return true;
        int index = left;
        int root = sequence[right];
        for (; index < right; index++)
            if (root < sequence[index]) break;
        for (int j = index; j < right; j++)
            if (root > sequence[j]) return false;

        return VerifySquenceOfBST(sequence, left, index - 1) && VerifySquenceOfBST(sequence, index, right - 1);
    }


    //面试题34_JZ34_二叉树中和为某一值的路径
    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null) return;
        path.add(node.val);
        //叶子节点 左子树为null 右子树为null
        if ((target -= node.val) == 0 && node.left == null && node.right == null) result.add(new ArrayList<>(path));
        else {
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        path.remove(path.size() - 1);
    }

    // 面试题35_JZ35_复杂链表的复制
    /*1.克隆+连接 2.建立random链接 3.拆分*/
    public RandomListNode Clone(RandomListNode pHead) {
        return null;
    }

    TreeNode pre;
    TreeNode head;

    //面试题36_JZ36_二叉搜索树与双向链表
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        Convert(pRootOfTree.left);
        //中序遍历
        if (pre == null) pre = head = pRootOfTree;
        else {
            TreeNode cur = pRootOfTree;
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }
        Convert(pRootOfTree.right);
        return head;
    }


    //面试题40_JZ40_最小的K个数
    public List<Integer> getLeastNumbers(int[] input, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if (input == null || input.length == 0 || k < 0) return res;
        //大顶堆 超过容量 值比较大的数就弹出
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        for (int i = 0; i < input.length; i++) {
            minHeap.add(input[i]);
            if (minHeap.size() > k) minHeap.poll();
        }
        for (Integer num : minHeap)
            res.add(num);
        return res;
    }

    //面试题42_JZ42_连续子数组的最大和
    public int FindGreatestSumOfSubArray(int[] array) {


        return -1;
    }

    public int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return -1;
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < array.length; i++) {
            curSum = curSum > 0 ? curSum + array[i] : array[i];
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    // 面试题46_JZ46_把数字翻译成字符串
    public int parseNumber(String nums) {
        if (nums == null || nums.length() == 0) return -1;
        // dp[i]的含义: 区间[i , nums.length()-1]上把数字翻译成字符串 出现多少种可能  dp[i]=dp[i-1]+g* dp[i-2]
        int[] dp = new int[nums.length() + 1];
        dp[dp.length - 1] = dp[dp.length - 2] = 1;
        int g;
        for (int i = nums.length() - 2; i >= 0; i--) {
            int num = Integer.parseInt(nums.charAt(i) + "" + nums.charAt(i + 1));
            g = (num >= 10 && num <= 25) ? 1 : 0;
            dp[i] = dp[i + 1] + g * dp[i + 2];
        }
        return dp[0];
    }

    // 面试题48_JZ48_最长不含重复字符的子字符串
    public static int longestSubstring(String s) {
        if (s == null || s.length() == 0) return -1;
        //以i为结尾,最长不含重复字符的子字符串长度
        int[] dp = new int[s.length()];
        int maxSub = dp[0] = 1;
        // first 1 --> s.length()-1
        for (int i = 1; i < s.length(); i++) {
            int index = i - 1;
            for (; index >= i - dp[i - 1]; index--)
                if (s.charAt(index) == s.charAt(i)) break;
            dp[i] = i - index;
            maxSub = Math.max(dp[i], maxSub);
        }
        return maxSub;
    }

    //面试题49_JZ49_丑数
    /*  把只包含因子 2、3 和 5 的数称作丑数 从1开始 1 2 3 4 5 6 ...... */
    public int uglyNumber(int index) {
        if (index < 7) return index;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < index; i++) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (m5 == min) i5++;
        }
        return list.get(list.size() - 1);
    }


    //面试题58_JZ43JZ44左旋转字符串和翻转单词序列
    public String ReverseSentence(String str) {


        return null;
    }


    public String reverseSentence(String str) {
        if (str == null || str.trim().length() == 0) return str;
        char[] charArray = str.toCharArray();
        reverse(charArray, 0, charArray.length - 1);
        int left = -1;
        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] == ' ') reverse(charArray, left + 1, (left = i) - 1);
        if (charArray[charArray.length - 1] != ' ') reverse(charArray, left + 1, charArray.length - 1);
        return new String(charArray);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) swap(chars, left++, right--);
    }

    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }


}
