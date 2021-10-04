package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

	// 私有构造函数
	private SingletonExample7() {

	}

	private static class SingletonExampleHolder {
		private static SingletonExample7 instance = new SingletonExample7();
	}

	//静态内部类画
	//首次调用getInstance1 才进行SingletonExampleHolder初始化
	public SingletonExample7 getInstance1() {
		return SingletonExampleHolder.instance;
	}


	//枚举
	public static SingletonExample7 getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private enum Singleton {
		INSTANCE;

		private SingletonExample7 singleton;

		// JVM保证这个方法绝对只调用一次
		Singleton() {
			singleton = new SingletonExample7();
		}

		public SingletonExample7 getInstance() {
			return singleton;
		}
	}
}
