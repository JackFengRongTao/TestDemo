package com.fengcase2.javacase;

import java.util.Arrays;

/**
 * 类说明：使用Lambda表达式调用Arrays的类方法
 * @Author: frt
 * @Date: 2019/8/5 10:18
 */
public class A0023lambdaArrays {
    public static void main(String[] args){
        String[] arr1 = new String[]{"java","fkava","fkit","ios","android"};
        Arrays.parallelSort(arr1,((o1, o2) -> o1.length()-o2.length()));
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = new int[]{3,-4,25,16,30,18};
        //left代表数组中前一个索引处的元素，计算第一个元素时，left为1
        //right代表数组中当前索引处的元素
        Arrays.parallelPrefix(arr2,((left, right) -> left*right));
        System.out.println(Arrays.toString(arr2));
        long[] arr3 = new long[5];
        Arrays.parallelSetAll(arr3,operand->operand*5);
        System.out.println(Arrays.toString(arr3));
    }
}
