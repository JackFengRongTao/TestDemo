package com.fengcase.part1.vola;

/**
 * 类提示：演示violate无法提供操作的原子性
 * @Author: frt
 * @Date: 2019/8/3 17:08
 */
public class A0017VolatileUnsafe {
    private static class VolatileVar implements Runnable{
        private volatile int a = 0;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            a = a++;
            System.out.println(threadName +" :========= "+a );
            a = a+1;
            System.out.println(threadName+" :========="+a );
        }
    }
    public static void main(String[] args){
        VolatileVar v = new VolatileVar();
        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
