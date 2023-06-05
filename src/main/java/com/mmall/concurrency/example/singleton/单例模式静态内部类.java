package com.mmall.concurrency.example.singleton;

public class 单例模式静态内部类 {
    //1.私有的构造函数
    private 单例模式静态内部类() {
    }

    //2.定义一个静态内部类
    private static class 单例模式静态内部类Holder {
        private final static 单例模式静态内部类 单例模式静态内部类Instance = new 单例模式静态内部类();
    }

    //3.对外访问的方法
    public static 单例模式静态内部类 get单例模式静态内部类Instance() {
        return 单例模式静态内部类Holder.单例模式静态内部类Instance;
    }
}
