package com.liwinli.jvm;

public class Chapter004 {
    private static int sCount = 0;

    public static void main(String[] args) {
        System.out.println(++sCount);
        main(args);
        int i = 10; int j = 20; int k = i + j;
    }
}
