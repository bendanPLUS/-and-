package com.bendanplus.algorithm.剑指offer;

//https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking&from=cyc_github
public class 面试题03_JZ50数组中重复的数字 {

	//把找到的第一个重复的数字 存放到duplication数组中 duplication[0] = nums[i];
	/**
	 * 思路 循环 判断nuns[i]!=i 就把nuns[i]的值放到i处
	 * 遍历到某个位置时重复的数字肯定会发生nums[i] != i 并且 nums[i]==nums[nums[i]]
	 * 不能申请额外的内存空间
	 */
	public boolean duplicate(int[] nums, int length, int[] duplication) {
		if (nums == null || nums.length == 0)
			return false;
		//判断一下所有数是否小于长度
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0 || nums[i] > length - 1)
				return false;
			while (nums[i] != i) {
				if (nums[i] == nums[nums[i]]) {
					//把找到的重复的数字存到duplication[0]里
					duplication[0] = nums[i];
					return true;
				} else
					Swap.swap(nums, i, nums[i]);
			}
		}
		return false;
	}
}
