package com.liwinli.basic.thread;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class LTThread {
    public static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("2.执行thread.start()之后，线程的状态：" + Thread.currentThread().getState());
            try {
                //休眠100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4.执行Thread.sleep(long)完成之后，线程的状态：" + Thread.currentThread().getState());
        });

        System.out.println("1.通过new初始化一个线程，但是还没有start()之前，线程的状态：" + thread.getState());
        thread.start();
        Thread.sleep(50);
        System.out.println("3.执行Thread.sleep(long)时，线程的状态：" + thread.getState());
        Thread.sleep(100);
        System.out.println("5.线程执行完毕之后，线程的状态：" + thread.getState() + "\n");
    }

    public static void test2() throws InterruptedException {
        AtomicBoolean obj = new AtomicBoolean(false);
        Thread thread1 = new Thread(() -> {
            System.out.println("2.执行thread.start()之后，线程的状态：" + Thread.currentThread().getState());
            synchronized (obj) {
                try {
                    //thread1需要休眠100毫秒
                    Thread.sleep(100);
                    //thread1100毫秒之后，通过wait()方法释放obj对象是锁
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("4.被object.notify()方法唤醒之后，线程的状态：" + Thread.currentThread().getState());
        });
        System.out.println("1.通过new初始化一个线程，但是还没有start()之前，线程的状态：" + thread1.getState());
        thread1.start();
        Thread.sleep(150);
        System.out.println("3.执行object.wait()时，线程的状态：" + thread1.getState());
        new Thread(() -> {
            synchronized (obj) {
                //唤醒等待的线程
                obj.notify();
            }
        }).start();
        Thread.sleep(10);
        System.out.println("5.线程执行完毕之后，线程的状态：" + thread1.getState() + "\n");
    }

    public static void test3() throws InterruptedException {
        System.out.println("======线程状态间的状态转换NEW->RUNNABLE->BLOCKED->RUNNABLE->TERMINATED======");
//定义一个对象，用来加锁和解锁
        AtomicBoolean obj2 = new AtomicBoolean(false);
//定义一个线程，先抢占了obj2对象的锁
        new Thread(() -> {
            synchronized (obj2) {
                try {
                    //第一个线程要持有锁100毫秒
                    Thread.sleep(100);
                    //然后通过wait()方法进行等待状态，并释放obj2的对象锁
                    obj2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//定义目标线程，获取等待获取obj2的锁
        Thread thread3 = new Thread(() -> {
            System.out.println("2.执行thread.start()之后，线程的状态：" + Thread.currentThread().getState());
            synchronized (obj2) {
                try {
                    //thread3要持有对象锁100毫秒
                    Thread.sleep(100);
                    //然后通过notify()方法唤醒所有在ojb2上等待的线程继续执行后续操作
                    obj2.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("4.阻塞结束后，线程的状态：" + Thread.currentThread().getState());
        });
//获取start()之前的状态
        System.out.println("1.通过new初始化一个线程，但是还没有thread.start()之前，线程的状态：" + thread3.getState());
//启动线程
        thread3.start();
//先等100毫秒
        Thread.sleep(50);
//第一个线程释放锁至少需要100毫秒，所以在第50毫秒时，thread3正在因等待obj的对象锁而阻塞
        System.out.println("3.因为等待锁而阻塞时，线程的状态：" + thread3.getState());
//再等300毫秒
        Thread.sleep(300);
//两个线程的执行时间加上之前等待的50毫秒以供250毫秒，所以第300毫秒，所有的线程都已经执行完毕
        System.out.println("5.线程执行完毕之后，线程的状态：" + thread3.getState());
    }

    public static void main(String[] args) throws InterruptedException {
//        LTThread.test1();
//        LTThread.test2();
        LTThread.test3();
    }

    private static Thread.State getThreadStatus(Thread thread) {
        if (null == thread) { return null; }

        return thread.getState();
    }
}
