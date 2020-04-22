package com.fengcase.part2.forkjoin.sum;

import com.fengcase.tools.A0004SleepTool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 类说明：fork/join 多线程计算值
 * @Author: frt
 * @Date: 2019/8/10 15:43
 */
public class A0029SumArrayForkJoin {
    private static class SumTask extends RecursiveTask<Long>{
        private final static int THRESHOLD = A0027MakeArray.ARRAY_LENGTH/10;
        private int[] src;//表示我们要实际统计的数组
        private int fromIndex;//开始统计的下标
        private int toIndex;//统计到哪里结束的下标

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        protected Long compute() {
            if (toIndex - fromIndex < THRESHOLD){
                Long count = 0L;
                for (int i=fromIndex;i<=toIndex;i++){
                   // A0004SleepTool.ms(1);
                    count += src[i];
                }
                return count;
            }else{
                int mid = (fromIndex+toIndex)/2;
                SumTask left = new SumTask(src,fromIndex,mid);
                SumTask right = new SumTask(src,fromIndex,mid);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }
    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = A0027MakeArray.makeArray();
        SumTask innerFind = new SumTask(src,0,src.length-1);
        long start = System.currentTimeMillis();
        pool.invoke(innerFind);//同步调用
        System.out.println(" Task is Running...... " );
        System.out.println(" The count is  "+innerFind.join()+" spend time:  "+(System.currentTimeMillis()-start)+" ms");
    }
}
