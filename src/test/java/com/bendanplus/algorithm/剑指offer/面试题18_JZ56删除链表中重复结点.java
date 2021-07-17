package com.bendanplus.algorithm.剑指offer;

public class 面试题18_JZ56删除链表中重复结点 {
	//需要用递归的思想
	public ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null || pHead.next == null)
			return pHead;
		if (pHead.val != pHead.next.val) {
			pHead.next = deleteDuplication(pHead.next);
			return pHead;
		} else {
			ListNode node = pHead.next;
			while (node != null && pHead.val == node.val)
				node = node.next;
			return deleteDuplication(node);
		}
	}
}
