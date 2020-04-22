package com.fengcase.part4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：快递实体类
 * @Author: frt
 * @Date: 2019/8/4 16:44
 */
public class A0049ExpressCond {
    public final static String CITY = "ShangHai";
    private int km;//快递运输里程
    private String site;//快递到达地点
    private Lock lock = new ReentrantLock();
    private Condition kmCond = lock.newCondition();
    private Condition siteCond = lock.newCondition();

    public A0049ExpressCond() {
    }

    public A0049ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }
    //变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
    public  void changeKm(){
        lock.lock();
        try {
            this.km = 101;
            kmCond.signalAll();
        } finally {
            lock.unlock();
        }
    }
    //变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理
    public  void changeSite(){
        lock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
            lock.unlock();
        }
    }

    public  void waitKm(){
        lock.lock();
        try {
            while (this.km<=100){
                try {
                    kmCond.await();
                    System.out.println("check km thread ["+Thread.currentThread().getId()+"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println(" the km is "+this.km+", I will change db.");
    }
    public  void waitSite(){
        lock.lock();
        try {
            while (CITY.equals(this.site)){
                try {
                    siteCond.await();
                    System.out.println("check site thread ["+Thread.currentThread().getId()+"] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println(" the site is "+this.site+", I will call user.");
    }
}
