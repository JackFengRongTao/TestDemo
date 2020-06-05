package com.fengcase3.utils;

/**
 *在调用一些接口的时候需要先初始化再去调用，
 * 这个demo是模仿第一次调用不成的情况下，
 * catch里面重新初始化一下调用接口参数，(程序会先执行catch，再执行finally)
 * 然后在finally里面再尝试第二次调用。
 */
public class TryCatchFinallyTest {
    public static void main(String[] args)  {
        try {
            main2();
        } catch (Exception e) {
            System.out.println("出现异常:"+e.getMessage());
        }
    }

    public static void main2() throws Exception {
        try {
            System.out.println("------------1.第一次尝试调用----------------");
            int a = 1 / 0;//模仿接口，第一次尝试调用，看接口正常与否
        } catch (Exception e) {//出现异常，接口不通
            System.out.println("------------2.重新初始化----------------");
        }finally {
            try {
                System.out.println("------------3.第二次尝试调用----------------");
                int a = 1 / 0;//模仿接口，第二次尝试调用后，抛异常处理
            } catch (Exception e) {
                System.out.println("------------4.重新初始化----------------");
                throw new Exception(e);
            }
        }
    }
}
