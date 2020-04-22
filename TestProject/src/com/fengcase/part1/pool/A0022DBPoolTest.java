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
    static CountDownLatch end;//������������main�߳̽���ȴ�����Worker�������ܼ���ִ��
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
                        System.out.println(Thread.currentThread().getName()+"�ȴ���ʱ��");
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
        //�߳�����
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//ÿ���̵߳Ĳ�������
        AtomicInteger got = new AtomicInteger();//��������ͳ�ƿ����õ����ӵ��߳�
        AtomicInteger notGot = new AtomicInteger();//��������ͳ��û���õ����ӵ��߳�
        for (int i=0;i<threadCount;i++){
            Thread thread = new Thread(new Worker(count,got,notGot),"worker_"+i);
            thread.start();
        }
        end.await();//main�߳��ڴ˴��ȴ�
        System.out.println("�ܹ������ˣ�"+(threadCount * count));
        System.out.println("�õ����ӵĴ�����"+got);
        System.out.println("û���õ����ӵĴ�����"+notGot);
    }
}
