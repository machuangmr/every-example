package com.machd.printdemo;


/**
 * 三个线程轮流打印 AABC
 * @author macd
 * @version 3
 * @since 3.0
 *
 * 总结： 1、使用一个共享变量作为锁的标记，即int i
 *       2、使用3个线程，分别去做相应的业务处理
 *       3、每个线程中的同步代码块中，做相应的业务逻辑处理。
 *       4、对于不断交换打印数字的问题，其实就是对于锁的状态的获取。
 */
public class PrintAABC {
    
    private static int i = 1;

    private static final Object object = new Object();

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            while(true) {
                synchronized (object) {
                    if (i % 4 == 1 || i % 4 == 2 ) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("A---");
                        i++;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }

   static class ThreadB implements Runnable{
    //private int i = 0;
        @Override
        public void run() {

            while (true){
                synchronized (object) {
                    if (i % 4 == 3){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("B---");
                        i++;
                        object.notifyAll();
                    }
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // flag = true;

                }
            }
        }
    }

    static class ThreadC implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    if (i % 4 == 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("C---");
                        i++;
                        object.notifyAll();
                    }
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }
    public static void main(String[] args) {
        //ThreadA threadA = new ThreadA();
        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());
        Thread t3 = new Thread(new ThreadC());
        t1.start();
        t2.start();
        t3.start();
    }
}

