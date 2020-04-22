package com.fengcase.part1.syn;

import com.fengcase.tools.A0004SleepTool;

/**
 * 类说明：演示对象锁和类锁
 * @Author: frt
 * @Date: 2019/8/3 15:10
 */
public class A0015SynClzAndInst {
    //使用类锁的线程
    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println(" TestClass is Running ");
            synClass();
        }
    }
    //使用对象锁的线程
    private static class InstanceSyn implements Runnable{
        private A0015SynClzAndInst a0015SynClzAndInst;

        public InstanceSyn(A0015SynClzAndInst a0015SynClzAndInst) {
            this.a0015SynClzAndInst = a0015SynClzAndInst;
        }
        @Override
        public void run() {
            System.out.println("TestInstance is running..."+a0015SynClzAndInst);
            a0015SynClzAndInst.instance();
        }
    }
    //使用对象锁的线程
    private static class Instance2Syn implements Runnable{
        private A0015SynClzAndInst a0015SynClzAndInst;

        public Instance2Syn(A0015SynClzAndInst a0015SynClzAndInst) {
            this.a0015SynClzAndInst = a0015SynClzAndInst;
        }
        @Override
        public void run() {
            System.out.println("TestInstance2 is running..."+a0015SynClzAndInst);
            a0015SynClzAndInst.instance2();
        }
    }

    //锁对象
    private synchronized void instance(){
        A0004SleepTool.second(3);
         System.out.println("synInstance is going..."+this.toString());
         A0004SleepTool.second(3);
        System.out.println(" syncInstance ended  "+this.toString() );
    }
    //锁对象
    private synchronized void instance2(){
        A0004SleepTool.second(3);
        System.out.println("synInstance2 is going..."+this.toString());
        A0004SleepTool.second(3);
        System.out.println(" syncInstance2 ended  "+this.toString() );
    }
    //类锁，实际是锁类的class对象
    private static synchronized void synClass(){
        A0004SleepTool.second(1);
        System.out.println("synClass is going...");
        A0004SleepTool.second(1);
        System.out.println(" synClass  end  " );
    }
    public  static void main(String[] args){
        A0015SynClzAndInst synClzAndInst = new A0015SynClzAndInst();
        Thread t1 = new Thread(new InstanceSyn(synClzAndInst));
        A0015SynClzAndInst synClzAndInst2 = new A0015SynClzAndInst();
        Thread t2 = new Thread(new Instance2Syn(synClzAndInst));
            t1.start();
            t2.start();
        SynClass synClass = new SynClass();
        synClass.start();
        A0004SleepTool.second(1);
    }
}
