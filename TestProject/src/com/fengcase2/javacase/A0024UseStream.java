package com.fengcase2.javacase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ��˵����ʹ��java 8 ������Stream��������
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
        //������þۼ������Ĵ���ÿ��ֻ��ִ��һ��
//        System.out.println(" is ����Ԫ�ص����ֵ�� "+is.max().getAsInt() );
//        System.out.println(" is ����Ԫ�ص���Сֵ�� "+is.min().getAsInt() );
//        System.out.println(" is ����Ԫ�ص��ܺͣ� "+is.sum() );
//        System.out.println(" is ����Ԫ�ص������� "+is.count());
//        System.out.println(" is ����Ԫ�ص�ƽ��ֵ�� "+is.average());
//        System.out.println(" is ����Ԫ�ص�ƽ���Ƿ񶼴���20�� "+is.allMatch(ele->ele*ele>20) );
//        System.out.println(" is �Ƿ�����κ�Ԫ�ص�ƽ������20�� "+is.anyMatch(ele->ele*ele>20) );
        //��isӳ���һ����Stream����Stream��ÿ��Ԫ��ʱԭStreamԪ�ص�2��+1
        IntStream newIs = is.map(ele->ele*2+1);
        //ʹ�÷������õķ�ʽ����������Ԫ��
        newIs.forEach(System.out::println);
        //��isӳ���һ����Stream����Stream��ÿ��Ԫ��ʱԭStreamԪ�ص�2��+1
        IntStream newIs2 = is2.map(ele->ele*10+1);
        //ʹ�÷������õķ�ʽ����������Ԫ��
        newIs2.forEach(System.out::println);
        System.out.println("//-----------------------------------------------");
        //----------------------ȥ��------------------------
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
