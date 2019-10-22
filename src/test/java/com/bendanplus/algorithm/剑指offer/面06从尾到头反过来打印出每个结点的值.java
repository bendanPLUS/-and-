package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Stack;

public class 面06从尾到头反过来打印出每个结点的值 {
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		ListNode node = listNode;
		while (node != null) {
			stack.add(node.val);
			node = node.next;
		}
		while (!stack.isEmpty())
			list.add(stack.pop());
		return list;
	}
	//递归方法
	public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
		ArrayList<Integer> list = new ArrayList<>();
		ListNode node = listNode;
		if (node != null) {
			list = printListFromTailToHead1(node.next);
			list.add(node.val);
		}
		return list;
	}
}