package com.liam.demo.jmx.agent;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MBeanRegistry {


    public static MBeanServer registMBean(String className) throws Exception{

        MBeanServer mserver = ManagementFactory.getPlatformMBeanServer();

        ObjectName oname = new ObjectName("jmxBean:name=" + className.toLowerCase());

        //反射的方式注册mbean
        mserver.registerMBean(Class.forName(className).newInstance(), oname);

        return mserver;

    }



}
