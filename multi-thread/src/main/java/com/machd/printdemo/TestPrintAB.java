package com.machd.printdemo;

import java.util.concurrent.TimeUnit;

public class TestPrintAB {
    /**
     * 思路：线程间通信，采用一个共享变量来控制.
     * wait/notify本质上是互斥方法，实现互斥，那么最简单的就是synchronized
     * @param args
     */
    // 同一把互斥锁
    private static final Object obj = new Object();
    private static boolean flag = true;
    public static void main(String[] args) {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();

    }
    static class T1 implements Runnable{
        /**
         * 思路：1、先抢占锁，如果抢占到锁后则执行逻辑
         *      2、判断共享变量的值，如果值为true，则需要将值转换为下一个值，并且唤醒等待当前锁的所有线程，自己释放锁。
         *      3、由于当前线程释放了锁，其他线程遍可以去竞争锁
         */

        @Override
        public void run() {
            while(true) {
                synchronized (obj) {
                    if (flag) {
                        System.out.println("A---");
                        flag = false;
                        try {
                            obj.notifyAll();
                            obj.wait();
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class T2 implements Runnable{

        @Override
        public void run() {
            while(true) {
                synchronized (obj) {
                    if (!flag) {
                        System.out.println("B---");
                        flag = true;
                        try {
                            obj.notifyAll();
                            obj.wait();
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
