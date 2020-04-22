package com.fengcase.part3;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 类说明:演示引用类型的原子操作类
 * @Author: frt
 * @Date: 2019/8/21 22:17
 */
public class A0039UseAtomicReference {
    static AtomicReference<UserInfo> useref = new AtomicReference<>();

    public static void main(String[] args){
        UserInfo user = new UserInfo("Mark",15);
        useref.set(user);
        UserInfo updateUser = new UserInfo("Bill",17);//要变化的新实列
        useref.compareAndSet(user,updateUser);
        System.out.println(useref.get().getName());
        System.out.println(useref.get().getAge());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    //定义一个实体类
    static class UserInfo{
        private String name;
        private int  age;
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
