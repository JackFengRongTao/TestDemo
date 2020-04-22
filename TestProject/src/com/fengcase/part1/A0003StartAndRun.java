package com.fengcase.part1;

import com.fengcase.tools.A0004SleepTool;

/**
 *类说明：rstart和un方法的区别
 * @Author: frt
 * @Date: 2019/8/1 21:15
 */
public class A0003StartAndRun {
    private   static  class ThreadRun extends Thread {
        @Override
        public void run() {
            int i = 100;
            while(i > 0){
                A0004SleepTool.ms(100);
                System.out.println("I am "+Thread.currentThread().getName()+" and now the i = "+i--);
            }
        }
    }
    private   static  class User{
        public void us() {
            System.out.println("I am "+Thread.currentThread().getName());
        }
    }
    public static void main(String[] args){
        ThreadRun tr = new ThreadRun();
        tr.setName("beCalledThread");
        //tr.start();
        tr.run();
    }
}
