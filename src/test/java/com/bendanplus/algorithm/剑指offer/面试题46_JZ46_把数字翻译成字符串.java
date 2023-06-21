package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 把数字翻译成字符串
 * 0翻译成a 1翻译成b .... 25翻译成z 一个数可能多很多种翻译
 * 编程实现一个函数,用来计算一个数字有多少种不同的翻译方法
 */
@Slf4j
public class 面试题46_JZ46_把数字翻译成字符串 {
    @Test
    public void test面试题46() {
        String str = "12258";
        final int translationCount = getTranslationCount(str);
        log.info("translationCount is {}", translationCount);
    }

    //动态规划
    public int getTranslationCount(String str) {
        if (StringUtils.isBlank(str)) return 0;
        int[] dp = new int[str.length() + 1];
        dp[str.length()] = 1;
        dp[str.length() - 1] = 1;
        int g = 1;
        //从后向前
        for (int i = str.length() - 2; i >= 0; i--) {
            int num = Integer.parseInt(str.charAt(i) + "" + str.charAt(i + 1));
            if (num <= 25 && num >= 10) g = 1;
            else g = 0;

            dp[i] = dp[i + 1] + (g * dp[i + 2]);
        }
        return dp[0];
    }
}
