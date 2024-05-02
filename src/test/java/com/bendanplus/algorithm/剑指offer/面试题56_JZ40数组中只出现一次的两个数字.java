package com.bendanplus.algorithm.剑指offer;

import java.util.Arrays;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，找出这两个数。
 * 传送门:https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13
 * &tqId=11193&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 思路: 异或：0^0=0，1^0=1，0^1=1，1^1=0（同为0，异为1）
 * 两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有元素异或得到的结果为不存在重复的两个元素异或的结果。diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以将两个元素区分开来。
 * x&(-x)：保留二进制下最后出现的1的位置，其余位置置0（即一个数中最大的2的n次幂的因数    -x = x取反+1
 * 如何理解x&(-x)和x&(x-1)  https://www.cnblogs.com/yzxag/p/12668034.html
 */
public class 面试题56_JZ40数组中只出现一次的两个数字 {
    /**
     * TODO  距离说明 [1,4,1,6]    1^100^1^110 = 010 100=4 110=6 4和6在第二位上不同
     */
    public int[] FindNumsAppearOnce(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) return res;
        int diff = 0;
        for (int num : nums)
            diff ^= num; //相同为0 相异为0
        int k = 1;
        //从左向右 找到两个数不相同的第一位
        while ((k & diff) == 0) k <<= 1;
        for (int num : nums) {
            if ((num & k) == 0) {
                res[0] ^= num;
            } else res[1] ^= num;
        }
        Arrays.sort(res);
        return res;
    }


    public int[] findNumsAppearOnce(int[] nums) {
        if (nums == null || nums.length == 0) return new int[2];
        int[] res = new int[2];
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        //从左往右 找出第一位为1的 位置
        int k = 1;
        while ((k & diff) != 1) k >>= 1;
        for (int num : nums) {
            if ((k & num) == 0) res[0] ^= num;
            else res[1] ^= num;
        }
        return res;
    }
}
