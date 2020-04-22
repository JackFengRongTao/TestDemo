package com.fengcase.part1;

/**
 * @Author: frt
 * @Date: 2019/8/3 12:49
 */
public class A0011SleepLock {
    private Object lock = new Object();
    private class ThreadSleep extends Thread{
        @Override
        public void run() {
            String threadName = currentThread().getName();
            System.out.println(threadName + " will take the lock ");
            try {
                synchronized (lock){
                    System.out.println( threadName + " taking the lock ");
                    System.out.println("Finish the work:"+threadName);
                }
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class ThreadNotSleep extends Thread{
        @Override
        public void run() {
            String threadName = currentThread().getName();
            System.out.println(threadName + " will take the lock time="+System.currentTimeMillis());
                synchronized (lock){
                    System.out.println( threadName + " taking the lock time="+System.currentTimeMillis());
                    System.out.println("Finish the work:"+threadName);
                }
        }
    }
    public static void main(String[] args){
        A0011SleepLock sleepTest = new A0011SleepLock();
        Thread t1 = sleepTest.new ThreadSleep();
        t1.setName("ThreadSleep");
        Thread t2 = sleepTest.new ThreadNotSleep();
        t2.setName("ThreadNotSleep");
        t1.start();
        try {
            Thread.sleep(3000);
            System.out.println("Main slept！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
