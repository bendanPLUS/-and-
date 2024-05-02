package com.bendanplus.algorithm.剑指offer;

public class 面试题23_JZ55链表中环的入口 {
    /**
     * 讲的非常好都能听懂 https://blog.nowcoder.net/n/0abeae05e9884baf8655b3f6625b6d4d
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        ListNode slow = pHead, fast = pHead;
        //快慢指针 一次走一步 一次走两步
        //do-while语句 先执行，后判断
        //1.找到相遇的节点
        do {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        //2.找到环的入口
        fast = pHead;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
