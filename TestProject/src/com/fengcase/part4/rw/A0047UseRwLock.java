package com.fengcase.part4.rw;

import com.fengcase.tools.A0004SleepTool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类说明：用内置锁实现商品服务接口
 * @Author: frt
 * @Date: 2019/9/7 13:23
 */
public class A0047UseRwLock implements  A0046GoodsService {

    private A0045GoodsInfo goodsInfo;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock getLock = readWriteLock.readLock();//读锁
    private final Lock writeLock = readWriteLock.writeLock();//写锁

    public A0047UseRwLock(A0045GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

        @Override
        public  A0045GoodsInfo getNum() {
            getLock.lock();
            try {
                A0004SleepTool.ms(5);
                return this.goodsInfo;
            } finally {
                getLock.unlock();
            }
        }

        @Override
        public  void setNum(int number) {
            writeLock.lock();
            try {
                A0004SleepTool.ms(5);
                goodsInfo.changeNumber(number);
            } finally {
                writeLock.unlock();
            }
        }

}
