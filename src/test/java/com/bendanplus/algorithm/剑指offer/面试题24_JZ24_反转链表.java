package com.bendanplus.algorithm.剑指offer;

public class 面试题24_JZ24_反转链表 {

	public ListNode ReverseList(ListNode head) {
		if (head == null) return head;
		ListNode pre = null;
		ListNode node = head;
		ListNode next;
		ListNode reverseNode = head;
		while (node != null) {
			next = node.next;
			if (next != null) reverseNode = next;
			node.next = pre;
			pre = node;
			node = next;
		}
		return reverseNode;
	}
}
