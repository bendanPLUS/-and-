package com.bendanplus.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  最小的k个数  给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
 * 输入n个整数,找出其实最小的k个数
 */
public class 面试题40_JZ40_最小的K个数 {


	public ArrayList<Integer> 新_GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if (k > input.length || k == 0) return arrayList;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		for (int i = 0; i < input.length; i++) {
			maxHeap.add(input[i]);
			if (maxHeap.size() > k) maxHeap.poll();
		}
		for (Integer num : maxHeap)
			arrayList.add(num);
		return arrayList;
	}

	public ArrayList<Integer> 老_GetLeastNumbers_Solution(int[] input, int k) {
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
