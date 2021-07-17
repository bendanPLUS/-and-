package com.bendanplus.algorithm.剑指offer;

/**
 * 52. 两个链表的第一个公共结点
 * url:https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class 面试题52_JZ36两个链表的第一个公共结点 {
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return null;
		}
		ListNode l1 = pHead1;
		ListNode l2 = pHead2;
		while (l1 != l2) {
			l1 = l1 == null ? pHead2 : l1.next;
			l2 = l2 == null ? pHead1 : l2.next;
		}
		return l1;
	}
}
