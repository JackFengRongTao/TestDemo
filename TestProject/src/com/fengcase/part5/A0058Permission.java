package com.fengcase.part5;

/**
 * 类说明：
 * @Author: frt
 * @Date: 2019/9/18 21:15
 */
public class A0058Permission {
    //是否允许查询，二进制第1位，0表示否，1表示是
    public static final int ALLOW_SELECT = 1 << 0;//0001 = 1
    //是否允许新增，二进制第2位，0表示否，1表示是
    public static final int ALLOW_INSERT = 1 << 1;//0010 = 2
    //是否允许修改，二进制第3位，0表示否，1表示是
    public static final int ALLOW_UPDATE = 1 << 2;//0100 = 4
    //是否允许修改，二进制第4位，0表示否，1表示是
    public static final int ALLOW_DELETE = 1 << 4;//1000 = 8
    //存储目前的权限状态
    private int flag;
    //设置用户的权限
    public void setPer(int per){
        flag = per;
    }
    //增加用户的权限（1个或者多个）
    public void enable(int per){
        flag = flag|per;
    }
    //删除用户的权限
    public void disable(int per){
        flag = flag&~per;
    }
    //判断用户的权限
    public boolean isAllow(int per){
        return ((flag&per)==per);
    }

    //权判断用户没有的限
    public boolean isNotAllow(int per){
        return ((flag&per)==0);
    }
    public static void main(String[] args){
        int flag = 15;// 1111 = 15,所有的权限
        A0058Permission  permission = new A0058Permission();
        permission.setPer(flag);
        permission.disable(ALLOW_DELETE|ALLOW_INSERT);
        System.out.println(" select = " + permission.isAllow(ALLOW_SELECT));
        System.out.println(" update = " + permission.isAllow(ALLOW_UPDATE));
        System.out.println(" insert = " + permission.isAllow(ALLOW_INSERT));
        System.out.println(" delete = " + permission.isAllow(ALLOW_DELETE));
    }

}
