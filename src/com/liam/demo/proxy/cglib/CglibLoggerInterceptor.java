package com.liam.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibLoggerInterceptor implements MethodInterceptor{

    private Object target;


    public Object createProxy(Object targetObject){
        this.target = targetObject;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before saying hello");

        //使用methodProxy调用
        Object o1 = methodProxy.invoke(target, objects);

        System.out.println("after saying hello");

        return o1;
    }
}
