package com.fengcase.part4.template;

import java.util.Date;

/**
 * 类说明：模板方法的使用范例；模板方法的父类；
 * @Author: frt
 * @Date: 2019/9/15 16:14
 */
public abstract class A0054SendCustom {
    //抽象方法，子类来具体实现
    public abstract void to();
    public abstract void from();
    public abstract void content();
    public  void date(){
        System.out.println(new Date());
    }
    public abstract void send();
    //框架方法-模板方法
    public  void sendMessage(){
        to();
        from();
        content();
        date();
        send();
    }

}
