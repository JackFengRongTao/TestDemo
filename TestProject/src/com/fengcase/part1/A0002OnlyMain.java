package com.fengcase.part1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 类说明：java的多线程无处不在
 * @Author: frt
 * @Date: 2019/7/31 23:44
 */
public class A0002OnlyMain {
    public static void main(String[] args){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threadInfos){
            System.out.println("threadId = " + threadInfo.getThreadId()+"  "+threadInfo.getThreadName());
        }
    }
}
