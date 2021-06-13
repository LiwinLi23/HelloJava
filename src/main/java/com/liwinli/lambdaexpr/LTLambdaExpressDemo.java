package com.liwinli.lambdaexpr;

@FunctionalInterface
interface Foo {
//    void say();
    int add(int x, int y);
    static int mul(int x, int y) { return x * y; }
    default int dev(int x, int y) { return x / y; }
}

public class LTLambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void say() { System.out.println("Hello, Interface"); }
//        };
//        foo.say();

        System.out.println("Lambda express 写接口三步骤： 1 复制中中括号，2 写死右箭头 3 落地大括号");
//        Foo foo = () -> { System.out.println("Hello, Interface"); };
//        foo.say();

        System.out.println("函数式表达式接口中只能定义一个方法！！！所以将之前的say()注释起来");
        Foo foo = (int x, int y) -> {
            return x + y;
        };
        System.out.println("1 + 2 = " + foo.add(1, 2));

        System.out.println("调用接口的static方法: " + Foo.mul(10, 2));
        System.out.println("调用接口匿名对象的default方法, 该方法可以被子类覆盖 " + foo.dev(10, 2));
    }
}
