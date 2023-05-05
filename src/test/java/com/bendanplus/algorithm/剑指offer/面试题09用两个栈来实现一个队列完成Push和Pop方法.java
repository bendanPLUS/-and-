package com.bendanplus.algorithm.剑指offer;

import java.util.Stack;

public class 面试题09用两个栈来实现一个队列完成Push和Pop方法 {
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	public void push(Integer num) {
		stack1.push(num);
	}

	public Integer pop() {
		while (stack2.isEmpty()) while (!stack1.isEmpty()) stack2.push(stack1.pop());
		return stack2.pop();
	}
}