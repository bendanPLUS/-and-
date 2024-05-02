package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Stringbuffer代表一个字符序列可变的字符串,当一个Stringbuffer被创建以后,通过Stringbuffer提供的
 * append() reverse() setCharAt() setLength()方法改变这个字符串序列
 * Stringbuffer是线程安全所有方法都是synchronized修饰的
 */
@Slf4j
public class 面试题05替换空格 {


    @Test
    public void test面试题03() {
        int[] array = {2, 1, 3, 2};

        log.info("结果:[{}]", replaceSpacex("hello world"));
    }


    public String replaceSpacex(String s) {
        // write code here
        if (s == null || s.length() == 0) return "";
        StringBuffer str = new StringBuffer(s);
        int oldLength = str.length();
        for (int i = 0; i < oldLength; i++)
            if (str.charAt(i) == ' ') str.append("  ");

        int newLength = str.length();
        int l = oldLength - 1;
        int h = newLength - 1;
        while (l >= 0 && l < h) {
            if (str.charAt(l) == ' ') {
                str.setCharAt(h--, '0');
                str.setCharAt(h--, '2');
                str.setCharAt(h--, '%');
            } else str.setCharAt(h--, str.charAt(l));
            l--;
        }
        return str.toString();
    }

    public String replaceSpace1(StringBuffer str) {
        if (str == null || str.length() == 0) return str.toString();
        int oldTail = str.length() - 1;
        for (int i = 0; i <= oldTail; i++)
            if (str.charAt(i) == ' ') str.append("  ");

        int newTail = str.length() - 1;
        //从后向前遍历
        while (oldTail >= 0 && oldTail != newTail) {
            char c = str.charAt(oldTail--);
            if (c == ' ') {
                str.setCharAt(newTail--, '0');
                str.setCharAt(newTail--, '2');
                str.setCharAt(newTail--, '%');
            } else str.setCharAt(newTail--, c);
        }
        //这是是出给C++人做的[奸笑] java用repaceAll
        return str.toString().replaceAll(" ", "02%");
    }


    public String replaceSpace(String s) {
        // write code here
        if (s == null || s.length() == 0) return s;
        StringBuffer str = new StringBuffer(s);
        int l = str.length() - 1;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == ' ') str.append("  ");

        int h = str.length() - 1;
        while (l >= 0 && l != h) {
            if (str.charAt(l) == ' ') {
                str.setCharAt(h--, '0');
                str.setCharAt(h--, '2');
                str.setCharAt(h--, '%');
            } else {
                str.setCharAt(h--, str.charAt(l));
            }
            l--;
        }
        return str.toString();
    }
}