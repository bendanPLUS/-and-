package com.bendanplus.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
//https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-by-powcai-2/
@Slf4j
public class Solution_46全排列 {

    @Test
    public void test() {
        int[] muns = {1, 2, 3};
        List<List<Integer>> res = permute(muns);
        log.info("结果:{}", res);
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> isUsed = new HashSet<>();
        backTrack(nums, 0, length, isUsed, stack, res);
        return res;
    }

    private void backTrack(int[] nums, int depth, int length, Set<Integer> isUsed, Stack<Integer> stack, List<List<Integer>> res) {
        if (depth == length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (!isUsed.contains(i)) {
                isUsed.add(i);
                stack.push(nums[i]);
                backTrack(nums, depth + 1, length, isUsed, stack, res);
                isUsed.remove(i);
                stack.pop();
            }
        }
    }
}
