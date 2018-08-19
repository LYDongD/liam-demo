package com.liam.demo.jmx.mbean;

public interface HelloMXBean {

    String getName();

    void setName(String name);

    void hello(String msg);

}
