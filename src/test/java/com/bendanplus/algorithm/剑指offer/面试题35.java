package com.bendanplus.algorithm.剑指offer;

public class 面试题35 {
	/**
	 * 请实现函数RandomListNode Clone(RandomListNode pHead)复制一个复杂链表
	 * 复杂链表中,每个节点除了有一个next指针指向下一个节点外 还有一个random指针指向链表中任意一个节点
	 * 或者为null
	 */
	private RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		cloneNode(pHead);
		connnectNodes(pHead);
		return reconnentNodes(pHead);
	}

	private RandomListNode reconnentNodes(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		RandomListNode newHead = pHead.next;
		RandomListNode pointer = newHead;
		pHead.next = newHead.next;
		pHead = pHead.next;
		while (pHead != null) {
			pointer.next = pHead.next;
			pointer = pointer.next;
			pHead.next = pointer.next;
			pHead = pHead.next;
		}
		return newHead;
	}

	//连接 复制的节点 到 random指针指向节点
	private void connnectNodes(RandomListNode pHead) {
		while (pHead != null) {
			if (pHead.random != null) {
				pHead.next.random = pHead.random.next;
			}
			pHead = pHead.next.next;

		}

	}
	private void cloneNode(RandomListNode pHead) {
		while (pHead != null) {
			RandomListNode temp = new RandomListNode(pHead.label);
			temp.next = pHead.next;
			pHead.next = temp;
			//走到下一个几点
			pHead = temp.next;
		}
	}
}
