package com.bendanplus.algorithm.剑指offer;

import java.util.Stack;

//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，
// 输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。
public class 面试题30_JZ30_包含min函数的栈 {


	private Stack<Integer> dataStack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(int node) {
		dataStack.push(node);
		minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
	}

	public void pop() {
		dataStack.pop();
		minStack.pop();
	}

	public int top() {
		return dataStack.peek();
	}

	public int min() {
		return minStack.peek();
	}
}
