package com.fengcase.part3;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 类说明：演示原子性
 * @Author: frt
 * @Date: 2019/8/21 21:34
 */
public class A0037AtomicArray {
    static int[] value = new int[]{1,2,3};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    public static void main(String[] args){
        System.out.println(" = " +"你好");
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
