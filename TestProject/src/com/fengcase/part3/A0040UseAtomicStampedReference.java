package com.fengcase.part3;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类说明：演示带版本戳的原子操作类
 * @Author:
 * @Date: 2019frt/8/21 22:34
 */
public class A0040UseAtomicStampedReference {
    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("Mark",0);
    public static void main(String[] args) throws Exception{
        final int oldStamp = asr.getStamp();//初始的版本号
        final String oldReference = asr.getReference();
        System.out.println(oldReference +"======" + oldStamp);
        Thread rightStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                +"当前变量值："+oldReference+"当前版本戳："+oldStamp+"-"
                        +asr.compareAndSet(oldReference,oldReference+"Java",oldStamp,oldStamp+1));
            }
        });
        Thread errorStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String reference = asr.getReference();
               //  int oldStamp = asr.getStamp();//初始的版本号
                System.out.println(Thread.currentThread().getName()
                        +"当前变量值："+reference+"当前版本戳："+asr.getStamp()+"-"
                        +asr.compareAndSet(reference,reference+"C",oldStamp,oldStamp+1));
            }
        });
        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference()+" ====== "+asr.getStamp() );
    }
}