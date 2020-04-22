package com.fengcase.part4.rw;

import com.fengcase.tools.A0004SleepTool;

import java.util.Random;

/**
 * 类说明：对商品业务的应用
 * @Author: frt
 * @Date: 2019/9/7 13:51
 */
public class A0048BusiApp {
    static final int readwriteRatio = 10;//读写比例
    static final int minthreadNum = 3;//读写比例
    //读操作
    private static class GetThread implements Runnable{
        private A0046GoodsService goodsService;

        public GetThread(A0046GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("start = " + start);
            for (int i = 0;i<100;i++){//操作100次
                goodsService.getNum();
            }
            long end = System.currentTimeMillis();
            System.out.println("end = " + end);
            System.out.println(Thread.currentThread().getName()+" 读取商品数据耗时： "+(end-start)+"ms---------");
        }


    }
    //读操作
    private static class SetThread implements Runnable{
        private A0046GoodsService goodsService;

        public SetThread(A0046GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("start = " + start);
            Random r = new Random();
            for (int i = 0;i<10;i++){//操作10次
                A0004SleepTool.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            long end = System.currentTimeMillis();
            System.out.println("end = " + end);
            System.out.println(Thread.currentThread().getName()+" 写商品数据耗时： "+(end-start)+"ms/////////");
        }
        public static void main(String[] args){
            A0045GoodsInfo goodsInfo = new A0045GoodsInfo("haha",100000,1000);
            //1.
//            A0046GoodsService goodsService = new A0046UseSyn(goodsInfo);
            //2.
            A0046GoodsService goodsService = new A0047UseRwLock(goodsInfo);
            for (int i=0;i<minthreadNum;i++){
                Thread setThread = new Thread(new SetThread(goodsService));
                for (int j=0;j<readwriteRatio;j++){
                    Thread getThred = new Thread(new GetThread(goodsService));
                    getThred.start();
                }
                A0004SleepTool.ms(100);
                setThread.start();
            }
        }

    }
}
