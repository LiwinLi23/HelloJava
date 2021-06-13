package com.liwinli.concurrency.aqs.demo;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static int value = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void test() {
        Thread thread1 = new Thread(() -> count()); Thread thread2 = new Thread(() -> count());
        Thread thread3 = new Thread(() -> count());
        thread1.start(); thread2.start(); thread3.start();
        try {
            thread1.join(); thread2.join(); thread3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(value);
    }

    private static void count() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < 1000; ++i) { ++value; }
        } finally {
            reentrantLock.unlock();
        }
    }
}
