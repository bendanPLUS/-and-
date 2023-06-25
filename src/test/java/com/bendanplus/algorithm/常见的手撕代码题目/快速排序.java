package com.bendanplus.algorithm.常见的手撕代码题目;


public class 快速排序 {
    public void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0 || low >= high) return;
        int i = low, j = high, temp = array[low];
        //选最左边的数作为基准 所以要右边先开始走
        while (i != j) {
            //从右向左 找到比基准小的数 结束循环
            while (i < j && temp <= array[j]) j--;
            //从左往右 找到比基准大的数 结束循环
            while (i < j && temp > array[i]) i++;
            if (i < j) swap(array, i, j);
        }
        //将基准归位  左边的数<基准<右边的数
        swap(array, low, i);
        quickSort(array, low, i - 1);
        quickSort(array, i + 1, high);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
