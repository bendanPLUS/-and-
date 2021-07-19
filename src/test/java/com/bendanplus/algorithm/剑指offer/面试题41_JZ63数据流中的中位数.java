package com.bendanplus.algorithm.剑指offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 * 如果从数据流中读取奇数个数值 那么中位数就是中间那个数
 * 数据流读出偶数个数 那么是中间的那两个数的平均数
 */
public class 面试题41_JZ63数据流中的中位数 {
	private static int count = 0;
	//最小堆
	private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();


	public static void main(String[] args) {
		int[] nums = new int[]{5, 2, 3, 4, 1, 6, 7, 0, 8};
		for (int i = 0; i < nums.length; i++) {
			Insert(nums[i]);
			final Double aDouble = GetMedian();
			System.out.println("aDouble==="+aDouble);
		}
	}

//	private PriorityQueue<Integer> maxHeap =
//			new PriorityQueue<>(15, new Comparator<Integer>() {
//				@Override
//				public int compare(Integer o1, Integer o2) {
//					return o2.compareTo(o1);
//				}
//			});

	//最大堆
	private static PriorityQueue<Integer> maxHeap =new PriorityQueue<>(15,(o1,o2) -> o2.compareTo(o1));


	public static void Insert(Integer num) {
		//偶数 放最大堆
		if ((count & 1) == 0) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		} else {
			//奇数 放最大堆
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
		count++;
	}

	public static Double GetMedian() {
		//偶数 取平均数
		if ((count & 1) == 0) {
			double res = (double) (maxHeap.peek() + minHeap.peek());
			return res / 2.0;
		} else {
			return new Double(minHeap.peek());
		}
	}
}
