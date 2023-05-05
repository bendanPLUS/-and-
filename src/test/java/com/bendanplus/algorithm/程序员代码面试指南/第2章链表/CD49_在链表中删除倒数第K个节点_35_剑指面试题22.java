package com.bendanplus.algorithm.程序员代码面试指南.第2章链表;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CD49_在链表中删除倒数第K个节点_35_剑指面试题22 {
	public static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static int nextInt() {
		try {
			st.nextToken();
			return (int) st.nval;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
		int n = nextInt();
		int k = nextInt();
//         Node head = build(n);
		Node head = new Node(0);
		Node lastNode = head;
		for (int i = 0; i < n; i++) {
			Node next = new Node(nextInt());
			lastNode.next = next;
			lastNode = next;
		}
		head = head.next;
		//Node newNode = removeNode(head, k);
		Node newNode = remove(head, n - k - 1);
		String res = NodeUtil.printList(newNode);
		System.out.println(res.trim());
	}


	public static Node removeNode(Node head, int lastKth) {
		if (head == null || lastKth < 1) return head;
		Node cur = head;
		while (cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if (lastKth == 0) head = head.next;
		if (lastKth < 0) {
			cur = head;
			while (++lastKth != 0) cur = cur.next;
			cur.next = cur.next.next;
		}
		return head;
	}

	public static Node remove(Node head, int k) {
		Node cur = head;
		while (cur != null) {
			if (k == 0) {
				cur.next = cur.next.next;
				break;
			}
			k--;
			cur = cur.next;
		}
		return head;
	}
}
