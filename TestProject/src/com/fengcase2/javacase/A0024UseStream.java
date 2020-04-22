package com.fengcase2.javacase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 类说明：使用java 8 新增的Stream操作集合
 * @Author: frt
 * @Date: 2019/8/5 13:13
 */
public class A0024UseStream {
    public static void main(String[] args){
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();
        IntStream is2 = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .add(18)
                .add(18)
                .build();
        //下面调用聚集方法的代码每次只能执行一行
//        System.out.println(" is 所有元素的最大值： "+is.max().getAsInt() );
//        System.out.println(" is 所有元素的最小值： "+is.min().getAsInt() );
//        System.out.println(" is 所有元素的总和： "+is.sum() );
//        System.out.println(" is 所有元素的总数： "+is.count());
//        System.out.println(" is 所有元素的平均值： "+is.average());
//        System.out.println(" is 所有元素的平方是否都大于20： "+is.allMatch(ele->ele*ele>20) );
//        System.out.println(" is 是否包含任何元素的平方大于20： "+is.anyMatch(ele->ele*ele>20) );
        //将is映射成一个新Stream，新Stream的每个元素时原Stream元素的2倍+1
        IntStream newIs = is.map(ele->ele*2+1);
        //使用方法引用的方式来遍历集合元素
        newIs.forEach(System.out::println);
        //将is映射成一个新Stream，新Stream的每个元素时原Stream元素的2倍+1
        IntStream newIs2 = is2.map(ele->ele*10+1);
        //使用方法引用的方式来遍历集合元素
        newIs2.forEach(System.out::println);
        System.out.println("//-----------------------------------------------");
        //----------------------去重------------------------
        List<String> list = new ArrayList<>();
        List<Map<String,Object>> list2 = new ArrayList<>();
//        list.add("aa");
//        list.add("bb");
//        list.add("bb");
//        list.add("cc");
//        list.add("vv");
//        list.add("nn");

        long l = list.stream().distinct().count();
        List<Map<String,Object>> ls = list2.stream().filter(map->!"3".equals(map.get("zt").toString())).collect(Collectors.toList());
        List<Map<String,Object>> ls2 = ls.stream().filter(map->!"3".equals(map.get("zt").toString())).collect(Collectors.toList());

        System.out.println(" No. of distinct elements :"+ l );
        String output = list.stream().distinct().collect(Collectors.joining(","));
        System.out.println("output = " + output);

        System.out.println("ls = " + ls);
        System.out.println("ls2 = " + ls2);



    }
}
