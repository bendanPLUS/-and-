package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class DoubleCheckLocking双重检验锁 {

    //无论什么模式 首先
    //1.无参数私有的构造函数
    private DoubleCheckLocking双重检验锁() {
    }

    //2.实例对象为私有静态变量
    private static  DoubleCheckLocking双重检验锁 instance;
    //TODO new一个对象等三个步骤 可能会发生重排序
    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存
    // JVM和cpu优化，发生了指令重排
    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    //3.静态方法-> 双重检验是否为null+加锁synchronized
    public static DoubleCheckLocking双重检验锁 getInstance() {
        if (instance == null) {// B线程判断instance!=null 但有可能因为重排序 初始化对象还未完成 -> 这时候就需要引进volatile来禁止2-3的重排序
            synchronized (DoubleCheckLocking双重检验锁.class) {
                if (instance == null) {
                    instance = new DoubleCheckLocking双重检验锁(); // A - 3
                }
            }
        }
        return instance;
    }
}
