package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class 剑指offer {
	@Test
	public void testCheckActivityApi() {
		System.out.println("--------");
		log.info("----log-----");
	}


	//面试题12_JZ65矩阵中的路径
	public boolean 面试题12hasPath(char[][] matrix, String word) {
		if (matrix == null || matrix.length == 0 || StringUtils.isBlank(word)) return false;

		//创建一个标记矩阵
		boolean[][] mark = new boolean[matrix.length][matrix[0].length];
		boolean res = false;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				res = 面试题12judge(matrix, mark, word, i, j, 0);
				if (res) return res;
			}
		}
		return false;
	}

	public boolean 面试题12judge(char[][] matrix, boolean[][] visited, String word, int i, int j, int k) {
		if (k == word.length()) return true;
		boolean res = false;
		if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && visited[i][j] == false && matrix[i][j] == word.charAt(k)) {
			visited[i][j] = true;
			res = 面试题12judge(matrix, visited, word, i + 1, j, k + 1) || 面试题12judge(matrix, visited, word, i, j + 1, k + 1) || 面试题12judge(matrix, visited, word, i - 1, j, k + 1) || 面试题12judge(matrix, visited, word, i, j - 1, k + 1);
			visited[i][j] = false;
		}
		return res;
	}

	public int 面试题14_JZ67剪绳子cutRope(int n) {
		if (n < 2) return 0;
		if (n == 2) return 1;
		if (n == 3) return 2;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		int max = 0;
		for (int i = 4; i < dp.length; i++) {
			max = 0;
			for (int j = 1; j <= i / 2; j++) {
				int tmp = dp[j] * dp[i - j];
				max = Math.max(max, tmp);
			}
			dp[i] = max;
		}
		return dp[n];
	}


	public int 面试题15二进制中1的个数NumberOf1(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n &= (n - 1);
		}
		return count;
	}

	//面试题16 数值的整数次方
	@Test
	private double Power(double base, int exponent) {
		if (powerEqual(base, 0.0) && exponent < 0) {
			return 0.0;
		}
		int absExponent = exponent;
		if (exponent < 0) absExponent = -exponent;
		double res = getPower(base, absExponent);
		if (exponent < 0) res = 1 / res;
		return res;
	}

	private double getPower(double base, int exponent) {
		if (exponent == 0) return 1;
		if (exponent == 1) return base;

		double res = getPower(base, exponent >> 1);
		res *= res;
		if ((exponent & 1) == 1) res *= base;
		return res;
	}

	private boolean powerEqual(double base1, double base2) {
		if (base1 - base2 <= 0.0000000000001 && base2 - base1 <= 0.000000000000001) return true;
		return false;
	}

	//面试题17 打印从1到最大的n位数
	@Test
	public int[] printNumbers(int n) {
		// write code here
		int len = (int) Math.pow(10, n);
		int index = 1;
		int[] res = new int[len - 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = index++;
		}
		return res;
	}


	//面试题18  删除 排序 链表中重复的节点
	//1 递归的解法 通过测试
	private ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null || pHead.next == null) {
			return pHead;
		}
		if (pHead.val != pHead.next.val) {
			pHead.next = deleteDuplication(pHead.next);
			return pHead;
		} else {
			ListNode nextNode = pHead.next;
			while (nextNode != null && nextNode.val == pHead.val) {
				nextNode = nextNode.next;
			}
			return deleteDuplication(nextNode);
		}
	}

	//2 循环的解法 通过测试
	private ListNode deleteDuplication1(ListNode pHead) {
		if (pHead == null) {
			return null;
		}
		ListNode preHead = new ListNode(pHead.val - 1);
		preHead.next = pHead;
		ListNode preNode = preHead;
		ListNode node = preHead.next;
		while (node != null && node.next != null) {
			if (node.val == node.next.val) {
				while (node != null && node.next != null && node.val == node.next.val) {
					node = node.next;
				}
				node = node.next;
				preNode.next = node;
			} else {
				preNode = node;
				node = node.next;
			}
		}
		return preHead.next;
	}

	//面试题21  调整数组顺序使奇数位于偶数前面 并保证偶数与奇数之间相对位置不变
	@Test
	public void test面试题21() {
		int[] array = {1, 2, 3, 4, 5, 6, 7};
		reOrderArray(array);
	}

	private void reOrderArray(int[] array) {
		if (array == null || array.length == 0) return;
		//保证顺序的交换ab的位置
		int a = 0;
		int b = a + 1;
		while (a < array.length) {
			//寻找的偶数位
			while (a < array.length && (array[a] & 1) == 1) a++;
			//寻找的奇数
			b = a + 1;
			while (b < array.length && (array[b] & 1) != 1) b++;
			//交换ab的位置
			if (b < array.length) {
				int tmp = array[b];
				for (int i = b; i > a; i--) {
					array[i] = array[i - 1];
				}
				array[a] = tmp;
				a++;
			} else {
				break;
			}
		}
	}

	//面试题22 链表中倒数第k个节点
	private ListNode FindKthToTail(ListNode head, int k) {
		if (head == null || k == 0) {
			return null;
		}
		ListNode p1 = head;
		ListNode p2 = head;
		for (int i = 0; i < k - 1; i++) {
			if (p1 != null) {
				p1 = p1.next;
			} else {
				return null;
			}
		}
		if (p1 == null) {
			return null;
		}
		while (p1.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}

		return p2;
	}

	//面试题23 链表中环的入口
	private ListNode EntryNodeOfLoop1(ListNode pHead) {
		if (pHead == null || pHead.next == null) return null;
		ListNode fast = pHead;
		ListNode slow = pHead;
		do {
			fast = fast.next.next;
			slow = slow.next;
		} while (fast != slow);
		fast = pHead;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	private ListNode EntryNodeOfLoop(ListNode pHead) {
		int count = 1;
		final ListNode meetingNode = MeetingNode(pHead);
		if (meetingNode == null) return null;
		ListNode p = meetingNode;
		while (p != meetingNode) {
			p = p.next;
			count++;
		}
		ListNode p1 = pHead;
		for (int i = 0; i < count; i++) {
			p1 = p1.next;
		}
		ListNode p2 = pHead;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	private ListNode MeetingNode(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		//每次走一步
		ListNode p1 = head.next;
		//每次走2步
		ListNode p2 = p1.next;
		while (p2 != null && p1 != null) {
			if (p2 == p1) {
				return p1;
			} else {
				p1 = p1.next;
				p2 = p2.next;
			}
			if (p2.next != null) {
				p2 = p2.next;
			}
		}
		return null;
	}

	//面试题24 反转链表
	private ListNode ReverseList(ListNode head) {
		if (head == null) return null;
		ListNode pre = null;
		ListNode node = head;
		ListNode next;
		ListNode reverseNode = head;
		while (node != null) {
			next = node.next;
			if (next == null) {
				reverseNode = node;
			}
			node.next = pre;
			pre = node;
			node = next;
		}
		return reverseNode;
	}

	//面试题25  合并两个排序的链表
	private ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		ListNode mergeHead = null;
		ListNode currentNode = null;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				if (mergeHead == null) {
					mergeHead = currentNode = list1;
				} else {
					currentNode.next = list1;
					currentNode = currentNode.next;
				}
				list1 = list1.next;
			} else {
				if (mergeHead == null) {
					mergeHead = currentNode = list2;
				} else {
					currentNode.next = list2;
					currentNode = currentNode.next;
				}
				list2 = list2.next;
			}
		}
		if (list1 == null) {
			currentNode.next = list2;
		} else {
			currentNode.next = list1;
		}
		return mergeHead;
	}

	private class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;

		}
	}

	//面试题26 树的子结构 输入两棵树 判断a树是否是b树的子结构
	private boolean HasSubtree(TreeNode root1, TreeNode root2) {
		boolean res = false;
		if (root1 != null && root2 != null) {
			if (root1.val == root1.val) {
				res = DoseTree1HasTree2(root1, root2);
			}
			if (!res) {
				res = HasSubtree(root1.left, root2);
			}
			if (!res) {
				res = HasSubtree(root1.right, root2);
			}
		}
		return res;
	}

	private boolean DoseTree1HasTree2(TreeNode root1, TreeNode root2) {
		if (root2 == null) return true;
		if (root1 == null) return false;
		if (root1.val != root2.val) return false;
		return DoseTree1HasTree2(root1.left, root2.left) && DoseTree1HasTree2(root1.right, root2.right);
	}

	//面试题27  输入一颗二叉树 该函数输出它的镜像
	private void Mirror(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) return;
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		if (root.left != null) Mirror(root.left);
		if (root.right != null) Mirror(root.right);
	}

	//面试题28 判断一颗二叉树是不是对称的 如果一颗二叉树和他的镜像一样 那么他就是对称的二叉树
	//空壳方法
	private boolean isSymmetrical(TreeNode pRoot) {
		return isSymmetrical(pRoot, pRoot);
	}

	private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.left, root2.right);
	}

	//面试题29  输入一个矩阵 按照从外向里顺时针依次打印每个数字
	public static ArrayList<Integer> arr = new ArrayList<>();

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		//边界条件的判断
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;

		//行数
		int rows = matrix.length;
		//列数
		int colums = matrix[0].length;
		int start = 0;
		while (rows > 2 * start && colums > 2 * start) {
			printMatrix(matrix, rows, colums, start);
			start++;
		}
		return arr;
	}


	private void printMatrix(int[][] matrix, int rows, int colums, int start) {
		int endX = rows - 1 - start;
		int endY = colums - 1 - start;
		for (int i = start; i <= endY; i++)
			arr.add(matrix[start][i]);

		if (endX - start >= 1) {
			for (int i = start + 1; i <= endX; i++)
				arr.add(matrix[i][endY]);

		}
		if (endX - start >= 1 && endY - start >= 1) {
			for (int i = endY - 1; i >= start; i--)
				arr.add(matrix[endX][i]);

		}
		if (endX - start >= 2 && endY - start >= 1) {
			for (int i = endX - 1; i >= start + 1; i--)
				arr.add(matrix[i][start]);
		}
	}

	//面试题30 定义栈的数据结构 实现找到栈的最小元素的min函数
	Stack<Integer> s1 = new Stack<>();
	Stack<Integer> s2 = new Stack<>();

	public void push(int node) {
		s1.push(node);
		if (s2.empty() || s2.peek() > node) {
			s2.push(node);
		} else {
			s2.push(s2.peek());
		}
	}

	public void pop() {
		s1.pop();
		s2.pop();
	}

	public int top() {
		return s1.peek();
	}

	public int min() {
		return s2.peek();
	}

	//面试题31 判断第二个序列是否是第一个序列的出栈顺序
	private boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA == null || pushA.length == 0 || popA == null || popA.length == 0) {
			return false;
		}
		int index = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < pushA.length; i++) {
			s.push(pushA[i]);
			while (index < popA.length && popA[index] == s.peek()) {
				index++;
				s.pop();
			}
		}
		return s.isEmpty();
	}

	//面试题32 题目一不分行从上到下打印二叉树
	private ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		if (root == null) return new ArrayList<>();
		//存储的打印数据
		ArrayList<Integer> list = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			//Retrieves and removes the head of this queue,
			final TreeNode poll = queue.poll();
			list.add(poll.val);
			if (poll.left != null) queue.offer(poll.left);
			if (poll.right != null) queue.offer(poll.right);
		}
		return list;
	}

	//面试题32 题目2 分行从上到下打印二叉树
	private ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		if (pRoot == null) return new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		ArrayList<Integer> tmp = new ArrayList<>();
		LinkedList<TreeNode> node = new LinkedList<>();

		node.add(pRoot);
		int start = 0;
		int end = 1;
		while (node != null && node.size() != 0) {
			final TreeNode first = node.pollFirst();
			end--;
			tmp.add(first.val);
			if (first.left != null) {
				node.add(first.left);
				start++;
			}
			if (first.right != null) {
				node.add(first.right);
				start++;
			}
			if (end == 0) {
				list.add(new ArrayList<Integer>(tmp));
				tmp.clear();
				end = start;
				start = 0;
			}
		}
		return list;
	}

	//面试题32 之字形打印二叉树
	private ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		int layer = 1;
		if (pRoot == null) {
			return list;
		}
		s1.push(pRoot);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			if ((layer & 1) == 1) {
				ArrayList<Integer> tmp = new ArrayList<>();
				while (!s1.empty()) {
					final TreeNode pop = s1.pop();
					tmp.add(pop.val);
					if (pop.left != null) {
						s2.add(pop.left);
					}
					if (pop.right != null) {
						s2.add(pop.right);
					}
				}
				if (!tmp.isEmpty()) {
					layer++;
					list.add(tmp);
				}
			} else {
				ArrayList<Integer> tmp = new ArrayList<>();
				while (!s2.empty()) {
					final TreeNode pop = s2.pop();
					tmp.add(pop.val);
					if (pop.right != null) {
						s1.add(pop.right);
					}
					if (pop.left != null) {
						s1.add(pop.left);
					}
				}
				if (!tmp.isEmpty()) {
					layer++;
					list.add(tmp);
				}
			}
		}
		return list;
	}

	//面试题33 输入一个整数数组,判断该数组是不是某二叉搜索树的后序遍历结果
	private boolean VerifySquenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length <= 0) {
			return false;
		}
		int root = sequence[sequence.length - 1];
		int i = 0;
		for (; i < sequence.length - 1; i++) {
			if (sequence[i] > root) {
				break;
			}
		}
		for (int j = i; j < sequence.length - 1; j++) {
			if (root >= sequence[j]) {
				return false;
			}
		}
		boolean left = true;
		if (i > 0) {
			left = VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
		}
		boolean right = true;
		if (i < sequence.length - 1) {
			right = VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length - 1));
		}
		return left && right;
	}

	//面试题34 输入一颗二叉树和一个整数,打印出二叉树中节点值的和为输入整数的所有路径.从数的根节点开始往下一直到叶子节点所经过的所有节点所形成的路径.
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
		if (root == null) {
			return arrayList;
		}
		ArrayList<Integer> arrayList1 = new ArrayList<>();
		int curSum = 0;
		FindPath1(root, target, curSum, arrayList, arrayList1);
		return arrayList;
	}

	private void FindPath1(TreeNode root, int target, int curSum, ArrayList<ArrayList<Integer>> arrayList, ArrayList<Integer> arrayList1) {
		if (root != null) {
			curSum += root.val;
			arrayList1.add(root.val);
		}
		if (target > curSum) {
			if (root.left != null) {
				FindPath1(root.left, target, curSum, arrayList, arrayList1);
			}
			if (root.right != null) {
				FindPath1(root.right, target, curSum, arrayList, arrayList1);
			}
		} else if (target == curSum) {
			if (root.left == null && root.right == null) {
				arrayList.add(new ArrayList<Integer>(arrayList1));
			}
		}
		arrayList1.remove(arrayList1.size() - 1);
	}
}
