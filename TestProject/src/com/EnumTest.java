package com;

/**
 * @Author: frt
 * @Date: 2020/1/8 10:45
 */
public enum EnumTest {

}
 enum Status {

    SCUUESS("1", "成功"), FAILED("2", "失败");

    private String value;
    private String desc;

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

    private Status(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}