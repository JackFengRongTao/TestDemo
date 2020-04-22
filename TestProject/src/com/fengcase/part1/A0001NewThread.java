package com.fengcase.part1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 类说明：如何新建线程
 * @Author: frt
 * @Date: 2019/7/31 21:18
 */
public class A0001NewThread {
    /**
     *实现Runnable接口
     */
    private static class UseRun implements Runnable{
        @Override
        public void run() {
            System.out.println("I am implements Runnable." );
        }
    }

    /**
     * 实现Callable接口，并带有返回值
     */
    private static  class UseCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("I am implements Callable. ");
            return "CallResult";
        }
    }

    public static void main(String[] args) throws Exception{
        UseRun  useRun = new UseRun();
        Thread t1 = new Thread(useRun);
        t1.start();
        //-----------------
        UseCall useCall = new UseCall();
        FutureTask<String> futureTask = new FutureTask<String>(useCall) ;
        new Thread(futureTask).start();
        System.out.println( futureTask.get());
    }

}
