package com.fengcase.part1.safeend;

/**
 * 类说明：如何安全中断线程
 * @Author: frt
 * @Date: 2019/8/3 13:23
 */
public class A0012EndThread {
    private static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(true){
                System.out.println(threadName+" is run!");
            }
            //System.out.println( threadName+" interrupt flag is " +Thread.currentThread().interrupted());
        }
    }
    public static void main(String[] args) throws  InterruptedException{
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }

}
