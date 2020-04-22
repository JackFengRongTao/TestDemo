package com.fengcase.part2.forkjoin.sum;

import com.fengcase.tools.A0004SleepTool;

/**
 * 类说明：单线程计算
 * @Author: frt
 * @Date: 2019/8/10 15:35
 */
public class A0028SumNormal {
    public static void main(String[] args){
        long count = 0;
        int[] src = A0027MakeArray.makeArray();
        long start = System.currentTimeMillis();
        for (int i = 0;i < src.length;i++){
            //A0004SleepTool.ms(1);
            count += src[i];
        }
        System.out.println(" The count is  "+count+
                " spend time: "+(System.currentTimeMillis()-start)+"ms");
    }
}
