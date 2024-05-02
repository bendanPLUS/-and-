package com.bendanplus.algorithm.剑指offer;

public class 面试题35_JZ35_复杂链表的复制 {
    /**
     * 请实现函数RandomListNode Clone(RandomListNode pHead)复制一个复杂链表
     * 复杂链表中,每个节点除了有一个next指针指向下一个节点外 还有一个random指针指向链表中任意一个节点
     * 或者为null
     */

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return pHead;
        // 1.插入新节点
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        // 2.建立 random 链接
        cur = pHead;
        while (cur != null) {
            if (cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3.拆分
        cur = pHead;
        RandomListNode pCloneHead = cur.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }

    private RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        cloneNode(pHead);
        connnectNodes(pHead);
        return reconnentNodes(pHead);
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

    private void connnectNodes(RandomListNode pHead) {
        while (pHead != null) {
            if (pHead.random != null) {
                pHead.next.random = pHead.random.next;
            }
            pHead = pHead.next.next;
        }
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
}
