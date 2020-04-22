package com.fengtest;

/**
 * @Author: frt
 * @Date: 2019/10/24 13:47
 */
public class TestDemo3 {
    public static void main(String[] args){
        InitConfigCheckThread c = new InitConfigCheckThread();
        Thread t = new Thread(c);
        c.start();
    }
}
class InitConfigCheckThread extends   Thread{
    @Override
    public void run() {
        while(true){
            int i =  (int)(Math.random()*10+1)*1000;
            System.out.println("i = " + i);
            if (i%10000 == 0){
                  System.out.println(" i yes . " );
            }

            int j =  (int)(Math.random()*10+1)*10000;
            System.out.println("j = " + j);
            if (j%100000 == 0){
                System.out.println(" j yes . " );
            }
            int k =  (int)(Math.random()*10+1)*100000;
            System.out.println("k = " + k);
            if (k%1000000 == 0){
                System.out.println(" k yes . " );
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
