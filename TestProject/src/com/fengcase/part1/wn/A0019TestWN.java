package com.fengcase.part1.wn;

/**
 * 类说明：测试wait/notify/notifyAll
 * @Author: frt
 * @Date: 2019/8/4 17:18
 */
public class A0019TestWN {
    private static A0018Express express = new A0018Express(0,A0018Express.CITY);
    //检查里程数变化的线程，不满足条件，线程一直等待
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }
    //检查地点变化的线程，不满足条件，线程一直等待
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }
    public static void main(String[] args) throws InterruptedException{//???这样设计是不是不太合理：因为所有被通知的线程都会对数据库进行操作，实际上只需要一个线程就行了。
        for (int i = 0;i<3;i++){//起三个线程，查看地点有无变化
            new CheckSite().start();
        }
        for (int i = 0;i<3;i++){//起三个线程，里程数的变化
            new CheckKm().start();
        }
        Thread.sleep(1000);
        express.changeKm();
    }

}
