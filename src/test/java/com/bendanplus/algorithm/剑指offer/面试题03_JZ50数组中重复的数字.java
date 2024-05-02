package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking&from=cyc_github
@Slf4j
public class 面试题03_JZ50数组中重复的数字 {

    @Test
    public void test面试题03() throws IOException {
        //读一整行字符串 变成StringBuilder
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder(br.readLine()); //转化成StringBuilder
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // String str = in.readLine();


        int[] array = {2, 1, 3, 2};
        x("123456789", 3);
        log.info("结果:[{}]", duplicate(array));
    }


    public void x(String s, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(s.charAt(i));
        }
        list.add(Integer.valueOf(sb.toString()));
        for (int i = k; i < s.length(); i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            list.add(Integer.valueOf(sb.toString()));
        }
    }

    public int duplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) return -1;
        for (int i = 0; i < numbers.length; i++) {
            //所有数都在 0～n-1范围内
            //判断一下所有数是否小于长度
            if (numbers[i] < 0 || numbers[i] > numbers.length - 1) return -1;
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) return numbers[i];
                else Swap.swap(numbers, i, numbers[i]);
            }
        }
        return -1;
    }


    //把找到的第一个重复的数字 存放到duplication数组中 duplication[0] = nums[i];

    /**
     * 思路 循环 判断nuns[i]!=i 就把nuns[i]的值放到i处
     * 遍历到某个位置时重复的数字肯定会发生nums[i] != i 并且 nums[i]==nums[nums[i]]
     * 不能申请额外的内存空间
     */
    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || nums.length == 0) return false;
        //判断一下所有数是否小于长度
        for (int i = 0; i < nums.length; i++) {
            //所有数都在 0～n-1范围内
            if (nums[i] < 0 || nums[i] > length - 1) return false;
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    //把找到的重复的数字存到duplication[0]里
                    duplication[0] = nums[i];
                    return true;
                } else Swap.swap(nums, i, nums[i]);
            }
        }
        return false;
    }


}
