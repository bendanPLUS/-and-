package com.bendanplus.sourcecode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadLocalTest {

	public static class MyRunnable implements Runnable {

		private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

		private ThreadLocal<String> threadLocalString = new ThreadLocal<>();

		private static AtomicInteger atomicInteger = new AtomicInteger();
		@Override
		public void run() {

			for (int i = 0; i < 4; i++) {
				threadLocalString.set(atomicInteger.addAndGet(2) + "");
				System.out.println("thread name is : " + Thread.currentThread().getName() + ", value is ; "
						+ threadLocalString.get());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


			threadLocal.set((int) (Math.random() * 100D));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
			System.out.println("--" + threadLocal.get());
			final String s = threadLocal.toString();

			log.info("threadLocal = {}", threadLocal);
		}
	}

	public static void main(String[] args) {
		MyRunnable sharedRunnableInstance = new MyRunnable();
		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);
		Thread thread3 = new Thread(sharedRunnableInstance);
		Thread thread4 = new Thread(sharedRunnableInstance);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

}
