package com.bendanplus.algorithm.剑指offer;

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
    public int[] FindNumsAppearOnce(int[] nums) {
        int[] res = new int[2];
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        diff &= -diff;//通过最右边第一个1的位置 来区分两个数
        for (int num : nums) {
            if ((num & diff) == 0)
                res[0] ^= num;
            else
                res[1] ^= num;
        }
        if (res[0] > res[1])
            swap(res);
        return res;
    }
    private void swap(int[] nums) {
        int t = nums[0];
        nums[0] = nums[1];
        nums[1] = t;
    }
}
