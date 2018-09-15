package com.liam.demo.proxy;

import com.liam.demo.proxy.cglib.CglibLoggerInterceptor;
import com.liam.demo.proxy.jdk.JdkLoggerInterceptor;

import java.lang.reflect.Proxy;

public class ProxyTestBootStrap {

    public static void main(String args[]) {

        //jdk方式1
        //为目标对象创建动态代理，并获得目标对象的控制权
        EnglishGuy englishGuy = new EnglishGuy();
        JdkLoggerInterceptor jdkLoggerInterceptor = new JdkLoggerInterceptor(englishGuy);
        ClassLoader classLoader = englishGuy.getClass().getClassLoader();
        Person personProxy = (Person)Proxy.newProxyInstance(classLoader, englishGuy.getClass().getInterfaces(), jdkLoggerInterceptor);
        //目标对象的调用实际上通过动态代理调用，动态代理通过拦截器(InvocationHandler)实现调用
        personProxy.sayHello();
        System.out.println("--------");

        //2 jdk方式2，进一步封装
        Person personProxy1 = (Person)new JdkLoggerInterceptor().createProxy(englishGuy);
        personProxy1.sayHello();
        System.out.println("--------");

        //cglib方式
        EnglishGuy englishGuyProxy = (EnglishGuy) new CglibLoggerInterceptor().createProxy(englishGuy);
        englishGuyProxy.sayHello();

    }
}
