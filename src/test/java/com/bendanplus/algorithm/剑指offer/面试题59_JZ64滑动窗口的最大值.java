package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * <p>
 * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
 * 传送门:https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
@Slf4j
public class 面试题59_JZ64滑动窗口的最大值 {

    @Test
    public void test() {
        int[] nums = {2, 3, 4, 2, 6, 2, 5, 1};
        ArrayList<Integer> res = maxInWindows(nums, 3);
        log.info("[结果={}]", res);
    }

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (num == null || num.length == 0 || size <= 0 || num.length < size) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        //大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(15, (o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++)
            queue.add(num[i]);
        //滑动窗口开始滑动 初始化已经完成 开滑
        res.add(queue.peek());
        for (int i = size; i < num.length; i++) {
            queue.remove(num[i - size]);
            queue.add(num[i]);
            res.add(queue.peek());
        }
        return res;
    }


    public ArrayList<Integer> maxInWindows1(int[] num, int size) {
        if (num == null || num.length == 0 || size <= 0 || num.length < size) return new ArrayList<>();
        //大顶堆 最大的数在最上面
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(15, (o1, o2) -> o2 - o1);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            maxHeap.add(num[i]);
        res.add(maxHeap.peek());
        for (int i = size; i < num.length; i++) {
            maxHeap.remove(num[i - size]);
            maxHeap.add(num[i]);
            res.add(maxHeap.peek());
        }
        return res;
    }
}
