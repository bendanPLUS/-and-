package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不含重复字符的子字符串
 * 测试地址:
 * https://www.nowcoder.com/practice/fafbd351003a499f9a80c3f641295f2a?tpId=101&tqId=33199&tPage=2&rp=2&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 */
@Slf4j
public class 面试题48_JZ48_最长不含重复字符的子字符串 {
    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            sb.append(in.nextInt());
//        }
        String s = new String("sdajaldjladjdfsjladjladjl");
        log.info("结果等于 {}", lengthOfLongestSubstring1(s));
        log.info("结果等于 {}", longestSubstring(s));
        log.info("结果等于 {}", longestSubstringWithoutDuo(s));
        log.info("结果等于 {}", lengthOfLongestSubstring(s));

    }


    public static int longestSubstring(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] dp = new int[s.length()]; //dp[i]代表的含义是以i为结尾的 最长子串的长度
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            int len = dp[i - 1];
            int l = i - len;
            int h = i - 1;
            for (; h >= l; h--)
                if (s.charAt(i) == s.charAt(h)) break;
            dp[i] = i - h;
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }


    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) return -1;
        //以i为结尾的字符串 最长不包含重复字符 长度
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int maxDp = 1;
        for (int i = 1; i < s.length(); i++) {
            int j;
            for (j = i - 1; j > i - dp[i - 1] - 1; j--) {
                if (s.charAt(i) == s.charAt(j)) break;
            }
            dp[i] = i - j;
            maxDp = maxDp > dp[i] ? maxDp : dp[i];
        }
        return maxDp;
    }

    private static int longestSubstringWithoutDuo(String str) {
        if (str == null || str.length() == 0) return 0;
        //dp[i] 代表以i结尾 从0到i 最长不含重复字符的子字符串 的长度
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int maxDp = 1;
        for (int dpIndex = 1; dpIndex < str.length(); dpIndex++) {
            int i = dpIndex - 1;
            //dpIndex - dp[dpIndex - 1] 代表  以dpIndex-1结尾最长不含重复字符子串 子串的起始位置 从dpIndex-1结束位置 从后往前遍历 --> 直到起始为止dpIndex - dp[dpIndex - 1]
            for (; i >= dpIndex - dp[dpIndex - 1]; i--)
                //如果相等说明 到达该点就是dp[i]的长度
                if (str.charAt(dpIndex) == str.charAt(i)) break;

            //两种情况:1.没有与str.charAt(dpIndex)相同的字符 2.有与str.charAt(dpIndex)相同的字符
            //从dpIndex开始往前遍历最长不含重复字符的子字符串 的长度
            dp[dpIndex] = dpIndex - i;
            maxDp = dp[dpIndex] > maxDp ? dp[dpIndex] : maxDp;
        }
        return maxDp;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return -1;
        //以i结尾 从0到i 不含重复字符的子字符串最大长度=dp[i]
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int maxDp = 1;
        for (int i = 1; i < s.length(); i++) {
            int len = dp[i - 1];
            int left = i - len;
            int right = i - 1;
            int j = right;
            for (; j >= left; j--)//从右往左遍历 找到第一个与位置i相同的字符 如果没有就要最大长度
                if (s.charAt(j) == s.charAt(i)) break;
            dp[i] = i - j;
            maxDp = maxDp > dp[i] ? maxDp : dp[i];
        }
        return maxDp;
    }
}
