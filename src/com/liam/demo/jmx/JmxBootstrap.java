package com.liam.demo.jmx;

import com.liam.demo.jmx.agent.MBeanRegistry;
import com.liam.demo.jmx.mbean.impl.StandardHello;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.rmi.registry.LocateRegistry;

public class JmxBootstrap {

    public static void main(String args[]) throws Exception{

        MBeanServer mBeanServer = MBeanRegistry.registMBean(StandardHello.class.getName());

        int[] array = new int[]{0,1};


        //本地进程连接：确保应用不退出，用于测试jconsole访问jmx(前提是应用启动时就开启并监听了jmx端口)
//        Thread.sleep(60 * 60 * 1000);

        //rmi方式进行远程连接：创建rmi服务
        //协议 rmi://<host>:<port>/<service>
        try {
            LocateRegistry.createRegistry(9999);
            JMXServiceURL url = new JMXServiceURL
                    ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
            jcs.start();
        }catch (Exception e){
            e.printStackTrace();
        }


        //tomcat的许多组件，例如StandardService是MBean，因此可以通过jmx接口动态管理，访问jmx可以使用rmi通信方式

    }


}
