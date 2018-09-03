package com.liam.demo.enumType.singleton;


/**
 * 通过枚举构造单例
 * 将单例对象作为枚举实例的共享对象，由于枚举实例本身是单例，所以共享对象也只会被初始化一次
 */
public enum ResourceHolder {

    INSTANCE;

    private Resource resource;

    //在访问枚举实例时该方法被调用：ResourceHolder.INSTANCE
    ResourceHolder() {
        resource = new Resource();
    }

    public Resource getInstance() {
        return resource;
    }

    public static void main(String args[]) throws Exception {

        Resource resource1 = ResourceHolder.INSTANCE.getInstance();
        Resource resource2 = ResourceHolder.INSTANCE.getInstance();
        System.out.println(resource1 == resource2);
    }
}
