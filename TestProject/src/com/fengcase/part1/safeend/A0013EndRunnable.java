package com.fengcase.part1.safeend;

/**
 * 类说明：中断Runnable类型的线程
 * @Author: frt
 * @Date: 2019/8/3 14:02
 */
public class A0013EndRunnable {
    private static class UseRunnable implements Runnable{

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();
            while(Thread.currentThread().isInterrupted()) {
                System.out.println(threadName+" is run!");
            }
            System.out.println(threadName+" interrput flag is "
                    +Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
