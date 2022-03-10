package com.machd.printdemo;

/**
 * 轮流打印AB
 * 两个线程轮流打印AB
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class PrintAB {

    private static boolean flag = true;
    private static Object obj = new Object();

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            synchronized (obj) {
                if (flag){
                    try {
                        System.out.println("A 抢占到锁，要准备释放锁。。。");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A线程执行到这里了。。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A线程开始打印。。。");
                System.out.println("B--");
                flag = false;
                obj.notify();
            }
        }
    }


    static class ThreadB implements Runnable{

        @Override
        public void run() {
            synchronized (obj) {
                if (!flag){
                    try {
                        System.out.println("B线程抢占到锁，准备释放");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B线程。。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B线程开始打印。。。");
                System.out.println("A--");
                flag = true;
                obj.notify();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());
        t1.start();
        t2.start();
    }
}
