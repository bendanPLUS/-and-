package com.bendanplus.algorithm.剑指offer;

//输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
public class 面试题25_JZ25_合并两个排序的链表 {

	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		ListNode mergeHead = null;
		ListNode cur = null;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				if (mergeHead == null) mergeHead = cur = list1;
				else {
					cur.next = list1;
					cur = cur.next;
				}
				list1 = list1.next;
			} else {
				if (mergeHead == null) mergeHead = cur = list2;
				else {
					cur.next = list2;
					cur = cur.next;
				}
				list2 = list2.next;
			}
		}
		if (list1 != null) cur.next = list1;
		if (list2 != null) cur.next = list2;
		return mergeHead;
	}
}
