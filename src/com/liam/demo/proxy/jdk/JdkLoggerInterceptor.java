package com.liam.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * The {@code LoggerInterceptor}
 *
 * 基于jdk实现的动态代理拦截器，可代理接口实现类
 * 拦截器具有被代理对象的控制权
 *
 * @author  liam
 * @version 1.0
 */
public class JdkLoggerInterceptor implements InvocationHandler {


    //被代理对象
    private Object target;

    public JdkLoggerInterceptor(Object target) {
        super();
        this.target = target;
    }

    public JdkLoggerInterceptor(){};


    //直接创建代理对象
    public Object createProxy(Object targetObject){

        this.target = targetObject;

        Class<?> targetClass = targetObject.getClass();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before saying hello");

        //需要传入被代理对象和参数
        method.invoke(target, args);

        System.out.println("after saying hello");
        return null;
    }
}
