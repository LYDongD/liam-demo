package com.liam.demo.jmx.mbean.impl;


import com.liam.demo.jmx.mbean.HelloMXBean;

public class StandardHello implements HelloMXBean {


    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello(String msg) {

        System.out.println("hello " + msg);

    }
}
