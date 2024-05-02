package com.bendanplus.algorithm.剑指offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 数组中的逆序对
 * url:https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数
 */
//就是让你写一个归并排序

//TODO 结果不对有问题 需要看一下 递归排序没问题
@Slf4j
public class 面试题51_JZ35数组中的逆序对_归并排序 {

    @Test
    public void test() {
        int[] array1 = {1, 1, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 9, 1, 1, 1, 1, 1, 1, 200, 300, 8, 6, 7, 14, 55, 66, 77, 2000, 200001, 100000, 0, 7, 0};
        int[] array2 = {1, 1, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 9, 1, 1, 1, 1, 1, 1, 200, 300, 8, 6, 7, 14, 55, 66, 77, 2000, 200001, 100000, 0, 7, 0};
        final int j = inversePairs(array2);
        log.info("归并排序后的数组array{}", array2);
        log.info("结果等于{}", j);
        final int i = InversePairs(array1);
        log.info("归并排序后的数组array{}", array1);
        log.info("结果等于{}", i);

    }

    //就是归并排序
    private int[] copy;
    private int[] copy2;
    private long count = 0;
    private long count1 = 0;

    //先归并 再排序
    public int inversePairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        copy2 = new int[array.length];
        for (int i = 0; i < array.length; i++)
            copy2[i] = array[i];

        先归并数组(array, 0, array.length - 1, copy2);
        //在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
        //输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
        //即输出P%1000000007
        return (int) count1 % 1000000007;


    }

    private void 先归并数组(int[] array, int left, int right, int[] copy) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        先归并数组(array, left, mid, copy);
        先归并数组(array, mid + 1, right, copy);
        再排序数组(array, left, mid, right, copy);
    }

    private void 再排序数组(int[] array, int left, int mid, int right, int[] copy) {
        int i = left, j = mid + 1, k = left;//i 左边数组起始位置 j右边数组其实位置 复制数组起始位置
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) copy[k++] = array[i++]; //两个数相比 那个数更小就放在前边
            else {
                copy[k++] = array[j++];//出现了前面的数大于后面的数 说明是逆序
                //这个地方可能会出现重复计算
                this.count1 += mid - i + 1; //逆序对个数 左边的数全部大于后边的数array[j]
            }
        }
        while (i <= mid) copy[k++] = array[i++];
        while (j <= right) copy[k++] = array[j++];
        for (int l = left; l <= right; l++)
            array[l] = copy[l];
    }

    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        copy = new int[array.length];
        for (int i = 0; i < array.length; i++)
            copy[i] = array[i];

        mergeSort(array, 0, array.length - 1, copy);
        //在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
        //输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
        //即输出P%1000000007
        return (int) count % 1000000007;
    }

    private void mergeSort(int[] array, int left, int right, int[] copy) {
        if (left < right) {
            int mid = (left + right) >> 1;
            //递归思想
            //把mid左边数组的排好序
            mergeSort(array, left, mid, copy);
            //把mid右边数组的排好序
            mergeSort(array, mid + 1, right, copy);
            //排序
            //现在左边右边数组都已经排好序 把整个数组排好序
            sort(array, left, mid, right, copy);
        }
    }

    private void sort(int[] array, int left, int mid, int right, int[] copy) {
        int i = left, j = mid + 1, k = left;//i 左边数组起始位置 j右边数组其实位置 复制数组起始位置
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) copy[k++] = array[i++]; //两个数相比 那个数更小就放在前边
            else {
                copy[k++] = array[j++];//出现了前面的数大于后面的数 说明是逆序
                //这个地方可能会出现重复计算
                this.count += mid - i + 1; //逆序对个数 左边的数全部大于后边的数array[j]
            }
        }
        while (i <= mid) copy[k++] = array[i++];
        while (j <= right) copy[k++] = array[j++];
        for (int l = left; l <= right; l++)
            array[l] = copy[l];
    }

    private void mergeSort1(int[] array, int left, int right, int[] copy) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        //递归思想
        //把mid左边数组的排好序
        mergeSort(array, left, mid, copy);
        //把mid右边数组的排好序
        mergeSort(array, mid + 1, right, copy);
        //排序
        //现在左边右边数组都已经排好序 把整个数组排好序
        sort1(array, left, mid, right, copy);
    }

    private void sort1(int[] array, int left, int mid, int right, int[] copy) {
        int i = left, j = mid + 1, k = left;//i 左边数组起始位置 j右边数组其实位置 复制数组起始位置
        while (i <= mid || j <= right) {
            if (i > mid) copy[k++] = array[i++];
            else if (j > right) copy[k++] = array[j++];
            else if (array[i] <= array[j]) copy[k++] = array[i++]; //两个数相比 那个数更小就放在前边
            else {
                copy[k++] = array[j++];//出现了前面的数大于后面的数 说明是逆序
                //这个地方可能会出现重复计算
                this.count += mid - i + 1; //逆序对个数 左边的数全部大于后边的数array[j]
            }
        }
        for (int l = left; l <= right; l++)
            array[l] = copy[l];
    }


}
