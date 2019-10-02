package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
	/**
	 * AtomicIntegerFieldUpdater
	 * 核心思想: 更新某一个类里的volatile修饰的字段的值
	 * 例如: 更新AtomicExample5.class里的count字段的值 --> updater.compareAndSet(example5, 100, 120)
	 */
	private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
			AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

	//count 必须是volatile类型 不然会会报错 java.lang.IllegalArgumentException: Must be volatile type
	@Getter
	public volatile int count = 100;

	public static void main(String[] args) {

		AtomicExample5 example5 = new AtomicExample5();

		if (updater.compareAndSet(example5, 100, 120)) {
			log.info("update success 1, {}", example5.getCount());
		}

		if (updater.compareAndSet(example5, 100, 120)) {
			log.info("update success 2, {}", example5.getCount());
		} else {
			log.info("update failed, {}", example5.getCount());
		}
	}
}
