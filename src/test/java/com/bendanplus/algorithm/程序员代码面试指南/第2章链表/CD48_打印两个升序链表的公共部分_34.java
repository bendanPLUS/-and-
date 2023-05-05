package com.bendanplus.algorithm.程序员代码面试指南.第2章链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD48_打印两个升序链表的公共部分_34 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		String[] numbers1 = bufferedReader.readLine().split(" ");
		int m = Integer.parseInt(bufferedReader.readLine());
		String[] numbers2 = bufferedReader.readLine().split(" ");
		Node head1 = NodeUtil.listGenerator(n, numbers1);
		Node head2 = NodeUtil.listGenerator(m, numbers2);
		printCommonPart(head1, head2);
	}

	public static void printCommonPart(Node head1, Node head2) {
		if (head1 == null || head2 == null) return;

		while (head1 != null && head2 != null) {
			if (head1.value > head2.value) head2 = head2.next;
			else if (head1.value < head2.value) head1 = head1.next;
			else {
				System.out.print(head1.value + " ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}
}
