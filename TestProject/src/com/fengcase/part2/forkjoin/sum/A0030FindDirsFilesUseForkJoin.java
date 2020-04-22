package com.fengcase.part2.forkjoin.sum;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 类说明：遍历指定目录(含子目录)找寻指定文件类型
 * @Author: frt
 * @Date: 2019/8/12 21:30
 */
public class A0030FindDirsFilesUseForkJoin extends RecursiveAction {
    private File path;//当前任务需要搜寻的目录

    public A0030FindDirsFilesUseForkJoin(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<A0030FindDirsFilesUseForkJoin> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null){
            for (File file : files){
                if (file.isDirectory()){
                    subTasks.add(new A0030FindDirsFilesUseForkJoin(file));
                }else{
                    //遇到文件，检查
                    if(file.getAbsolutePath().endsWith("txt")){
                        System.out.println(" files: "+file.getAbsolutePath() );
                    }
                }
            }
            if (!subTasks.isEmpty()){
                for (A0030FindDirsFilesUseForkJoin subTask :invokeAll(subTasks)){
                    subTask.join();//等待子任务完成
                    System.out.println("subTask.join() is executed." );
                }
            }
        }
    }
    public static void main(String[] args){
        try {
            //用一个ForkJoinPool实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            A0030FindDirsFilesUseForkJoin task = new A0030FindDirsFilesUseForkJoin(new File("D:/"));
            pool.execute(task);//异步调用
            System.out.println(" Task is Running...... " );
            Thread.sleep(1);
            int otherWork = 0 ;
            for (int i = 0;i < 100;i++){
                otherWork = otherWork + i;
            }
            System.out.println(" Main Thread done sth...... ,otherWork = "+otherWork);
            task.join();//阻塞的方法
            System.out.println(" Task end " );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
