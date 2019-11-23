package com.bendanplus.algorithm.常见的手撕代码题目;

public class 归并排序 {

	public void mergeSort(int[] array) {
		if (array == null || array.length == 0)
			return;
		int len = array.length;
		int[] copy = new int[len];
		for (int i = 0; i < len; i++)
			copy[i] = array[i];
		sort(array, 0, len - 1, copy);

	}

	private void sort(int[] array, int left, int right, int[] copy) {
		if (left < right) {
			int mid = (left + right) >> 1;
			//把mid左边数组的排好序
			sort(array, left, mid, copy);
			//把mid右边数组的排好序
			sort(array, mid + 1, right, copy);
			//现在左边右边数组都已经排好序 把整个数组排好序
			merge(array, left, mid, right, copy);
		}
	}

	private void merge(int[] array, int left, int mid, int right, int[] copy) {
		int i = left;
		int j = mid + 1;
		int k = left;
		while (i <= mid && j <= right) {
			if (array[i] < array[j])
				copy[k++] = array[i++];
			else
				copy[k++] = array[j++];
		}
		while (i <= mid)
			copy[k++] = array[i++];
		while (j <= right)
			copy[k++] = array[j++];
		for (i = left; i <= right; i++)
			array[i] = copy[i];
	}
}
