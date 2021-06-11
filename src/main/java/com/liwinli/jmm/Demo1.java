package com.liwinli.jmm;

public class Demo1 {

    private static volatile boolean prepareExit = false;

    public static void showJMMIssue() throws InterruptedException {
        new Thread(() -> {
            System.out.println("Waiting for prepareExit to true");
            while (!prepareExit) { }

            System.out.println("Exit work thread");
        }).start();

        Thread.sleep(2000);

        new Thread(() -> exitWorkThread()).start();
    }

    private static void exitWorkThread() {
        System.out.println("Do something");
        prepareExit = true;
        System.out.println("Looking for work thread exit");
    }
}
