package com.fengcase2.javacase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Decription:lamada表达式不能用break、continue
 * @Author: frt
 * @Date: 2019/9/5 9:07
 */
public class A0043UseForeachReturn {
    public static void main(String[] args){

            List<String> list = Collections.EMPTY_LIST;
            list = Arrays.asList("awg","weg","wweg","wegwe");
            list.forEach(System.out::println);
            System.out.println("//-----------------------------------------");
            list.forEach(str -> {
                if("awg".equals(str)){
                    return;
                }
                System.out.println(str);
            });


    }

}
