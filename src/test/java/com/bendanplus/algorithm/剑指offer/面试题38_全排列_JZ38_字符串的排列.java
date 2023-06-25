package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 字符串的排列
 * 输入一个字符串,打印出该字符串中字符的所有排列.
 * 例如: abc 则打印出abc所排列出的所有字符串 abc acb bac bca cab cba
 */
@Slf4j
public class 面试题38_全排列_JZ38_字符串的排列 {

    @Test
    public void test面试题38() {
        final ArrayList<String> res = Permutation("abc");
        log.info("结果:[{}]", res);
    }

    private ArrayList<String> res = new ArrayList<>();

    public ArrayList<String> permute(String str) {
        //判空
        if (str == null || str.length() == 0) return null;
        //变成char数组
        char[] chars = str.toCharArray();
        //排序  重要
        Arrays.sort(chars);
        //位置元素使用情况
        boolean[] isUsed = new boolean[chars.length];
        StringBuffer s = new StringBuffer();
        backtracking(chars, isUsed, s);
        return res;
    }

    //此方法像是选元素 选一个元素出来 在0位置 再选第二个元素出来再1位置 .... 再选第i个元素出来在i位置 ....以此类推
    public void backtracking(char[] chars, boolean[] isUsed, StringBuffer s) {
        if (chars.length == s.length()) {
            res.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            //判断该位置元素是否已经被使用过
            if (isUsed[i]) continue;
            //去重
            //判断是否会出现重复 !isUsed[i - 1]没有被使用 下一层元素就可以使用isUsed[i - 1]位置的元素 就会出现重复
            if (i != 0 && chars[i - 1] == chars[i] && !isUsed[i - 1]) continue;
            s.append(chars[i]);
            //标记该位置元素已经被使用
            isUsed[i] = true;
            //循环下一层
            backtracking(chars, isUsed, s);
            //排列完成 删除该位置的标记
            isUsed[i] = false;
            s.deleteCharAt(s.length() - 1);
        }
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> array = new ArrayList();
        if (StringUtils.isBlank(str))
            return array;
        char[] chars = str.toCharArray();
        Permutation001(chars, array, 0);
        Collections.sort(array);
        return array;
    }

    //交换位置 自己和自己交换 再和自己后面一位的元素交换 以此类推
    private void Permutation001(char[] chars, ArrayList<String> array, int begin) {
        if (begin == chars.length - 1) {
            String str = String.valueOf(chars);
            if (!array.contains(str)) array.add(str);
        } else {
            for (int i = begin; i < chars.length; i++) {
                swap(chars, begin, i);
                Permutation001(chars, array, begin + 1);
                swap(chars, begin, i);
            }
        }
    }

    private void swap(char[] chars, int i, int j) {
        if (chars == null || chars.length == 0) return;
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
