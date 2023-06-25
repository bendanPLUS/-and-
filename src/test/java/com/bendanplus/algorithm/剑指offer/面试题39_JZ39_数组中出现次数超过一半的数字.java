package com.bendanplus.algorithm.剑指offer;

/**
 * 数组中出现次数超过一半的数字
 */
public class 面试题39_JZ39_数组中出现次数超过一半的数字 {


    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        int num = array[0], time = 1, count = 0;

        for (int i = 1; i < array.length; i++) {
            time = num == array[i] ? time + 1 : time - 1;//一换一原则
            if (time == 0) {//重置
                num = array[i];
                time = 1;
            }
        }
        //记录下num出现过几次 是否超过一半
        for (int i = 0; i < array.length; i++)
            if (array[i] == num) count++;
        return 2 * count <= array.length ? 0 : num;
    }

    public int MoreThanHalfNum_Solution_老版本(int[] array) {
        if (array == null || array.length == 0) return 0;
        int temp = array[0];
        int time = 1;
        int numbers = 0;
        for (int i = 1; i < array.length; i++) {
            if (time == 0) {
                temp = array[i];
                time = 1;
            } else if (temp == array[i]) time++;
            else time--;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == temp) numbers++;
        }
        if (2 * numbers <= array.length) return 0;

        return temp;
    }
}
