package com.liwinli.jvm.stack;

public class StackFlowDemo {
    public static void main(String[] args) {
        int length = args.length;
        StackFlowDemo demo = new StackFlowDemo();
        demo.method1();
//        System.out.println("args length: " + args.length);
        demo.iPPvsPPi();
        System.out.println("- main()");
    }

    public void method1() {
        System.out.println("+ method1()");
        method2();
        System.out.println("- method1()");
    }

    public void method2() {
        System.out.println("+ method2()");
        method3();
        System.out.println("- method2()");
    }

    public void method3() {
        System.out.println("+ method3()");
        int i = 10; int j = 20; int k = 30;
        System.out.println("- method3()");
//        System.out.println(10 / 0);
    }

    public void iPPvsPPi() {
        // 第一类问题
        int i1 = 10; i1++; int i2 = 10; ++i2;

        // 第二类问题
        int i3 = 20; int i4 = i3++;
        int i5 = 20; int i6 = ++i5;
    }
}
