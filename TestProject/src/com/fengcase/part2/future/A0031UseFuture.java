package com.fengcase.part2.future;

import com.fengcase.tools.A0004SleepTool;
import jdk.nashorn.internal.codegen.CompilerConstants;

import javax.swing.plaf.SliderUI;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类说明：演示future等的使用
 * @Author: frt
 * @Date: 2019/8/12 22:51
 */
public class A0031UseFuture {
    private static class UseCallable implements Callable<Integer>{
        private  int sum;

        @Override
        public Integer call() throws Exception {         ;
            System.out.println(" Callable SubThread start sum." );
            Thread.sleep(2000);
            for (int i = 0;i < 5000;i++){
                sum += i;
            }
            System.out.println(" Callable SubThread complete sum,result= "+sum );
            return sum;
        }
    }
    public static void main(String[] args){
        try {
            UseCallable useCallable = new UseCallable();
            FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
            new Thread(futureTask).start();
            Random r = new Random();
           // A0004SleepTool.second(1);
//            if (r.nextBoolean()){
                System.out.println(" Get UseCallable result = "+futureTask.get() );
//            }else{
//                System.out.println(" Break sum. " );
//                futureTask.cancel(true);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(" futureTask.get() is a blocking function. ");
    }
}
