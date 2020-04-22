package com.fengcase.part1;

/**
 * 类说明：守护线程的使用和守护线程中的finally语句块
 * @Author: frt
 * @Date: 2019/8/3 12:18
 */
public class A0010DaemonThread {
    private static class UseThread extends Thread{
        @Override
        public void run() {
            try {
                while(!isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+" I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName()+" interrupt flag is "+isInterrupted());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("....finally");
            }
        }
    }
    public static void main(String[] args) throws Exception{
        UseThread ut = new UseThread();
        ut.setDaemon(true);
        ut.start();
        Thread.sleep(5);
        ut.interrupt();
    }

}
