package com.machd.printdemo;

import java.util.concurrent.TimeUnit;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class TestPrintAB {

    private static boolean flag = true;
    private static final Object OBJ = new Object();
    static class PintAThread implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (OBJ) {
                    if (!flag) {
                        try {
                            OBJ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A--");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                    OBJ.notify();
                }
            }
            }
    }

    static class PrintBThread implements Runnable{

        @Override
        public void run() {
            while(true) {
                synchronized (OBJ) {
                    if (flag) {
                        try {
                            OBJ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B--");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                    OBJ.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
       Thread t1 = new Thread(new PintAThread());
       Thread t2 = new Thread(new PrintBThread());
       t1.start();
       t2.start();
    }
}
