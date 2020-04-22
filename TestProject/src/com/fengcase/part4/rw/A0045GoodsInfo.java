package com.fengcase.part4.rw;

/**
 *商品的实体类
 * @Author: frt
 * @Date: 2019/9/7 13:02
 */
public class A0045GoodsInfo {
    private final String name;
    private double totalMoney;//总销售额
    private int storeNum;//总库存

    public A0045GoodsInfo(String name, double totalMoney, int storeNum) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNum = storeNum;
    }

    public String getName() {
        return name;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getStoreNum() {
        return storeNum;
    }
    public void changeNumber(int sellNumber){
        this.totalMoney += sellNumber*25;
        this.storeNum -=sellNumber;
    }
}
