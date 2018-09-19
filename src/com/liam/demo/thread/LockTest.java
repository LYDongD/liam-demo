package com.liam.demo.thread;

import static java.lang.Thread.sleep;

public class LockTest {

    private volatile int i = 0;

    private synchronized void write() {
        i++;
    }

    private int read() {
        return i;
    }


    public static void main(String args[]) throws Exception{

        LockTest lockTest = new LockTest();
        new Thread((lockTest::write)).start();
        System.out.println(lockTest.read());
        sleep(5000);
    }

}

