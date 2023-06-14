package com.bendanplus.algorithm.剑指offer;

public class 面试题23_JZ22_链表中倒数最后k个结点 {
    public ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here
        if (pHead == null || k == 0) return null;
        ListNode p2 = pHead, p1 = pHead;

        for (int i = 0; i < k; i++) {
            if (p2.next != null) p2 = p2.next;
            else return null;
        }
        p2 = p2.next;
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
