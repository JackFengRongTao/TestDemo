package com.fengcase3.utils;

public class ExceptionOutTest {
    public static void main(String[] args) {
        try {
            String a = null;
            System.out.println(Integer.parseInt(a.trim()));
        } catch (Exception e) {
            //输出异常
            System.out.println(e);
            //输出异常
            System.out.println(e.getMessage());
            //输出异常
            e.printStackTrace();
        }
    }
}
