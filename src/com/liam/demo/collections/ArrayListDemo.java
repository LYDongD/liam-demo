package com.liam.demo.collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {


    public static void main(String args[]) {
        List<Integer> testList = new ArrayList<>();
        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        System.out.println(testList.get(1));

        testList.remove(1);

        System.out.println(testList.get(1));
    }
}
