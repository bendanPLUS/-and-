package com.bendanplus.algorithm.剑指offer;

import org.junit.Test;

public class 面试题21_JZ21_调整数组顺序使奇数位于偶数前面 {

    private int[] reOrderArray(int[] array) {
        if (array == null || array.length == 0) return array;
        int a = 0, b = a + 1;
        while (a < array.length) {
            while ((array[a] & 1) == 1) a++;    // 奇数就++ 直到偶数位
            b = a + 1;
            while (b < array.length && (array[b] & 1) == 0) b++;//寻找奇数位
            //逐个按照顺序交换位置
            if (b < array.length) {
                int tmp = array[b];
                for (int i = b; i > a; i--)
                    array[i] = array[i - 1];
                array[a] = tmp;
            } else break;
        }
        return array;
    }

    //申请额外的空间
    public int[] reOrderArrayT (int[] array) {
        int res[] = new int[array.length];
        int idx = 0;
        for (int a : array)
            if (a % 2 == 1) res[idx++] = a;
        for (int a : array)
            if (a % 2 == 0) res[idx++] = a;
        return res;
    }

    @Test
    public void test面试题21() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        reOrderArray(array);
    }
}
