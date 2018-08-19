package com.liam.demo.jmx.clinet;

import com.liam.demo.jmx.mbean.HelloMXBean;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ClientBoostrap {

    public static void main(String args[]) throws Exception{

        //创建jmx连接
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL,null);

        //获取mbean信息
        MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        //打印所有domains
        String[] domains = mBeanServerConnection.getDomains();
        for (String domain : domains){
            System.out.println("domain: " + domain);
        }
        System.out.println("MBean count = " + mBeanServerConnection.getMBeanCount());

        //动态调用方法,代理模式
        ObjectName oname = new ObjectName("jmxBean:name=com.liam.demo.mbean.impl.standardhello");
        HelloMXBean proxy = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, oname, HelloMXBean.class,false);
        proxy.hello("world");


    }
}
