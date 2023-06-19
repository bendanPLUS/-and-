package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class 面试题11旋转数组的最小数字 {

    @Test
    public void test面试题11() {
        int[] array = {1, 2, 2, 2, 2, 2};
        log.info("结果:[{}]", minNumberInRotateArray(array));
    }

    public int minNumberInRotateArray(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) return 0;
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int m = (l + h) / 2;
            if (nums[m] == nums[l] && nums[m] == nums[h]) return minNumber(nums, l, h);
            else if (nums[m] <= nums[h]) h = m;
            else l = m + 1;
        }
        return nums[h];
    }

    private int minNumber(int[] array, int l, int h) {
        for (int i = l; i < h; i++)
            if (array[i] > array[i + 1])
                return array[i + 1];
        return array[l];
    }

}
