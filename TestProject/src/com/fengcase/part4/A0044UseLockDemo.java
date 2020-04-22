package com.fengcase.part4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：使用Lock的范式
 * @Author: frt
 * @Date: 2019/9/7 12:51
 */
public class A0044UseLockDemo {
    private Lock lock = new ReentrantLock();//可重入锁
    private int count;

    public synchronized void incremnet2() {//重入锁的情况
        count++;
        incremnet2();//重复调用自己(递归)
    }
    public synchronized void test() {//重入锁的情况
        incremnet2();
    }
    //Lock的调用范式
    public void increment(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }
}
