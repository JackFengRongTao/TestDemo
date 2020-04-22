package com.fengcase.part1.safeend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: frt
 * @Date: 2019/8/3 14:18
 */
public class A0014HasInterruptedException {
    private static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    private static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(!isInterrupted()){
                try {
                    System.out.println(" UseThread:"+formater.format(new Date()) );
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " catch interrupt flag is "+isInterrupted() +" at "+(formater.format(new Date())));
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName);
            }
            System.out.println(threadName + " interrupt flag is "+isInterrupted());
        }
    }
    public static void main(String[] args) throws InterruptedException{
        Thread endThread = new UseThread("HasInterruptEx");
        endThread.start();
        System.out.println("Main:"+formater.format(new Date()));
        Thread.sleep(800);
        System.out.println(" Main begin interrupt thread:"+formater.format(new Date()));
        endThread.interrupt();
    }

}
