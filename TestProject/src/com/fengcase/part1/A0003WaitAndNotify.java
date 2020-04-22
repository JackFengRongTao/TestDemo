package com.fengcase.part1;

import java.util.concurrent.FutureTask;

/**
 * @Author: frt
 * @Date: 2019/8/1 17:11
 */
public class A0003WaitAndNotify  {
    /**
     *实现Runnable接口
     */
    private static class UseRun implements Runnable{

        @Override
        public void run() {
            Long i = 0L;
            while(i>100){
                i++;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am implements Runnable." );
            //this.notifyAll();
        }

    }

    public static void main(String[] args) throws Exception{
        A0003WaitAndNotify.UseRun useRun = new A0003WaitAndNotify.UseRun();
        Thread t1 = new Thread(useRun);
        t1.start();


        System.out.println("I am  main");
    }
}
