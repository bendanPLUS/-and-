package com.bendanplus.algorithm.程序员代码面试指南.第2章链表;

public class NodeUtil {
	public static Node listGenerator(int length, String[] numbers) {
		Node head = new Node(Integer.parseInt(numbers[0]));
		Node cur = head;
		for (int i = 1; i < length; i++) {
			cur.next = new Node(Integer.parseInt(numbers[i]));
			cur = cur.next;
		}
		cur.next = null;
		return head;
	}


	public static String printList(Node head) {
		if (head == null) return null;
		StringBuilder res = new StringBuilder();
		while (head != null) {
			res.append(head.value).append(" ");
			head = head.next;
		}
		return res.toString();
	}
}
