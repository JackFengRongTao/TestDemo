package com.fengcase.part4.condition;

/**
 * 类说明：测试condition
 * @Author: frt
 * @Date: 2019/8/4 17:18
 */
public class A0050TestCond {
    private static A0049ExpressCond express = new A0049ExpressCond(0, A0049ExpressCond.CITY);
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
    public static void main(String[] args) throws InterruptedException{
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
