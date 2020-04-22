package com.fengcase2.javacase;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * 类说明：使用Collection的stream方法
 * @Author: frt
 * @Date: 2019/8/5 14:33
 */
public class A0025UseCollectionStream {
    public static void main(String[] args){
        //String[] books = new String[]{"C","疯狂Java","Java初级","Java中级","Java高级","疯狂python中级"};
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂ios讲义");
        books.add("疯狂Ajax讲义");
        books.add("疯狂Android讲义");
        //统计书名包含"疯狂"子串的图书数量
        System.out.println(books.stream().filter(ele->((String)ele).contains("疯狂")).count());
        //统计书名包含"Java"子串的图书数量
        System.out.println(books.stream().filter(ele->((String)ele).contains("Java")).count());
        //统计书名字符串大于10的图书数量
        System.out.println(books.stream().filter(ele->((String)ele).length()>10).count());
        //先调用Collection对象的stream()方法将集合转换为Stream
        //再调用Stream的mapToInt()方法获取原有的Stream对应的IntStream
        books.stream().mapToInt(ele->((String)ele).length()).forEach(System.out::println);

    }
}
