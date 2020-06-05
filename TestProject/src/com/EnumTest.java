package com;

/**
 * enum类的使用
 * @Author: frt
 * @Date: 2020/1/8 10:45
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Status.SCUUESS.getDesc());
    }
}
 enum Status {

    SCUUESS("1", "成功"), FAILED("2", "失败");

     public String value;
     public String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

      Status(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}