package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 丑数
 * url:https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。
 * 例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。
 * 习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
 */
@Slf4j
public class 面试题49_JZ49_丑数 {

    @Test
    public void test() {
        int index = 77;
        final int i = GetUglyNumber_Solution(index);
        final boolean ugly = isUgly(i);
        log.info("第index是否为丑数:{}  第index丑数是:{}", ugly, i);
        log.info("第index丑数是:{}", uglyNumber(index));
        log.info("第x丑数是:{}", uglyNumberc(index));
    }

    public int uglyNumberc(int index) {
        if (index < 7) return index;
        int[] nums = new int[index];
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < index; i++) {
            int m2 = nums[i2] * 2;
            int m3 = nums[i3] * 3;
            int m5 = nums[i5] * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            nums[i] = min;
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (m5 == min) i5++;
        }
        return nums[index - 1];
    }

    public int GetUglyNumber_Solution(int index) {
        if (index <= 6) return index;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        while (arrayList.size() < index) {
            int m2 = arrayList.get(i2) * 2;
            int m3 = arrayList.get(i3) * 3;
            int m5 = arrayList.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            arrayList.add(min);
            if (m2 == min) i2++;
            if (m3 == min) i3++;
            if (m5 == min) i5++;
        }
        return arrayList.get(arrayList.size() - 1);
    }

    public int uglyNumber(int index) {
        if (index <= 6) return index;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0; //数组该位置下的数 乘以2or3or5
        int m2 = 0, m3 = 0, m5 = 0, min;
        for (int i = 1; i < index; i++) {
            m2 = 2 * list.get(i2);
            m3 = 3 * list.get(i3);
            m5 = 5 * list.get(i5);
            min = Math.min(m2, Math.min(m3, m5));
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (min == m5) i5++;
            list.add(min);
        }
        return list.get(list.size() - 1);
    }

    // 判断是否是丑数
    public boolean isUgly(int number) {
        while (number % 2 == 0) number = number / 2;
        while (number % 3 == 0) number = number / 3;
        while (number % 5 == 0) number = number / 5;
        return number == 1 ? true : false;

    }

}
