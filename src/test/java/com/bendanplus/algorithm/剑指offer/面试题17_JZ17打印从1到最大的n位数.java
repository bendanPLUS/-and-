package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class 面试题17_JZ17打印从1到最大的n位数 {

    @Test
    public void test面试题17() {
        //log.info("结果:[{}]", printNumbers(3));
        Print1ToMaxOfNDigits_3(3);
    }

    public int[] printNumbers(int n) {
        int len = (int) Math.pow(10, n); //10的n次幂
        int index = 1;
        int[] res = new int[len - 1];
        for (int i = 0; i < res.length; i++)
            res[i] = index++;
        return res;
    }


    public static void Print1ToMaxOfNDigits_3(int n) {
        if (n < 0) return;
        //构造一个不带字符，但具有指定初始容量的字符串缓冲区。
        StringBuffer s = new StringBuffer(n);
        for (int i = 0; i < n; ++i)
            s.append('0');
        Print1ToMaxOfNDigits_3_Recursely(s, n, 0);
    }

    public static void Print1ToMaxOfNDigits_3_Recursely(StringBuffer s, int n, int index) {
        if (index == s.length()) {
            PrintNumber(s);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            s.setCharAt(index, (char) (i + '0'));
            Print1ToMaxOfNDigits_3_Recursely(s, n, index + 1);
        }
    }

    public static void PrintNumber(StringBuffer s) {
        boolean isBeginning0 = true;
        for (int i = 0; i < s.length(); i++) {
            if (isBeginning0 && s.charAt(i) != '0') //保证不打印001前面的00只打印出来 1；  保证打印出来的字母第一个不是0;
                isBeginning0 = false;
            if (!isBeginning0)
                System.out.print(s.charAt(i));
        }
        System.out.println();
    }

}
