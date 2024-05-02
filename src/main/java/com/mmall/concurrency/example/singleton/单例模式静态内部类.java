package com.mmall.concurrency.example.singleton;

public class 单例模式静态内部类 {
    //1.一个私有无参数的构造函数
    private 单例模式静态内部类() {
    }

    //2.静态内部类获取实例参数 -> 第一次调用静态方法触发类的初始化->类的初始化所有线程都需要获取初始化锁,再访问该方法
    private static class 单例模式静态内部类Holder {
        private static final 单例模式静态内部类 instance = new 单例模式静态内部类();
    }

    //3.对外访问的方法 第一次调用触发初始化
    public static 单例模式静态内部类 get单例模式静态内部类Instance() {
        return 单例模式静态内部类Holder.instance;
    }
}
