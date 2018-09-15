package com.liam.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * The {@code CglibLoggerInterceptor}
 * 基于类的动态代理，目标对象无需实现接口
 * 拦截器具有被代理对象的控制权
 *
 * @author  liam
 * @version 1.0
 */
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
