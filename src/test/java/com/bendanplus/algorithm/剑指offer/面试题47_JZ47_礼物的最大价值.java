package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Scanner;

/**
 * 礼物的最大价值
 * m*n的棋盘的每一格都放一个礼物 你可以从棋盘的左上角开始拿礼物,
 * 并每次向左或者向下移动一格,直到到达棋盘的右下角
 */
@Slf4j
public class 面试题47_JZ47_礼物的最大价值 {


    //dp[i][j]含义:从(0,0)到(i,j)结尾 礼物的最大价值
    //dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+gift[i][j];
    //dp[i][j] 只与dp[i-1][j] dp[i][j-1]有关系 可以压缩空间 dp[i-1]存放dp[i][j-1] dp[i]存放dp[i-1][j]


    @Test
    public void test面试题47() {
        int[][] grid = new int[][]{new int[]{1, 2, 5}, new int[]{3, 2, 1}};


        int i = maxValue5(grid);
        log.info("translationCount is {}", 2);
    }


    public int maxValue5(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++)
            dp[i] = grid[0][i] + dp[i - 1];

        for (int i = 1; i < grid.length; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < grid[0].length; j++)
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i][j];
        }
        return dp[dp.length - 1];
    }

    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        //以(i,j)结尾从(0,0)到(i,j)礼物的最大值
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        //初始化 边缘位置的值
        //第0列所有的值
        for (int i = 1; i < grid.length; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        //第0行所有的值
        for (int j = 1; j < grid[0].length; j++)
            dp[0][j] = dp[0][j - 1] + grid[0][j];

        //从上到下从到右
        for (int i = 1; i < grid.length; i++)
            for (int j = 1; j < grid[0].length; j++)
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public int maxValue2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        //初始化 第0行所有的值
        for (int j = 1; j < grid[0].length; j++)
            dp[j] = dp[j - 1] + grid[0][j];// 0行j列
        //从上到下 从左到右
        for (int i = 1; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0];// i行0列
            for (int j = 1; j < grid[0].length; j++)
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i][j];
        }
        return dp[dp.length - 1];
    }

    public int maxValue3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid[0].length; //总共n列
        int[] dp = new int[n];
        for (int[] grids : grid) {//一行一行元素拿出来
            dp[0] += grids[0];
            for (int j = 1; j < n; j++)//j代表列
                dp[j] = Math.max(dp[j], dp[j - 1]) + grids[j];
        }
        return dp[n - 1];
    }
}
