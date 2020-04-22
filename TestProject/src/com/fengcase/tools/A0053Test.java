package com.fengcase.tools;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: frt
 * @Date: 2019/9/15 15:49
 */
public class A0053Test {
    public static void main(String[] args){
        //LockSupport   ;
//        AbstractQueuedSynchronizer;
//        FutureTask
//        ReentrantLock
//        ConcurrentHashMap
//        Modifier
//        HashMap
         int num = 2;
         num  <<= 1;
        System.out.println(num);
        System.out.println("num2 = ");
    }
}
