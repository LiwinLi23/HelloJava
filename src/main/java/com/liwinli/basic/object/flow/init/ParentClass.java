package com.liwinli.basic.object.flow.init;

public class ParentClass {
    public static String staticField = "这是第一个父类初始化: 静态变量";

    static {
        System.out.println(staticField);
        System.out.println("这是第二个父类初始化: 静态初始化区域");
    }

    public String field = "这是第三个父类初始化: 普通成员";

    {
        System.out.println(field);
        System.out.println("这是第四个父类初始化: 非static块");
    }

    public ParentClass() {
        System.out.println("这是第五个父类初始化: 构造函数");
    }
}
