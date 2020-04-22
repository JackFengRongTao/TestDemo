package com.fengcase.tools;

import java.util.concurrent.TimeUnit;

/**
 * 类说明：线程休眠辅助工具类
 * @Author: frt
 * @Date: 2019/8/1 21:20
 */
public class A0004SleepTool {
    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int  seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 按毫秒休眠
     * @param milliseconds 毫秒数
     */
    public static final void ms(int  milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
