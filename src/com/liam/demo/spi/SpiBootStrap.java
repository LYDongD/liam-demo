package com.liam.demo.spi;

import java.util.List;
import java.util.ServiceLoader;
import java.util.TimeZone;

public class SpiBootStrap {

//    public static void main(String args[]) {
//        ServiceLoader<SpiService> spiServiceList = ServiceLoader.load(SpiService.class);
//        for (SpiService service : spiServiceList) {
//            service.print();
//        }
//    }

    public static void main(String args[]) {
        System.out.println(TimeZone.getDefault().getID());
        System.out.println(System.currentTimeMillis());
    }
}
