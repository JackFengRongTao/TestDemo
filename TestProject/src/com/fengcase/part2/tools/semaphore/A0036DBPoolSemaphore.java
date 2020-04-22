package com.fengcase.part2.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *类说明：演示Semaphone用法，一个数据库连接池的实现
 * @Author: frt;
 * @Date: 2019/8/20 21:56
 */
public class A0036DBPoolSemaphore {
    private final static int POOL_SiZe = 10;
    private final Semaphore useful,useless;//useful表示可用的数据库连接，useless表示已用的数据库连接
    public A0036DBPoolSemaphore() {
        this.useful = new Semaphore(POOL_SiZe);
        this.useless = new Semaphore(0);
    }
    //存放数据库连接池的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    //初始化池
    static{
        for (int i = 0 ;i<POOL_SiZe;i++){
            pool.addLast(A0035SqlConnectImpl2.fetchConnection());
        }
    }
    //归还连接
    public void returnConnnect(Connection connection) throws InterruptedException{
        if (connection!=null){
            System.out.println(" 当前有"+useful.getQueueLength()+"个线程等待数据库连接！" );
            useless.acquire();
            synchronized (pool){
                pool.addLast(connection);
            }
            useful.release();
        }
    }
    //从池子拿连接
    public Connection takeConnection() throws InterruptedException{
        useful.acquire();
        Connection conn;
        synchronized (pool){
            conn = pool.removeFirst();
        }
        useless.release();
        return conn;
    }
}
