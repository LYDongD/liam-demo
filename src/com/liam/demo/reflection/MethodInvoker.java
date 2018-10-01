package com.liam.demo.reflection;

import java.lang.reflect.Method;

/**
 * 观察反射方法调用的原理
 *
 * 1 委派机制
 * 2 本地方法调用
 * 3 字节码增强
 * 4 inflation切换
 */
public class MethodInvoker {


    /**
     * 通过反射调用该方法
     *
     * @param i 参数
     */
    public static void target(int i) throws Exception{
        //通过构造异常来追踪方法调用栈信息
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String args[]) throws Exception{

        Class<?> methodInvokerClass = Class.forName("com.liam.demo.reflection.MethodInvoker");
        //参数是可变参数，获取方法时本质是通过数组传递
        Method targetMethod = methodInvokerClass.getMethod("target", int.class);

        //反复调用，委派模式inflation切换：前15次采用本地实现，后5次采用动态实现
        //通过-Dsun.reflect.inflationThreshol=15设定， -Dsun.reflect.noInflation=true 关闭本地实现
        for (int i = 0; i < 20; i++){
            //只有静态方法才可以传null，否则应该传入具体事例
            targetMethod.invoke(null, 1);
        }

    }
}
