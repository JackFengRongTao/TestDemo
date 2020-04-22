package com.fengcase2.javacase;

import java.math.BigDecimal;

/**
 *
 * @Author: frt
 * @Date: 2019/9/4 14:28
 */
public class A0042UseBieDecimal {
    public static void main(String[] args){
        System.out.println(new BigDecimal("1000.9999").longValue());
        System.out.println("//-----------------------");
        System.out.println(new BigDecimal("0").compareTo(new BigDecimal("0")));
        System.out.println(new BigDecimal("1000.9999").longValue());
        System.out.println(new BigDecimal("2").divide( new BigDecimal("0"), 4, BigDecimal.ROUND_HALF_UP));

    }
}
