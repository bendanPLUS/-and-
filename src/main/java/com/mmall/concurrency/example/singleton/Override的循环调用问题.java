package com.mmall.concurrency.example.singleton;

public class Override的循环调用问题 {
	public void doSomething() {
		System.out.println(" 父类的doSomething()方法");
				this.doSomething();
	}

	public static void main(String[] args) {
		Override的循环调用问题 fater = new Override的循环调用问题Son();
		fater.doSomething();
	}
}

class Override的循环调用问题Son extends Override的循环调用问题 {
	@Override
	public void doSomething() {
		System.out.println(" 子类的doSomething()方法");
		super.doSomething();
	}
}
