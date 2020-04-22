package com.fengcase.part1;

/**
 * 类说明：演示下join方法的使用
 * @Author: frt
 * @Date: 2019/8/1 22:02
 */
public class A0005UseJoin {
    private static class JumpQueue implements Runnable{
        Thread thread ;//用来插队的线程
        public JumpQueue(Thread thread) {
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                int i = 0;
                while(i<100){
                    i++;
                    Thread.sleep(30);
                    System.out.println("I am "+Thread.currentThread().getName());
                }
                System.out.println(thread.getName()+" will be join before "+Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" terminted.");
        }
    }
    public static void main(String[] args){
        Thread pevious = Thread.currentThread() ;//现在是主线程
        for (int i = 0;i < 1;i++){
            Thread thread = new Thread(new JumpQueue(pevious),String.valueOf(i));
            System.out.println(pevious.getName()+" jump a queue the thread:"+thread.getName());
            thread.start();
            pevious = thread;
        }
        System.out.println("i am main ."  );
    }
}
