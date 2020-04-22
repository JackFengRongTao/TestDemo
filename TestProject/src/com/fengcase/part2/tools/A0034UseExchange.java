package com.fengcase.part2.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * 类说明：Exchange的使用
 * @Author: frt
 * @Date: 2019/8/20 21:28
 */
public class A0034UseExchange {
    private static final Exchanger<Set<String>> exchange
            = new Exchanger<Set<String>>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA = new HashSet<>();//存放数据的容器
                try {
                    setA = exchange.exchange(setA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run () {
                Set<String> setB = new HashSet<>();//存放数据的容器
                try {
                    setB = exchange.exchange(setB);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
