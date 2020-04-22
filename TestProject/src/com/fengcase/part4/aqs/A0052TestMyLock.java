package com.fengcase.part4.aqs;

import com.fengcase.tools.A0004SleepTool;
import com.fengcase2.javacase.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: frt
 * @Date: 2019/9/12 16:36
 */
public class A0052TestMyLock {
    public void test(){
//        final Lock lock = new ReentrantLock();
//        final Lock lock = new A0051SelefLock();
        final Lock lock = new A0056TrinityLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try {
                        A0004SleepTool.second(1);
                        System.out.println(Thread.currentThread().getName());
                        A0004SleepTool.second(1);
                    } finally {
                        lock.unlock();
                    }
                    A0004SleepTool.second(1);
                }
            }
        }
        //启动10个子线程
        for (int i=0;i<10;i++){
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        for (int i = 0;i<10;i++){
            A0004SleepTool.second(1);
            System.out.println();
        }
    }
    public static void main(String[] args){
        A0052TestMyLock testMyLock = new A0052TestMyLock();
        testMyLock.test();
    }
}
