package com.fengcase3.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: frt
 * @Date: 2019/11/18 11:19
 */
public class TestDemo4 {
    public static void main(String[] args){

//        System.out.println(null+"");
//        String str1="中英益利资产管理股份有限公司";
//        String str2="中英益利资产管理股份有限公司";
//        System.out.println(str1.compareTo(str2) );
//
//        String str3 = "樊勇　";
//        int length = str3.length();
//        int length2 = "樊勇 ".length();
//        System.out.println("str3.trim() = " + str3.trim());
//        System.out.println("str3 = " + str3.replace("　",""));

//        while(true){
//            try {
//                Thread.sleep(10*1000);//等系统启动2分钟后，再开始轮询
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                int i = 1/0;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        List ls = new ArrayList();
        ls.add(1);
        ls.add(2);
        ls.add(3);
        ls.add(4);
        ls.add(5);
        System.out.println(ls.subList(0,3));
        System.out.println(ls.subList(3,5));
        ls.subList(3,5).clear();
        System.out.println("------"+ls);
    }
}
