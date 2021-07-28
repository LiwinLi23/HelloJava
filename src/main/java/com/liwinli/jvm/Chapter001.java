package com.liwinli.jvm;

public class Chapter001 {
    private static int s_int = 1;
    static { s_int = 2; s_int2 = 10; }

    private static int s_int2 = 20;
    public static void main(String[] args) {
        System.out.println(s_int + ", " + s_int2);
    }
}
