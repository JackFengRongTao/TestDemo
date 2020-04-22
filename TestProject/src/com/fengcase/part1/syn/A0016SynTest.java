package com.fengcase.part1.syn;

/**
 *
 * @Author: frt
 * @Date: 2019/8/3 16:49
 */
public class A0016SynTest {
    private volatile int age = 100000;//初始值100000
    public void setAge(){
        age = age +20 ;
    }
    public synchronized  void test2(){
        age--;
    }
    public synchronized  void test(){
        age++;
        test2();
    }
    public int getAge(){
        return age;
    }
    private static class TestThread extends Thread{
        private A0016SynTest synTest;

        public TestThread(String name, A0016SynTest synTest) {
            super( name);
            this.synTest = synTest;
        }

        @Override
        public void run() {
           for (int i = 0;i<100000;i++){//递增100000
                synTest.test();
           }
            System.out.println(Thread.currentThread().getName()+" age = "+synTest.getAge());
        }
    }
    public static void main(String[] args){
        A0016SynTest synTest = new A0016SynTest();
        Thread endThread = new TestThread("endThread",synTest);
        endThread.start();
        for (int i = 0;i<100000;i++){//递减100000
            synTest.test2();
        }
        System.out.println(Thread.currentThread().getName()+" age = "+synTest.getAge());
    }
}
