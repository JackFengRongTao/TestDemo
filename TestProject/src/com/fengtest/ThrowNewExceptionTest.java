package com.fengtest;

/**
 * 抛出新异常并被捕获
 */
public class ThrowNewExceptionTest {


    public static void main(String[] args) {
        try {
            test0();
        } catch (Exception e) {
            if(e.getMessage().contains("[testError]")){
                System.out.println("起线程");
                AsyncNotifyThread asyncNotifyThread = new AsyncNotifyThread();
                Thread thread = new Thread(asyncNotifyThread);
                thread.start();
            }
        }
        System.out.println("结束");
    }
    public static void test0() throws Exception {
        try {
            int i1 = test();
            int i3 = test3();
        } catch (Exception e) {
            throw new Exception("[testError]"+e.getMessage());
        }
    }
    public static int test() throws Exception {
        try {
            int i = 1/0;
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }finally {
            test2();//重新初始化
        }
        System.out.println("1");
        return 1;
    }
    public static int test2() {

        try {
            int i = Integer.parseInt("hhh");
        } catch (Exception e) {
             e.printStackTrace();
        }
        System.out.println("重新初始化...");
        return 2;
    }

    public static int test3() throws Exception {
        System.out.println("进来test3，执行接口调用");
        try {
            int i = Integer.parseInt("3hhh");
        } catch (Exception e) {
            throw  new Exception("test3Error");
        }
        System.out.println("执行接口调用");
        return 3;
    }

}

class AsyncNotifyThread implements Runnable{
        @Override
        public void run() {
            System.out.println("请您看下接口有无问题...");
            int count = 4;//从redis取值
            while (count > 0){
                try {
                    Thread.sleep(60*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("请您看下接口有无问题...");
                count--;
            }
        }
}
