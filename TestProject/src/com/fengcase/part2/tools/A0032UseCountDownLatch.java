package com.fengcase.part2.tools;

import com.fengcase.tools.A0004SleepTool;

import java.util.concurrent.CountDownLatch;

/**
 * 类说明：演示CountDownLatch，有5个初始化的线程，6个扣除点
 * @Author: frt
 * @Date: 2019/8/13 20:50
 */
public class A0032UseCountDownLatch {
    static CountDownLatch latch = new CountDownLatch(6);
    private static class InitThread implements Runnable{
        @Override
        public void run() {
            System.out.println(" Thread_"+Thread.currentThread().getId()
            +" ready init work......");
            latch.countDown();//初始化线程完成工作了，countdown方法扣减一次；
            for (int i = 0;i < 2;i++){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" Thread_ "+Thread.currentThread().getId()
                +"......continue do its work");
            }
        }
    }
    //业务线程
    private static class BusiThread implements Runnable{
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0;i < 3;i++){
                System.out.println(" BusiThread_"+Thread.currentThread().getId()
                +" do business----");
            }
        }
    }
    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                A0004SleepTool.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 1st......");
                latch.countDown();//每完成一步初始化工作，扣减一次
                System.out.println(" begin step 2nd...... " );
                A0004SleepTool.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 2nd......");
                latch.countDown();//每完成一步初始化工作，扣减一次
            }
        }).start();
        new Thread(new BusiThread()).start();
        for (int i = 0;i <= 3;i++){
            Thread  thread = new Thread(new InitThread());
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" Main do its work...... " );
    }
}
