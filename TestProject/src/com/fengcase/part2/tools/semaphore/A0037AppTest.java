package com.fengcase.part2.tools.semaphore;

import com.fengcase.tools.A0004SleepTool;

import java.sql.Connection;
import java.util.Random;

/**
 * 类说明：测试数据库连接池
 * @Author: frt
 * @Date: 2019/8/21 20:50
 */
public class A0037AppTest {
    private static A0036DBPoolSemaphore dbpool = new A0036DBPoolSemaphore();
    private static class BusiThread extends Thread{
        @Override
        public void run() {
            try {
                    Random r  = new Random();//让每个线程持有连接的时间不一样
                    long start = System.currentTimeMillis();
                    Connection connect = dbpool.takeConnection();
                    System.out.println("Thread_" + Thread.currentThread().getId()+
                            "_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
                    A0004SleepTool.ms(100+r.nextInt(100));//模拟业务操作，线程持有连接查询数据
                    System.out.println(" 查询数据完成，归还连接！ ");
                    dbpool.returnConnnect(connect);
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        for (int i = 0;i < 50;i++){
            Thread thread = new BusiThread();
            thread.start();
        }
    }
}
