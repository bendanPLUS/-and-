package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 把数字翻译成字符串
 * 0翻译成a 1翻译成b .... 25翻译成z 一个数可能多很多种翻译
 * 编程实现一个函数,用来计算一个数字有多少种不同的翻译方法
 */
/*dp[i]含义:字符以i开始到dp.length结束 的字符串 可以翻译成多少种结果 */
@Slf4j
public class 面试题46_JZ46_把数字翻译成字符串 {
    @Test
    public void test面试题46() {
        String str = "10324209123131292349280120313109812389212312313";
        final int translationCount = solve(str);
        log.info("translationCount is {}", translationCount);
        log.info("方法2的结果:  {}", getTranslationCount(str));
        log.info("方法3的结果:  {}", parseNumber(str));
        log.info("方法4的结果:  {}", parseNumberc(str));
    }

    public int parseNumberc(String nums) {
        if (nums == null || nums.length() == 0) return -1;
        // dp[i]的含义: 区间[i , nums.length()-1]上把数字翻译成字符串 出现多少种可能  dp[i]=dp[i-1]+g* dp[i-2]
        int[] dp = new int[nums.length() + 1];
        dp[dp.length - 1] = dp[dp.length - 2] = 1;
        int g;
        for (int i = nums.length() - 2; i >= 0; i--) {
            int num = Integer.parseInt(nums.charAt(i) + "" + nums.charAt(i + 1));
            g = (num >= 10 && num <= 25) ? 1 : 0;
            dp[i] = dp[i + 1] + g * dp[i + 2];
        }
        return dp[0];
    }

    /**
     * nums: 1 1 3 5
     * dp  : x x x 1 1 dp[dp.length - 1] = 1 dp[dp.length - 2] = 1
     */
    public int parseNumber(String nums) {
        if (nums == null || nums.length() == 0) return -1;
        if (nums.equals("0")) return 0;
        int[] dp = new int[nums.length() + 1]; //为了多设置一位 dp[dp.length - 1] 的值
        dp[dp.length - 1] = dp[dp.length - 2] = 1;
        int i = dp.length - 2;
        int g;
        while (i-- > 0) {
            int num = Integer.parseInt(nums.charAt(i) + "" + nums.charAt(i + 1));
            g = (num >= 10 && num <= 25) ? 1 : 0;
            dp[i] = dp[i + 1] + dp[i + 2] * g;
        }
        return dp[0];
    }


    public int solve(String nums) {
        if (nums == null || nums.length() == 0) return -1;
        if (nums.equals("0")) return 0;
        int[] dp = new int[nums.length() + 1];
        dp[dp.length - 1] = 1;
        dp[dp.length - 2] = 1;
        for (int i = nums.length() - 2; i >= 0; i--) {
            int num = Integer.parseInt(nums.charAt(i) + "" + nums.charAt(i + 1));
            int g = 0;
            if (num >= 10 && num <= 26) g = 1;
            dp[i] = dp[i + 1] + g * dp[i + 2];
        }
        return dp[0];
    }

    //动态规划
    public int getTranslationCount(String str) {
        if (StringUtils.isBlank(str)) return 0;
        //dp[i]含义:字符以i开始到dp.length结束 的字符串 可以翻译成多少种结果
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
