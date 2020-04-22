package com.fengcase2.javacase;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 类说明：Stream的distinct的用法
 * @Author: frt
 * @Date: 2019/8/8 14:03
 */
public class A0026UseStreamDistinct {
    private static class Book {
        private String name;
        private int price;

        public Book(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public int hashCode() {
            int hashno = 7;
            hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
            return hashno;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            final Book book = (Book) obj;
            if (this == book) {
                return true;
            } else {
                return (this.name.equals(book.name) && this.price == book.price);
            }
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        //----------------------去重------------------------
        //List<String> list = Arrays.asList("AA","BB","BB","CC","DD");
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("BB");
        list.add("CC");
        list.add("DD");
        long l = list.stream().distinct().count();
        System.out.println(" Num1 of distinct elements :" + l);

        String output = list.stream().distinct().collect(Collectors.joining(","));
        System.out.println("output = " + output);

        //----------------------去重:Stream.distinct() with List of Objects------------------------
        List<Book> lis = new ArrayList<>();
        lis.add(new Book("Core Java", 200));

        lis.add(new Book("Core Java", 200));

        lis.add(new Book("Learning Freemarker", 150));

        lis.add(new Book("Spring MVC", 300));

        lis.add(new Book("Spring MVC", 300));

        long l2 = lis.stream().distinct().count();

        System.out.println(" num2 of distinct books: " + l2);

        lis.stream().distinct().forEach(b -> System.out.println(b.getName() + "," + b.getPrice()));

        //----------------------去重:Distinct by Property------------------------
        long l3 = lis.stream().filter(distinctByKey(b -> b.getName())).count();
        System.out.println(" num3 of distinct books: " + l3);
        lis.stream().filter(distinctByKey(b -> b.getName())).forEach(b -> System.out.println(b.getName() + " , " + b.getPrice()));

        //----------------------去重------------------------
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("1", "AA");
        map1.put("2", "aa");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("1", "BB");
        map2.put("2", "bbb");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("1", "BB");
        map3.put("2", "bb");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("1", "CC");
        map4.put("2", "cc");
        ls.add(map1);
        ls.add(map2);
        ls.add(map3);
        ls.add(map4);
        long l4 = ls.stream().filter(distinctByKey(map -> map.get("1"))).count();
        System.out.println(" num4 of distinct maps: " + l4);
        ls.stream().filter(distinctByKey(map -> map.get("1"))).forEach(map -> System.out.println(map.get("1") + " , " + map.get("2")));
    }
}
