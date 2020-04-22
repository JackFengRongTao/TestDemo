package com.fengcase.part1;

/**
 * 类说明：主线程等待子线程执行完，再接着执行
 * @Author: frt
 * @Date: 2019/8/2 9:26
 */
public class A0006UseJoinMainThreadWait {
    //private String  result = "";
    public static void main(String[] args){
        Thread    childThread =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //this.result = "1000";
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread "+Thread.currentThread().getName()+" is done");
            }
        });
        childThread.start();
        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " +Thread.currentThread().getName()+" is done" );
        
    }
}
