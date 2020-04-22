package com.fengcase.part2.tools;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 类说明：CyclicBarrier的使用
 * @Author: frt
 * @Date: 2019/8/20 20:42
 */
public class A0033UseCyclicBarrier {
    private static CyclicBarrier barrier = new CyclicBarrier(5,new CollectionThread());
    private static ConcurrentHashMap<String,Long> resultMap
            =  new ConcurrentHashMap<>();
    //负责屏障开放以后的工作
    private static class CollectionThread implements Runnable{
        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String,Long> workResult:resultMap.entrySet()){
                result.append("["+workResult.getValue()+"]");
            }
            System.out.println(" the result = "+result);
            System.out.println(" do other business...... " );
        }
    }
    private static class SubThread implements Runnable{
        @Override
        public void run() {
            long id = Thread.currentThread().getId();//线程本身处理结果
            resultMap.put(Thread.currentThread().getId()+"",id);
            Random r = new Random();//随机决定工作线程是否睡眠
            try {
                if (r.nextBoolean()){
                    Thread.sleep(2000+id);
                    System.out.println("Thread_"+id+"...do something" );
                }
                System.out.println("id"+"...is await" );
                barrier.await();
                Thread.sleep(1000+id);
                System.out.println(" Thread_"+id+"... do its business " );
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        for (int i = 0;i<=4;i++){
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }
}
