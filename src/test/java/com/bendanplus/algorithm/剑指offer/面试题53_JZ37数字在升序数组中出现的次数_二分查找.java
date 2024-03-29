package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 53. 统计一个数字在排序数组中出现的次数
 * 二分查找
 */
@Slf4j
public class 面试题53_JZ37数字在升序数组中出现的次数_二分查找 {
    @Test
    public void test() {
        int[] nums = {1, 1, 3, 3, 3, 3};
        final int i = GetNumberOfK(nums, 1);
        log.info("[结果={}]", i);
    }

    public int GetNumberOfK(int[] nums, int K) {
        if (nums == null || nums.length == 0)
            return 0;
        final int firstK = getFirstNum(nums, K);
        final int firstKAdd1 = getFirstNum(nums, K + 1);
        return (firstK == nums.length || nums[firstK] != K) ? 0 : firstKAdd1 - firstK;
    }


    /**
     * binarySearch
     * 利用二分查找 找到排序数组中 最左变的第一个K值 如果没有 找到大于K且于K值最接近的值
     */
    private int getFirstNum(int[] nums, int K) {//binarySearch
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] >= K)
                end = mid;
            else
                start = mid + 1;
        }
        //start==end
        return start;
    }

    private int binarySearch(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= k)
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }

}
