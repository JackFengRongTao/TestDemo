package com.fengtest;

/**
 * 抛出新异常并被捕获
 */
public class ThrowNewExceptionDemo{
    /**
     *
     * 主线程可以先结束，异步线程去处理通知相关人员来处理-
     */
    public static void main(String[] args) {
        try {
            exceptionFunction();
        } catch (Exception e) {
            if(e.getMessage().contains("[testError]")){
                System.out.println("起异步线程通知相关人员去处理.");
                AsyncNotifyThreadDemo asyncNotifyThread = new AsyncNotifyThreadDemo();
                Thread thread = new Thread(asyncNotifyThread);
                thread.start();
            }
        }
        System.out.println("main结束执行.");
    }

    public static void exceptionFunction() throws Exception {
        try {
            int i = 1/0;//模拟调用接口出异常
        } catch (Exception e) {
            throw  new Exception("[testError]"+e);
        }
        System.out.println("exceptionFunction结束执行.");
    }
}

class AsyncNotifyThreadDemo implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(3*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //模拟发短信或者邮件
        System.out.println("xxx，您好，请您看下接口有无问题...");
    }
}
