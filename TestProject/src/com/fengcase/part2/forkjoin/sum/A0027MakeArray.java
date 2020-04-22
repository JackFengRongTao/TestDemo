package com.fengcase.part2.forkjoin.sum;

import java.util.Random;

/**
 * 类说明：产生整形数组
 * @Author: frt
 * @Date: 2019/8/10 15:28
 */
public class A0027MakeArray {
    //数组长度
    public static final int ARRAY_LENGTH = 400000000;
    public static int[] makeArray(){
        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int [ARRAY_LENGTH];
        for (int i=0;i<ARRAY_LENGTH;i++){
            result[i] = r.nextInt(ARRAY_LENGTH*3);
        }
        return result;
    }
}
