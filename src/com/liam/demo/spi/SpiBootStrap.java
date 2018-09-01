package com.liam.demo.spi;

import java.util.List;
import java.util.ServiceLoader;

public class SpiBootStrap {

    public static void main(String args[]) {
        ServiceLoader<SpiService> spiServiceList = ServiceLoader.load(SpiService.class);
        for (SpiService service : spiServiceList){
            service.print();
        }
    }
}
