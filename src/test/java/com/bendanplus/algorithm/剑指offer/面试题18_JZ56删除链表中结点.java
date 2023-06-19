package com.bendanplus.algorithm.剑指offer;

public class 面试题18_JZ56删除链表中结点 {
	public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
		if (head == null || tobeDelete == null) return head;
		if (head == tobeDelete) return head.next;
		//不是尾节点
		if (tobeDelete.next != null) {
			tobeDelete.val = tobeDelete.next.val;
			tobeDelete.next = tobeDelete.next.next;
		} else {
			ListNode cur = head;
			while (cur.next != tobeDelete) cur = cur.next;
			cur.next = null;
		}
		return head;
	}
}
