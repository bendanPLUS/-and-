package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  最小的k个数
 * 输入n个整数,找出其实最小的k个数
 */
public class 面试题40 {

	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if (k > input.length || k == 0) {
			return arrayList;
		}
		//最大堆 可以放k个数
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		//先放k个数进去
		for (int i = 0; i < k; i++) {
			maxHeap.offer(input[i]);
		}
		for (int i = k; i < input.length; i++) {
			if (input[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(input[i]);
			}
		}
		for (Integer i : maxHeap) {
			arrayList.add(i);
		}
		return arrayList;
	}
}
