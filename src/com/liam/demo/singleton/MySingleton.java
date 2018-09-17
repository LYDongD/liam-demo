package com.liam.demo.singleton;

/**
 * 使用内部类初始化单例，利用类只会初始化一次的机制保证单例的唯一性
 */
public class MySingleton {

    private MySingleton() {
    }

    private static class SingletonHolder {

        //类初始化时会初始化静态final成员变量,相当于初始化常量,常量一般大写
        static final MySingleton INSTANCE = new MySingleton();

        //测试类初始化
        static {
            System.out.println("SingletonHolder <cinit>");
        }
    }

    public static Object getSingletonn(boolean initArray) {
        //返回数组或单例
        if(initArray) return new SingletonHolder[2];
        return SingletonHolder.INSTANCE;
    }

    public static void main(String args[]) {
        MySingleton.getSingletonn(true);
        System.out.println("---------");
        MySingleton.getSingletonn(false);
    }

}
