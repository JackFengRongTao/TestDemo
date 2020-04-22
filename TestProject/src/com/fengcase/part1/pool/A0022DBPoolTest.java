package com.fengcase.part1.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @Author: frt
 * @Date: 2019/8/4 20:23
 */
public class A0022DBPoolTest {
    static A0021DBPool pool = new A0021DBPool(10);
    static CountDownLatch end;//控制器：控制main线程将会等待所有Worker结束才能继续执行
    static class Worker implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count>0){
                try {
                    Connection connection = pool.fetchConn(1000);
                    if (connection!= null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+"等待超时！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
    public static void main(String[] args) throws Exception{
        //线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//每个线程的操作次数
        AtomicInteger got = new AtomicInteger();//计数器：统计可以拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();//计数器：统计没有拿到连接的线程
        for (int i=0;i<threadCount;i++){
            Thread thread = new Thread(new Worker(count,got,notGot),"worker_"+i);
            thread.start();
        }
        end.await();//main线程在此处等待
        System.out.println("总共尝试了："+(threadCount * count));
        System.out.println("拿到连接的次数："+got);
        System.out.println("没能拿到连接的次数："+notGot);
    }
}
