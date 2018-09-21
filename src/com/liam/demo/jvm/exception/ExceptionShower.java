package com.liam.demo.jvm.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看jvm的异常处理机制
 * 1 异常处理表：exception table
 * 2 目标跳转
 */
public class ExceptionShower {

    private int tryBlock;

    private int catchBlock;

    private int finallyBlock;

    private int methodExit;


    public void showException(){
        try {
            tryBlock = 0;
        }catch (Exception e){
            catchBlock = 1;
        }finally {
            finallyBlock = 2;
        }

        methodExit = 3;
    }


    public void catchMultipleException(){
        try {
            List<Integer> testList = new ArrayList<>();
            System.out.println(testList.get(0));
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            e.printStackTrace();
        }
    }

}
