package com.fengcase2.javacase;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: frt
 * @Date: 2019/9/2 16:28
 */
public class A0041UseStreamGroupBy {



        public static void main(String[] args) {

            //3 apple, 2 banana, others 1
            List<Item> items = Arrays.asList(
                    new Item("apple", 10, new BigDecimal("9.99")),
                    new Item("banana", 20, new BigDecimal("19.99")),
                    new Item("orang", 10, new BigDecimal("29.99")),
                    new Item("watermelon", 10, new BigDecimal("29.99")),
                    new Item("papaya", 20, new BigDecimal("9.99")),
                    new Item("apple", 10, new BigDecimal("9.99")),
                    new Item("banana", 10, new BigDecimal("19.99")),
                    new Item("apple", 20, new BigDecimal("9.99"))
            );

            Map<String, Long> counting = items.stream().collect(
                    Collectors.groupingBy(Item::getName, Collectors.counting()));

            System.out.println(counting);

            Map<String, Integer> sum = items.stream().collect(
                    Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

            System.out.println(sum);
            System.out.println("------------------------------------------------------");
            Map<String,Object>  map1  = new HashMap<String,Object>();
            map1.put("type","1");
            map1.put("sbjg","1");
            Map<String,Object>  map2  = new HashMap<String,Object>();
            map2.put("type","1");
            map2.put("sbjg","2");
            Map<String,Object>  map3  = new HashMap<String,Object>();
            map3.put("type","2");
            map3.put("sbjg","5");
            Map<String,Object>  map4  = new HashMap<String,Object>();
            map4.put("type","2");
            map4.put("sbjg","1");
            Map<String,Object>  map5  = new HashMap<String,Object>();
            map5.put("type","9");
            map5.put("sbjg","1");
            List<Map<String,Object>> items1 = new ArrayList<>();
            items1.add(map1);
            items1.add(map2);
            items1.add(map3);
            items1.add(map4);
            items1.add(map5);
            Map<String, Long> counting1 = items1.stream().collect(
                    Collectors.groupingBy(map->map.get("type").toString(), Collectors.counting()));
            System.out.println(counting1);
            Map<String, Optional<Map<String, Object>>> min = items1.stream().collect(Collectors.groupingBy(map->map.get("type").toString(), Collectors.minBy(Comparator.comparing(map->new BigDecimal(map.get("sbjg").toString()).doubleValue()))));
            min.forEach((key,val)->{
                System.out.println(key +  " = " + val.toString());
            });
            Map<String, Optional<Map<String, Object>>> max = items1.stream().collect(Collectors.groupingBy(map->map.get("type").toString(), Collectors.maxBy(Comparator.comparing(map->new BigDecimal(map.get("sbjg").toString()).doubleValue()))));
            max.forEach((key,val)->{
                System.out.println(key +  " = " + val.get().toString());
            });
        }

}
 class Item {

    private String name;
    private int qty;
    private BigDecimal price;

    //constructors, getter/setters

     public Item(String name, int qty, BigDecimal price) {
         this.name = name;
         this.qty = qty;
         this.price = price;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getQty() {
         return qty;
     }

     public void setQty(int qty) {
         this.qty = qty;
     }

     public BigDecimal getPrice() {
         return price;
     }

     public void setPrice(BigDecimal price) {
         this.price = price;
     }
 }
