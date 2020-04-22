package com.fengcase.part5;

/**
 * 类说明：演示位运算
 * @Author: frt
 * @Date: 2019/9/17 21:35
 */
public class A0057IntToBinary {
    public static void main(String[] args){
        int data = 4;
        System.out.println("the data is " +Integer.toBinaryString(data));
        //位于 & (1&1=1、1&0=0、0&0=0)
        System.out.println("the 4 is " +Integer.toBinaryString(4));
        System.out.println("the 6 is " +Integer.toBinaryString(6));
        System.out.println("the 4&6 is " +Integer.toBinaryString(4&6));
        //位或 | (1|1=1、1|0=1、0|0=0)
        System.out.println("the 4 is " +Integer.toBinaryString(4));
        System.out.println("the 6 is " +Integer.toBinaryString(6));
        System.out.println("the 4|6 is " +Integer.toBinaryString(4|6));
        //位非~ (~1=0、~0=1)
        System.out.println("the 4 is " +Integer.toBinaryString(4));
        System.out.println("the ~4 is " +Integer.toBinaryString(~4));
        //位异或^ (1^1=0、1^0=1、0^0=0)
        System.out.println("the 4 is " +Integer.toBinaryString(4));
        System.out.println("the 6 is " +Integer.toBinaryString(6));
        System.out.println("the 4^6 is " +Integer.toBinaryString(4^6));
        //<<有符号左移>>有符号的右移>> >>>无符号右移
        //取模的操作a%(2^n)等价于 a&(2^n-1)
        System.out.println("the 345 % 16 is "+(345%16)+" or "+(345&(16-1)));

        System.out.println("the 15 is " +Integer.toBinaryString(15));
    }
}
