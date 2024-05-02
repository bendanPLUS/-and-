package com.bendanplus.algorithm.剑指offer;

public class 面试题22_JZ22_链表中倒数最后k个结点 {
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if (pHead == null || k == 0) return null;
        ListNode l = pHead, h = pHead;
        for (int i = 0; i < k - 1; i++) {
            if (h.next == null) return null;
            h = h.next;
        }
        while ( h.next != null) {
            l = l.next;
            h = h.next;
        }
        return l;
    }
}
