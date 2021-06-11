package com.liwinli.concurrency;

public class ConcurrencyVSSerial {
    private static long sCount = 10000;

    private static void concurrencyTest() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread subShread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < sCount; ++i) { ++a; }
            }
        });
        subShread.start();
        int b = 0;
        for (int i = 0; i < sCount; ++i) { --b; }
        subShread.join();
        long durationTime = System.currentTimeMillis() - startTime;
        System.out.println("ConcurrentTest duration: " + durationTime);
    }

    private static void serialsTest() {
        long startTime = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < sCount; ++i) { ++a; }

        int b = 0;
        for (int i = 0; i < sCount; ++i) { --b; }
        long durationTime = System.currentTimeMillis() - startTime;
        System.out.println("SerialTest duration: " + durationTime);
    }

    /*
    *   ConcurrencyVSSerial.test(10000);
        ConcurrencyVSSerial.test(100000);
        ConcurrencyVSSerial.test(1000000);
        ConcurrencyVSSerial.test(10000000);
        ConcurrencyVSSerial.test(100000000);
    * */
    public static void test(long countParam) throws InterruptedException {
        sCount = countParam;
        concurrencyTest();
        serialsTest();
    }
}
