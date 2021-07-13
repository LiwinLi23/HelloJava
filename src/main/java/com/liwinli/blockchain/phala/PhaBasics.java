package com.liwinli.blockchain.phala;

public class PhaBasics {
    // 获取抵押率
    public static float getCurDiYaLv() {
        return getCurDiYaLv(4, 10);
    }

    public static float getCurDiYaLv(float diYaTotal, float gongYingLiang) {
        return diYaTotal * 100 / gongYingLiang;
    }

//    public static f
}
