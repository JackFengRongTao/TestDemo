package com.fengcase.part4.rw;

import com.fengcase.tools.A0004SleepTool;

/**
 *类说明：用内置锁实现商品服务接口
 * @Author: frt
 * @Date: 2019/9/7 13:12
 */
public class A0046UseSyn implements  A0046GoodsService{
    private A0045GoodsInfo goodsInfo;

    public A0046UseSyn(A0045GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized A0045GoodsInfo getNum() {
        A0004SleepTool.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        A0004SleepTool.ms(5);
        goodsInfo.changeNumber(number);
    }
}
