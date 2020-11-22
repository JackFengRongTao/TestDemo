package com.fengcase3.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static void main(String[] args) {
        String str = "您提交的QMT使用申请流程已通过。客户账户已开立完成，用户名为12位资金账号，默认密码为dwzq@123，请及时通知用户修改默认密码。";
        System.out.println(str.length());

        List<String> tprx = new ArrayList<>();
        tprx.add("1");
        tprx.add("2");
        tprx.add("3");
        System.out.println(tprx.toString().substring(tprx.toString().indexOf("[")+1,tprx.toString().indexOf("]")));

    }
}
