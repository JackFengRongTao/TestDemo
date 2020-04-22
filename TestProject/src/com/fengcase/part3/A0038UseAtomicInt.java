package com.fengcase.part3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类说明：
 * @Author: frt
 * @Date: 2019/8/21 21:42
 */
public class A0038UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);
    public static void increment(){//递增操作
        Integer val = ai.get();
        for (;;){
            boolean bl = ai.compareAndSet(val,val+1);
            if (bl == true){
               break;
            }
        }
    }

    public static void main(String[] args){
        System.out.println(ai.getAndIncrement());//10-->11
        System.out.println(ai.incrementAndGet());//11-->12-->out
        System.out.println(ai.get());
        increment();
        System.out.println(ai.get());
    }

}
