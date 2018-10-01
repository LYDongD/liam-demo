package com.liam.demo.bit;

/**
 * 位运算
 */
public class BitCalculate {


    //相当于n * 0.75， 右移2相当于缩小4倍
    public static int calculateMapResizeThreadHold(int n) {
        return n - (n >>> 2);
    }

    //求模运算，其中n是2的幂次方
    public static int mod(int n, int hash){
        //n-1求低位掩码，通过掩码获取hash的低位
        return (n - 1) & hash;
    }


    public static void main(String args[]) {
        System.out.println(calculateMapResizeThreadHold(16));
        System.out.println(mod(16, 5));
    }

}
