package com.liwinli.basic.object.flow.init;

public class SubClass extends ParentClass {
    public static String staticField = "这是第一个子类初始化: 静态变量";

    static {
        System.out.println(staticField);
        System.out.println("这是第二个子类初始化: 静态初始化区域");
    }

    public String field = "这是第三个子类初始化: 普通成员";

    {
        System.out.println(field);
        System.out.println("这是第四个子类初始化: 非static块");
    }

    public SubClass() {
        System.out.println("这是第五个子类初始化: 构造函数");
    }
}
