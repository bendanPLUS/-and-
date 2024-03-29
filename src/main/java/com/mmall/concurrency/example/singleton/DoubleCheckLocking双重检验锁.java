package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class DoubleCheckLocking双重检验锁 {

    // 私有构造函数
    private DoubleCheckLocking双重检验锁() {

    }

    //TODO new一个对象等三个步骤 可能会发生重排序
    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    // 单例对象
    private static DoubleCheckLocking双重检验锁 instance = null;

    // 静态的工厂方法
    public static DoubleCheckLocking双重检验锁 getInstance() {
        if (instance == null) { // 双重检测机制        // B
            synchronized (DoubleCheckLocking双重检验锁.class) { // 同步锁
                if (instance == null) {
                    instance = new DoubleCheckLocking双重检验锁(); // A - 3
                }
            }
        }
        return instance;
    }
}
