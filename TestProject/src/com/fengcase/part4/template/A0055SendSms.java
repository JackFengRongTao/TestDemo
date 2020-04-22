package com.fengcase.part4.template;

/**
 *类说明：模板方法的派生类
 * @Author: frt
 * @Date: 2019/9/15 16:24
 */
public class A0055SendSms extends A0054SendCustom {
    @Override
    public void to() {
        System.out.println("Mark");
    }

    @Override
    public void from() {
        System.out.println("Bill");
    }

    @Override
    public void content() {
        System.out.println("Hello world");
    }

    @Override
    public void send() {
        System.out.println("Send sms");
    }
    public static void main(String[] args){
        A0054SendCustom sendCustom = new A0055SendSms();
        sendCustom.sendMessage();
    }
}
