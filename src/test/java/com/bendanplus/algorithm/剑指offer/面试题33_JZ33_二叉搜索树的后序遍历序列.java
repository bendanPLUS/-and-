package com.bendanplus.algorithm.剑指offer;

//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
// 如果是则返回 true ,否则返回 false 。假设输入的数组的任意两个数字都互不相同。
public class 面试题33_JZ33_二叉搜索树的后序遍历序列 {

	public boolean VerifySequenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length == 0) return false;
		return VerifySequenceOfBST(sequence, 0, sequence.length - 1);
	}

	public boolean VerifySequenceOfBST(int[] sequence, int start, int end) {
		if (end - start <= 1) return true;
		int root = sequence[end];
		int index = start;
		while (index < end && sequence[index] <= root) index++;
		int leftEnd = index-1;
		int rightStart = index;
		while (index < end) {
			if (sequence[index] < root) return false;
			index++;
		}
		return VerifySequenceOfBST(sequence, start, leftEnd) && VerifySequenceOfBST(sequence, rightStart, end-1
		);
	}


	private boolean verify(int[] sequence, int first, int last) {
		if (last - first <= 1) return true;
		int rootVal = sequence[last];
		int cutIndex = first;
		while (cutIndex < last && sequence[cutIndex] <= rootVal) cutIndex++;
		for (int i = cutIndex; i < last; i++)
			if (sequence[i] < rootVal) return false;
		return verify(sequence, first, cutIndex - 1) && verify(sequence, cutIndex, last - 1);
	}
}
