package com.bendanplus.algorithm.剑指offer;

public class 面试题24_JZ24_反转链表 {

    public ListNode ReverseList(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        ListNode reverseNode = head;
        while (cur != null) {
            if ((next = cur.next) != null) reverseNode = next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return reverseNode;
    }
}
