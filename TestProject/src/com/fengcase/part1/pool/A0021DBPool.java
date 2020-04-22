package com.fengcase.part1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 *类说明：实现一个数据库的连接池
 * @Author: frt
 * @Date: 2019/8/4 20:01
 */
public class A0021DBPool {
    private static LinkedList<Connection> pool = new LinkedList<>();
    public A0021DBPool(int inintalSize) {
        if(inintalSize>0){
            for (int i=0;i<inintalSize;i++){
                pool.addLast(A0020SqlConnectImpl.fetchConnection());
            }
        }
    }
    public Connection fetchConn(long mills) throws InterruptedException{
        synchronized (pool){
            if (mills<0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long overtime = System.currentTimeMillis()+mills;
                long remain = mills;
                while (pool.isEmpty()&&remain>0){
                    pool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
    public void releaseConn(Connection conn){
        if (conn!=null){
            synchronized (pool){
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }
}
