package com.fengcase.part4.aqs;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 类说明：三元共享同步工具类
 * @Author: frt
 * @Date: 2019/9/17 19:29
 */
public class A0056TrinityLock implements Lock {
    private final Sync sync = new Sync(3);
    private static final class Sync extends AbstractQueuedSynchronizer{
         Sync(int count) {
            if (count <= 0){
                throw new IllegalArgumentException("count must large then zero.");
            }
            setState(count);
        }
        public int tryAcquireShared(int reduceCount){
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }
        public boolean tryReleaseShared(int reduceCount){
            for(;;){
                int current = getState();
                int newCount = current + reduceCount;
                if (compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
        final ConditionObject newCondition(){
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
            sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) > 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
