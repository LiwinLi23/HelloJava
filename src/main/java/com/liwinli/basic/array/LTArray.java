package com.liwinli.basic.array;

public class LTArray {
    private static void allowArrayParam(String[] strParams) {
        if (null != strParams) {
            for (String item : strParams) { System.out.println(strParams); }
        }
    }

    public static void test1() { allowArrayParam(new String[]{"Str1", "Str2"}); }

//    public static void test2() {allowArrayParam({"Str1", "Str2"});}       // 这样是不允许的， 编译错误
}
